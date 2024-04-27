import pgService from "../services/pg.service.js";

export const getUsuario = async (username,password)=>{
    const  pg = new pgService();
    return await  pg.connection.oneOrNone('SELECT * FROM "user" WHERE "user_name" = $1 and "password" = $2', [username,password]);
}
export const createUser = async (username, name,birthdate,mail,password, img_profile, phone, gender) => {
    const  pg = new pgService();
    return await  pg.connection.oneOrNone('INSERT INTO "user" (user_name, name, birthdate, mail, password, img_profile, phone, gender) VALUES ($1, $2, $3, $4, $5, $6, $7, $8) RETURNING *',
        [username, name, birthdate, mail, password, img_profile, phone, gender]);

};


export const updateUser = async (username, password,id) => {
    const  pg = new pgService();
    return await  pg.connection.oneOrNone('UPDATE usuario SET username = $1, password = $2 WHERE id = $3', [username,password,id]);
};

export const deleteUser = async (id) => {
    const pg = new pgService();
    return await pg.connection.oneOrNone('DELETE FROM usuario WHERE id = $1', [id]);
};
