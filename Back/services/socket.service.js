import { exports } from "../config/default.js";

const socketIo = require('socket.io');
const io = socketIo(server);

// Manejar conexiones de Socket.IO
io.on('connection', (socket) => {
    console.log('Nuevo cliente conectado');

    // Manejar desconexiones
    socket.on('disconnect', () => {
        console.log('Cliente desconectado');
    });
});

/**
export default class pgService {
    static  instance;
    connection;

    constructor() {
        if (pgService.instance) {
            return pgService.instance;
        }
        pgService. instance = this;
        const pgp = pgPromise({ Initialization Options });
        this.connection = pgp(exports.postgress);
        this.connection.connect()
            .then(obj => {
                console.log('db connect' + obj.client.serverVersion);
                obj.done();
            }).catch(error => {
            console.log('ERROR:', error.message || error);
        });
    }
}
**/
