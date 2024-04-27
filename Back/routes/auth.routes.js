import { Router } from "express";
import {create, deleteU, login, update, allUser, imgCreate,imgUpdate} from "../controllers/auth.controller.js";

const router = Router();

router.get("/",  login );
router.delete("/", deleteU);
router.put("/", update);
router.post("/",  create);
router.get("/",  allUser);
router.post("/", imgUpdate);
router.post("/", imgCreate);







export default router;