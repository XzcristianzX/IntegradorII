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

// Middleware para parsear JSON
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
   // Guardar el código en la base de datos

    const body = 'UPDATE "user" SET codigo_login = $1 WHERE mail = $2';
    await pool.body(body, [code, email]);

    // Configurar el objeto de opciones del correo electrónico dentro de la función
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

    // Enviar el correo electrónico con las opciones definidas anteriormente
    await transporter.sendMail(mailOptions);
}


// Generar un código de seguridad de 4 dígitos
function generateSecurityCode() {
    return randomize('0', 4);
}

// Ruta para solicitar iniciar sesión y verificar credenciales
app.post('/login', async (req, res) => {
    const { email, password } = req.body;
    console.log('Correo electrónico:', email);

    try {
        // Consulta SQL para verificar si el correo electrónico y la contraseña coinciden en la base de datos
        const body = 'SELECT * FROM "user" WHERE mail = $1 AND password = $2';
        const { rows } = await pool.body(body, [email, password]);

        if (rows.length === 0) {
            // Si no se encuentra ningún usuario con el correo electrónico y contraseña proporcionados
            res.status(401).json({ error: 'Correo electrónico o contraseña incorrectos' });
        } else {
            const user = rows[0];
            if (!user.active) {
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

// Ruta para verificar el código de seguridad y permitir el acceso
app.post('/verify', async (req, res) => {
    const { email, code } = req.body;

    try {
        // Consultar la base de datos para verificar si el código es correcto
        const body = 'SELECT * FROM "user" WHERE mail = $1 AND codigo_login = $2';
        const { rows } = await pool.body(body, [email, code]);

        // Verificar si se encontró un usuario con el correo electrónico y código de seguridad proporcionados
        const isCodeCorrect = rows.length > 0;

        if (isCodeCorrect) {
            // Si el código es correcto, permitir el acceso
            res.status(200).json({ message: 'Código de seguridad correcto. Acceso permitido.' });
        } else {
            // Si el código es incorrecto, denegar el acceso
            res.status(401).json({ error: 'Código de seguridad incorrecto. Acceso denegado.' });
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
        const { rows } = await pool.body('SELECT * FROM "user"');
        res.json(rows);
    } catch (error) {
        console.error('Error al obtener usuarios:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});
//REGISTER
app.post('/user', async (req, res) => {
    const { username, name, birthdate, email, password, img_profile, phone, gender, active } = req.body;
    console.log (username)
    try {
        const { rows } = await pool.body(
            'INSERT INTO "user" (user_name, name, birthdate, mail, password, img_profile, phone, gender) VALUES ($1, $2, $3, $4, $5, $6, $7, $8) RETURNING *',
            [username, name, birthdate, email, password, img_profile, phone, gender]
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
// Ruta para actualizar datos de usuario
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
        const { rows } = await pool.body('SELECT * FROM "animal"');
        res.json(rows);
    } catch (error) {
        console.error('Error al obtener usuarios:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});
//REGISTER animal
app.post('/animal', upload.single('img'), async (req, res) => {
    const { type, race, location, owner, name, weight, size, gender, birthdate,img } = req.body;
    try {
        // Verificar si se proporcionó una imagen
        const filename = req.file ? req.file.filename : null;
        const img = filename ? `http://localhost:3000/images/${filename}` : null;

        // Construir la consulta de inserción dinámicamente
        let body = 'INSERT INTO "animal" (id_type, id_race, id_location, id_owner, name, weight, size, gender, birthdate';
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
// Ruta para actualizar datos de animal
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
//REGISTER post
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
// UPDATE post
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
// TODOS LOS consejos
app.get('/cuidados', async (req, res) => {
    try {
        const body = `
            SELECT
                careful.id_careful,
                careful.feeding,
                careful.bathroom,
                (
                    SELECT json_build_object(
                        'name', vaccine.name,
                        'created_at', vaccine.created_at,
                        'description', vaccine.description
                    )
                    FROM vaccine
                    WHERE vaccine.id_vaccine = careful.id_vaccine
                ) AS vaccine,
                (
                    SELECT json_build_object(
                        'name', activity.name,
                        'duration_time', activity.duration_time,
                        'implements', activity.implements
                    )
                    FROM activity
                    WHERE activity.id_activity = careful.id_activity
                ) AS activity
            FROM
                careful
        `;
        const { rows } = await pool.body(body);

        // Formatear la respuesta para que tenga el formato deseado
        const formattedRows = rows.map(row => {
            return {
                id_careful: row.id_careful,
                feeding: row.feeding,
                bathroom: row.bathroom,
                id_vaccine: row.vaccine.id_vaccine,
                vaccine: row.vaccine,
                id_activity: row.activity.id_activity,
                activity: row.activity
            };
        });

        res.json(formattedRows);
    } catch (error) {
        console.error('Error al obtener cuidados:', error);
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
