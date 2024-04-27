import { Router } from "express";
import {update, create, getName, getAll, deleteP} from "../controllers/producto.controller.js";
import {validate} from  "../middlewares/validator.middleware.js";
import { createProductValidator, updateProductValidator, getProductByNameValidator } from "../validators/producto.validator.js";
import {verifyToken} from "../middlewares/token.middleware.js";

const router = Router();

router.get("/All", getAll);
router.get("/", validate(getProductByNameValidator), getName);
router.post("/", validate(createProductValidator), create);
router.put("/", validate(updateProductValidator), update);
router.delete("/", deleteP);

export default router;