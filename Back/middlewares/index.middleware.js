import { verifyToken } from "./token.middleware.js"

import { Router } from "express"; 

const router = Router();

router.use('/producto', verifyToken);



export default router;