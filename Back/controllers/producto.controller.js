import {
    getProductoAll,
    getProductoName,
    deleteProduct,
    createProduct,
    updateProduct
} from "../models/producto.model.js";
export const getAll = async (req, res) => {
    try {
        const result =await getProductoAll();
        res.json({success: true, result: result , msg: 'Todos Los Productos'})
    } catch (error) {
        res.status(500).json({success: false, message: error.message});
    }
}
export const getName = async (req, res) =>{
    try {
        let {nombre} = req.query;
        const result =await getProductoName(nombre);
        return res.status(200).json({success: true, result: result, message: "get name"});
    } catch (error) {
        res.status(500).json({success: false, message: error.message});
    }
}

export const deleteP = async (req, res) => {
    try {
        let { id } = req.query;
        const result = await deleteProduct(id);
        return res.status(200).json({ success: true, message: `Producto eliminado correctamente con id: ${result}` });
    } catch (error) {
        res.status(500).json({success: false, message: error.message});
    }
}
export const  update = async (req, res) => {
    try {
        let {detalle, nombre, valor, id} = req.query;
        let result = await updateProduct(detalle, nombre,valor,id);
        return res.status(200).json({success: true, message: `Producto actualizado correctamente con id ${result}`});
    } catch (error) {
        res.status(500).json({success: false, message: error.message});
    }
}
export const create = async (req, res) => {
    try {
        let {detalle, nombre, valor} = req.query;
        let result = await createProduct(detalle, nombre, valor);
        return res.status(200).json({success: true, result: result, message: "Producto creado"});
    } catch (error) {
        res.status(500).json({success: false, message: error.message});
    }
}

