import dotenv  from "dotenv"
dotenv.config(); 
export const exports =  {
  port: process.env.PORT || 3000,
  postgress: process.env.DB_URL_PG,
  secret: process.env.SECRET_KEY
};