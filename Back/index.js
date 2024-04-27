/**
import Server from "./config/server.js";
const runServer = new Server();
runServer.load()
**/

const express = require('express');
const multer = require('multer');
const path = require('path');

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


// Configurar Express para servir archivos estáticos desde el directorio 'images'
app.use('/images', express.static(path.join(__dirname, 'images')));

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

// Ruta para subir una imagen
app.post('/img', upload.single('file'), (req, res) => {
    if (!req.file) {
        return res.status(400).json({ error: 'No se ha proporcionado ningún archivo' });
    }
    res.send('Archivo subido correctamente');
});

// Ruta para obtener la imagen de perfil de un usuario por su ID
app.get('/user/:id/img', async (req, res) => {
    const userId = req.params.id;

    try {
        const { rows } = await pool.query('SELECT img_profile FROM "user" WHERE id_user = $1', [userId]);

        if (rows.length === 0 || !rows[0].img_profile) {
            return res.status(404).json({ error: 'Usuario no encontrado o sin imagen de perfil' });
        }

        const imgProfileFilename = rows[0].img_profile;
        res.redirect(`/images/${imgProfileFilename}`);
    } catch (error) {
        console.error('Error al obtener imagen de perfil:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});

// Ruta para actualizar la imagen de perfil de un usuario
app.put('/user/:id', upload.single('img'), async (req, res) => {
    const userId = req.params.id;
    const { user_name, name, birthdate, mail, password, phone, gender } = req.body;
    const filename = req.file ? req.file.filename : null;
    const img_profile = filename ? `http://localhost:3000/images/${filename}` : null;

    try {
        const { rows } = await pool.query(
            'UPDATE "user" SET user_name = $1, name = $2, birthdate = $3, mail = $4, password = $5, img_profile = $6, phone = $7, gender = $8 WHERE id_user = $9 RETURNING *',
            [user_name, name, birthdate, mail, password, img_profile, phone, gender, userId]
        );
        if (rows.length === 0) {
            return res.status(404).json({ error: 'Usuario no encontrado' });
        }

        res.json({ ...rows[0], filename: filename, img_profile: img_profile });

    } catch (error) {
        console.error('Error al actualizar usuario:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});


app.put('/user/:user_name', upload.single('img'), async (req, res) => {
    const user_name = req.params.username;
    const { name, password, phone } = req.body;
    const filename = req.file ? req.file.filename : null;
    const img_profile = filename ? `http://localhost:3000/images/${filename}` : null;

    try {
        const { rows } = await pool.query(
            'UPDATE "user" SET name = $1, password = $2, img_profile = $3, phone = $4 WHERE user_name = $5 RETURNING *',
            [name, password, img_profile, phone,user_name]
        );
        if (rows.length === 0) {
            return res.status(404).json({ error: 'Usuario no encontrado' });
        }

        res.json({ ...rows[0], filename: filename, img_profile: img_profile });

    } catch (error) {
        console.error('Error al actualizar usuario:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});




// TODOS LOS USER
app.get('/user', async (req, res) => {
    try {
        const { rows } = await pool.query('SELECT * FROM "user"');
        res.json(rows);
    } catch (error) {
        console.error('Error al obtener usuarios:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});
// LOGIN
app.put('/user', async (req, res) => {
    const { username, password} = req.query;
    try {
        const { rows } = await pool.query('SELECT * FROM "user" WHERE "user_name" = $1 and "password" = $2', [username,password]);
        if (rows.length === 0) {
            return res.status(401).json({ error: 'Usuario no encontrado: ' + username });
        }
        const user = rows[0]; // Obtén el primer usuario de la lista
        res.json(user);
    } catch (error) {
        console.error('Error al iniciar sesión:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
});
//REGISTER
app.post('/user', async (req, res) => {
    const { username, name, birthdate, mail, password, img_profile, phone, gender } = req.query;
    try {
        const { rows } = await pool.query(
            'INSERT INTO "user" (user_name, name, birthdate, mail, password, img_profile, phone, gender) VALUES ($1, $2, $3, $4, $5, $6, $7, $8) RETURNING *',
            [username, name, birthdate, mail, password, img_profile, phone, gender]
        );
        const newUser = rows[0];
        io.emit('newUser', newUser); // Emitir un evento a todos los clientes conectados sobre el nuevo usuario
        res.status(201).json(newUser);
    } catch (error) {
        console.error('Error al crear usuario:', error);
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
