import {createUser, deleteUser, getUsuario, updateUser} from "../models/auth.model.js";
import {generateToken} from "../services/token.service.js";

export const create = async (req, res) => {
    const { username, name, birthdate, mail, password, img_profile, phone, gender } = req.query;
    try {
        let data = await createUser(username, name, birthdate, mail, password, img_profile, phone, gender);
        if (!data) {
            throw new Error("datos son incorrectos");
        }
        const newUser = rows[0];
        io.emit('newUser', newUser); // Emitir un evento a todos los clientes conectados sobre el nuevo usuario
        res.status(201).json(newUser);
    } catch (error) {
        console.error('Error al crear usuario:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
}
export const imgUpdate = async (req, res) => {
    try {
        let data = await Alluser();
        if (!data) {
            throw new Error("datos son incorrectos");
        }
        res.json(data)
        res.status(200).json({
            token: generateToken(data),
            success: true,
            msg: "Logeado Correctamente"
        });

        io.emit('newUser', newUser); // Emitir un evento a todos los clientes conectados sobre el nuevo usuario
        res.status(201).json(newUser);
    } catch (error) {
        console.error('Error al crear usuario:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
}
export const imgCreate = async (req, res) => {
    try {
        let data = await Alluser();
        if (!data) {
            throw new Error("datos son incorrectos");
        }
        res.json(data)
        res.status(200).json({
            token: generateToken(data),
            success: true,
            msg: "Logeado Correctamente"
        });

        io.emit('newUser', newUser); // Emitir un evento a todos los clientes conectados sobre el nuevo usuario
        res.status(201).json(newUser);
    } catch (error) {
        console.error('Error al crear usuario:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
}
export const allUser = async (req, res) => {
    try {
        let data = await Alluser();
        if (!data) {
            throw new Error("datos son incorrectos");
        }
        res.json(data)
        res.status(200).json({
            token: generateToken(data),
            success: true,
            msg: "Logeado Correctamente"
        });

        io.emit('newUser', newUser); // Emitir un evento a todos los clientes conectados sobre el nuevo usuario
        res.status(201).json(newUser);
    } catch (error) {
        console.error('Error al crear usuario:', error);
        res.status(500).json({ error: 'Error interno del servidor' });
    }
}

export const login = async (req, res) => {
    try {
        let {username, password} = req.query;
        let data = await getUsuario(username, password);
        if (!data) {
            throw new Error("datos son incorrectos");
        }

        const user = rows[0]; // Obtén el primer usuario de la lista
        res.json(user);
        res.status(200).json({
            token: generateToken(data),
            success: true,
            msg: "Logeado Correctamente"
        });
    } catch (error) {
        res.status(401).json({
            token: ' ',
            success: false,
            msg: error.message
        });
    }

       /* const token = jwt.sign({
            exp: Math.floor(Date.now() / 1000) + (60 * 60),
            data: {
                username: 'admin',
                role: 'rol_admin'
            }
        },  exports.secret);
*/

}

export const deleteU = async (req, res) => {
    try {
        let {id} = req.query;

        let data = await deleteUser(id);

        if (!data) {
            return res.status(200).json({success: true,data:data, message: "User deleted successfully"});

        }
         res.status(404).json({success: false, message: "no se encuentra el usuario"});

    } catch (error) {
        res.status(500).json({success: false, message: error.message});

    }
}

export const update = async (req, res) => {
    try {
        let {username, password,id} = req.query;

        let data = await updateUser(username, password,id);

        if (!data) {
            return res.status(200).json({success: true, message: "User actualizado correctamente"});

        }
        res.status(404).json({success: false, message: "no se encuentra el usuario"});

    } catch (error) {
        res.status(500).json({success: false, message: error.message});

    }
}






