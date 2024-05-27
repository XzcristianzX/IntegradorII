const express = require('express');
const multer = require('multer');
const path = require('path');
const nodemailer = require('nodemailer');
const randomize = require('randomatic');

const { Pool } = require('pg');
const http = require('http');
const socketIo = require('socket.io');

const app = express();
const PORT = process.env.PORT || 3000;

// Crear el servidor HTTP con Express
const server = http.createServer(app);

// Crear instancia de Socket.IO y pasarle el servidor HTTP
const io = socketIo(server);

// Configuración de la base de datos
const pool = new Pool({
    user: 'postgres',
    host: 'localhost',
    database: 'animal',
    password: 'admin',
    port: 5432,
});

app.use(express.json());

// Configurar multer para gestionar la subida de archivos
const storage = multer.diskStorage({
    destination: path.join(__dirname, 'images'),
    filename(req, file, cb) {
        cb(null, `${Date.now()}-${file.originalname}`);
    }
});
const upload = multer({ storage });

// Función para enviar correo electrónico con el código de seguridad
async function sendSecurityCode(email, code) {
    try {
        // Configurar el objeto de opciones del correo electrónico
        const mailOptions = {
            from: 'cristiaykarol1986@gmail.com',
            to: email, // Usar la dirección de correo electrónico proporcionada como argumento
            subject: 'Código de seguridad para iniciar sesión',
            text: `Tu código de seguridad es: ${code}`
        };

        // Configurar el transporte SMTP para enviar el correo electrónico
        const transporter = nodemailer.createTransport({
            service: 'gmail',
            auth: {
                user: 'cristiaykarol1986@gmail.com', // Correo electrónico desde el que se enviará el código
                pass: 'javx opcd ygro nqsu' // Contraseña del correo electrónico
            }
        });

        // Actualizar la base de datos con el código de seguridad
        const body = 'UPDATE "user" SET codigo_login = $1 WHERE mail = $2';
        const { rows } = await pool.query(body, [code, email]);


        console.log('Código de seguridad guardado en la base de datos.');

        // Enviar el correo electrónico con las opciones definidas anteriormente
        await transporter.sendMail(mailOptions);

        console.log('Correo electrónico con código de seguridad enviado correctamente.');
    } catch (error) {
        console.error('Error al enviar el correo electrónico:', error);
    }
}


// Generar un código de seguridad de 4 dígitos
function generateSecurityCode() {
    return randomize('0', 4);
}

// INICIO DE SESION
app.post('/login', async (req, res) => {
    const { email, password } = req.body;
    console.log('Correo electrónico:', email);

    try {
        // Consulta SQL para verificar si el correo electrónico y la contraseña coinciden en la base de datos
        const body = 'SELECT * FROM "user" WHERE mail = $1 AND password = $2';
        const { rows } = await pool.query(body, [email, password]);

        if (rows.length === 0) {
            // Si no se encuentra ningún usuario con el correo electrónico y contraseña proporcionados
            res.status(401).json({ error: 'Correo electrónico o contraseña incorrectos' });
        } else {
            const user = rows[0];
            console.log('Correo electrónico:', user.status);
            if (!user.status) {
                // Si el usuario está temporalmente desactivado
                res.status(401).json({ error: 'Usuario temporalmente desactivado. Para más información, comunícate al 3174178254' });
            } else {
                // Generar y enviar el código de seguridad
                const securityCode = generateSecurityCode();
                const code = securityCode;
                await sendSecurityCode(email, code);
                 res.status(200).json({ message: code });

                // Si se encuentra un usuario con el correo electrónico y contraseña proporcionados
            }
        }
    } catch (error) {
        console.error('Error al realizar la consulta SQL:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// VERIFICAR CÓDIGOS
app.post('/verify', async (req, res) => {
    const { email, code } = req.body;
    console.log('Correo electrónico:', email);
    try {
        console.log(code)
        console.log(email)
        // Consultar la base de datos para verificar si el código es correcto
        const query = 'SELECT * FROM "user" WHERE mail = $1 AND codigo_login = $2';
        const { rows } = await pool.query(query, [email, code]);

        // Verificar si se encontró un usuario con el correo electrónico y código de seguridad proporcionados
        const user = rows[0];

        if (user) {
            // Si se encontró un usuario con el correo electrónico y código de seguridad proporcionados, devolver los detalles del usuario
            res.status(200).json({ message: 'Código de seguridad correcto. Acceso permitido.', user: user });
        } else {
            // Si el código es incorrecto o el usuario no existe, denegar el acceso
            res.status(401).json({ error: 'Código de seguridad incorrecto o usuario no encontrado. Acceso denegado.' });
        }
    } catch (error) {
        console.error('Error al verificar el código de seguridad en la base de datos:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});


// Configurar Express para servir archivos estáticos desde el directorio 'images'
app.use('/images', express.static(path.join(__dirname, 'images')));
// Ruta para subir una imagen
app.post('/img', upload.single('file'), (req, res) => {
    if (!req.file) {
        return res.status(400).json({ error: 'No se ha proporcionado ningún archivo' });
    }
    res.send('Archivo subido correctamente');
});
// TODOS LOS USER
app.get('/user', async (req, res) => {
    try {
        const { rows } = await pool.query('SELECT * FROM "user"');
        res.json(rows);
    } catch (error) {
        console.error('Error al obtener usuarios:', error);
        res.status(500).json({ error: 'Error interno del servidor'});
    }
});

//REGISTRO DE USUARIOS
app.post('/user', async (req, res) => {
    const { username, name, birthdate, email, password, img_profile, phone, gender, active } = req.body;
    console.log (username)
    try {
        const { rows } = await pool.query(
            'INSERT INTO "user" (user_name, name, birthdate, mail, password, img_profile, phone, gender, status) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9) RETURNING *',
            [username, name, birthdate, email, password, img_profile, phone, gender, active]
        );
        const newUser = rows[0];
        io.emit('newUser', newUser); // Emitir un evento a todos los clientes conectados sobre el nuevo usuario
        res.status(201).json(newUser);
        console.log (newUser)

    } catch (error) {
        console.error('Error al crear usuario:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// ACTUALIZAR USUARIOS
app.put('/user/:id', upload.single('img'), async (req, res) => {
    const userId = req.params.id;
    const { user_name, name, birthdate, email, password, phone, gender } = req.body;
    const filename = req.file ? req.file.filename : null;
    const img_profile = filename ? `http://localhost:3000/images/${filename}` : null;

    try {
        // Construir la consulta de actualización dinámicamente
        let body = 'UPDATE "user" SET ';
        const values = [];
        let index = 1;

        // Verificar cada dato y agregarlo a la consulta si está presente
        if (user_name) {
            body += `"user_name" = $${index++}, `;
            values.push(user_name);
        }
        if (name) {
            body += `"name" = $${index++}, `;
            values.push(name);
        }
        if (birthdate) {
            body += `"birthdate" = $${index++}, `;
            values.push(birthdate);
        }
        if (email) {
            body += `"mail" = $${index++}, `;
            values.push(email);
        }
        if (password) {
            body += `"password" = $${index++}, `;
            values.push(password);
        }
        if (img_profile) {
            body += `"img_profile" = $${index++}, `;
            values.push(img_profile);
        }
        if (phone) {
            body += `"phone" = $${index++}, `;
            values.push(phone);
        }
        if (gender) {
            body += `"gender" = $${index++}, `;
            values.push(gender);
        }

        // Eliminar la última coma y espacio en la consulta
        body =body.slice(0, -2);

        // Agregar la condición WHERE y el ID del usuario
        body += ` WHERE "id_user" = $${index} RETURNING *`;
        values.push(userId);

        // Ejecutar la consulta con los valores dinámicos
        const { rows } = await pool.body(body, values);

        // Verificar si se encontró el usuario
        if (rows.length === 0) {
            return res.status(404).json({ error: 'Usuario no encontrado' });
        }

        // Responder con los datos actualizados del usuario
        res.json({ ...rows[0], filename: filename, img_profile: img_profile });

    } catch (error) {
        // Manejar errores
        console.error('Error al actualizar usuario:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// TODOS LOS animales
app.get('/animal', async (req, res) => {
    try {
        const { rows } = await pool.query('SELECT * FROM "animal"');
        res.json(rows);
    } catch (error) {
        console.error('Error al obtener animales:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

//REGISTRAR MASCOTA
app.post('/animal', upload.single('img'), async (req, res) => {
    const { type, race, location, owner, name, weight, size, gender, birthdate,img } = req.body;
    try {
        // Verificar si se proporcionó una imagen
        const filename = req.file ? req.file.filename : null;
        const img = filename ? `http://localhost:3000/images/${filename}` : null;

        // Construir la consulta de inserción dinámicamente
        let body = 'INSERT INTO "animal" (id_type, id_race, id_location, id_vaccine, id_owner, name, weight, size, gender, birthdate,status';
        let values = [type, race, location, owner, name, weight, size, gender, birthdate];

        // Agregar la columna de imagen si se proporcionó
        if (img) {
            body += ', img';
            values.push(img);
        }

        // Finalizar la consulta y agregar los valores dinámicos
        body += ') VALUES (' + values.map((_, index) => '$' + (index + 1)).join(', ') + ') RETURNING *';

        // Ejecutar la consulta con los valores dinámicos
        const { rows } = await pool.body(body, values);

        // Responder con los datos del nuevo animal registrado
        const newAnimal = rows[0];
        res.status(201).json(newAnimal);
    } catch (error) {
        // Manejar errores
        console.error('Error al crear animal:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});


//ACTUALIZAR INFOTMACIÓN DE MASCOTA
app.put('/animal/:id', upload.single('img'), async (req, res) => {
    const id_animal = req.params.id;
    const { type, race, location, owner, name, weight, size, gender, img, birthdate } = req.body;
    const filename = req.file ? req.file.filename : null;
    const img_profile = filename ? `http://localhost:3000/images/${filename}` : null;

    try {
        // Construir la consulta de actualización dinámicamente
        let body = 'UPDATE "animal" SET ';
        const values = [];
        let index = 1;

        // Verificar cada dato y agregarlo a la consulta si está presente
        if (type) {
            body += `"id_type" = $${index++}, `;
            values.push(type);
        }
        if (race) {
            body += `"id_race" = $${index++}, `;
            values.push(race);
        }
        if (location) {
            body += `"id_location" = $${index++}, `;
            values.push(location);
        }
        if (owner) {
            body += `"id_owner" = $${index++}, `;
            values.push(owner);
        }
        if (name) {
            body += `"name" = $${index++}, `;
            values.push(name);
        }
        if (weight) {
            body += `"weight" = $${index++}, `;
            values.push(weight);
        }
        if (size) {
            body += `"size" = $${index++}, `;
            values.push(size);
        }
        if (gender) {
            body += `"gender" = $${index++}, `;
            values.push(gender);
        }
        if (img) {
            body += `"img" = $${index++}, `;
            values.push(img);
        }
        if (birthdate) {
            body += `"birthdate" = $${index++}, `;
            values.push(birthdate);
        }

        // Eliminar la última coma y espacio en la consulta
        body = body.slice(0, -2);

        // Agregar la condición WHERE y el ID del animal
        body += ` WHERE "id_animal" = $${index} RETURNING *`;
        values.push(id_animal);

        // Ejecutar la consulta con los valores dinámicos
        const { rows } = await pool.body(body, values);

        // Verificar si se encontró el animal
        if (rows.length === 0) {
            return res.status(404).json({ error: 'Animal no encontrado' });
        }

        // Responder con los datos actualizados del animal
        res.json({ ...rows[0], filename: filename, img_profile: img_profile });

    } catch (error) {
        // Manejar errores
        console.error('Error al actualizar animal:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// TODOS LOS POST
app.get('/post', async (req, res) => {
    try {
        const { rows } = await pool.body('SELECT * FROM "adoption_post"');
        res.json(rows);
    } catch (error) {
        console.error('Error al obtener usuarios:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});


//CREAR UN POST
app.post('/post', upload.single('img'), async (req, res) => {
    const { title, body, id_user, status, created_at} = req.body;
    const filename = req.file ? req.file.filename : null;
    const img_url = filename ? `http://localhost:3000/images/${filename}` : null;

    try {
        const { rows } = await pool.body(
            'INSERT INTO "adoption_post" (title, body, id_user, status,created_at, img) VALUES ($1, $2, $3, $4, $5, $6) RETURNING *',
            [title, body, id_user, status,created_at, img_url]
        );
        const newPost = rows[0];
        io.emit('newPost', newPost); // Emitir un evento a todos los clientes conectados sobre el nuevo post
        res.status(201).json(newPost);
    } catch (error) {
        console.error('Error al crear post:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// ACTUALIZAR UN POST
app.put('/post/:id', upload.single('img'), async (req, res) => {
    const postId = req.params.id;
    const { title, body, status, created_at } = req.body;
    const filename = req.file ? req.file.filename : null;
    const img_url = filename ? `http://localhost:3000/images/${filename}` : null;

    try {
        let body = 'UPDATE "adoption_post" SET';
        const values = [];

        if (title) {
            body += ' title = $1,';
            values.push(title);
        }
        if (body) {
            body += ' body = $2,';
            values.push(body);
        }
        if (status) {
            body += ' status = $3,';
            values.push(status);
        }
        if (created_at) {
            body += ' created_at = $4,';
            values.push(created_at);
        }
        if (img_url) {
            body += ' img = $5,';
            values.push(img_url);
        }

        // Eliminar la coma extra al final de la consulta
        body = body.slice(0, -1);

        // Agregar la condición WHERE para actualizar el post específico
        body += ' WHERE id_adoption_post = $6 RETURNING *';
        values.push(postId);

        // Ejecutar la consulta
        const { rows } = await pool.body(body, values);

        if (rows.length === 0) {
            return res.status(404).json({ error: 'Post no encontrado' });
        }

        const updatedPost = rows[0];
        res.json(updatedPost);
    } catch (error) {
        console.error('Error al actualizar post:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});


// CUIDADOS
app.get('/cuidados', async (req, res) => {
    const { id_race } = req.query;
    let query = `
        SELECT
            careful.id_careful,
            careful.feeding,
            careful.bathroom,
            race.id_race,
            race.name AS race_name,
            activity.id_activity,
            activity.duration_time,
            activity.implements
        FROM
            careful
        INNER JOIN race ON race.id_careful = careful.id_careful
        INNER JOIN activity ON careful.id_activity = activity.id_activity
    `;

    if (id_race) {
        query += ` WHERE race.id_race = $1`;
    }

    try {
        const { rows } = await pool.query(query, id_race ? [id_race] : []);

        // Organizar los resultados en un formato deseado
        const formattedRows = rows.map(row => {
            return {
                id_careful: row.id_careful,
                feeding: row.feeding,
                bathroom: row.bathroom,
                race: {
                    id_race: row.id_race,
                    name: row.race_name
                },
                activity: {
                    id_activity: row.id_activity,
                    name: row.name,
                    duration_time: row.duration_time,
                    implements: row.implements
                }
            };
        });

        res.json(formattedRows);
    } catch (error) {
        console.error('Error al obtener cuidados:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

app.post('/vaccines', async (req, res) => {
    const { name, description } = req.body;
    try {
        const result = await pool.query(
            'INSERT INTO vaccine (name, description) VALUES ($1, $2) RETURNING *',
            [name, description]
        );
        res.status(201).json(result.rows[0]);
    } catch (error) {
        console.error('Error al agregar la vacuna:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// READ - Obtener todas las vacunas
app.get('/vaccines', async (req, res) => {
    try {
        const result = await pool.query('SELECT * FROM vaccine');
        res.status(200).json(result.rows);
    } catch (error) {
        console.error('Error al obtener las vacunas:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// READ - Obtener una vacuna por ID
app.get('/vaccines/:id', async (req, res) => {
    const { id } = req.params;
    try {
        const result = await pool.query('SELECT * FROM vaccine WHERE id_vaccine = $1', [id]);
        if (result.rows.length === 0) {
            return res.status(404).json({ error: 'Vacuna no encontrada' });
        }
        res.status(200).json(result.rows[0]);
    } catch (error) {
        console.error('Error al obtener la vacuna:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// UPDATE - Actualizar una vacuna
app.put('/vaccines/:id', async (req, res) => {
    const { id } = req.params;
    const { name, description } = req.body;
    try {
        const result = await pool.query(
            'UPDATE vaccine SET name = $1, description = $2 WHERE id_vaccine = $3 RETURNING *',
            [name, description, id]
        );
        if (result.rows.length === 0) {
            return res.status(404).json({ error: 'Vacuna no encontrada' });
        }
        res.status(200).json(result.rows[0]);
    } catch (error) {
        console.error('Error al actualizar la vacuna:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// DELETE - Eliminar una vacuna
app.delete('/vaccines/:id', async (req, res) => {
    const { id } = req.params;
    try {
        const result = await pool.query('DELETE FROM vaccine WHERE id_vaccine = $1 RETURNING *', [id]);
        if (result.rows.length === 0) {
            return res.status(404).json({ error: 'Vacuna no encontrada' });
        }
        res.status(200).json({ message: 'Vacuna eliminada correctamente' });
    } catch (error) {
        console.error('Error al eliminar la vacuna:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// Manejador de errores para rutas no encontradas
app.use((req, res) => {
    res.status(404).json({ error: 'Ruta no encontrada' });
});

// Manejador de errores global
app.use((err, req, res, next) => {
    console.error(err.stack);
    res.status(500).json({ error: 'Error interno del servidor' });
});

// Manejar conexiones de Socket.IO
io.on('connection', (socket) => {
    console.log('Nuevo cliente conectado');

    // Manejar desconexiones
    socket.on('disconnect', () => {
        console.log('Cliente desconectado');
    });
});

// Iniciar el servidor
server.listen(PORT, () => {
    const address = server.address();
    const ipAddress = address.address === '::' ? 'localhost' : address.address;
    console.log(`Servidor escuchando en http://${ipAddress}:${address.port}`);
});
