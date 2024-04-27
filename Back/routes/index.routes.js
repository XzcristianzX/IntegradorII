import { Router } from "express";
import Producto from "./producto.routes.js"
import Auth from "./auth.routes.js"

const router = Router();


router.use('/producto' , Producto);
router.use('/auth' , Auth);

// Manejador de ruta para rutas no encontradas
router.use((req, res, next) => {
    res.status(404).json({ success: false, message: "La ruta solicitada no existe, solo producto y auth" });
});


export default router;