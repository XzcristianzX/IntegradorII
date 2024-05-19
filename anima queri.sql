CREATE TABLE "user" (
  "id_user" SERIAL PRIMARY KEY NOT NULL,
  "user_name" VARCHAR(20) UNIQUE NOT NULL,
  "name" VARCHAR(50) NOT NULL,
  "birthdate" VARCHAR NOT NULL,
  "mail" VARCHAR(50) UNIQUE NOT NULL,
  "password" VARCHAR(4) NOT NULL,
  "img_profile" VARCHAR,
  "gender" CHAR NOT NULL,
  "status" BOOLEAN,
  "phone" VARCHAR(10),
  "codigo_login" VARCHAR(4)
);

CREATE TABLE "type_animal" (
  "id_type" SERIAL PRIMARY KEY NOT NULL,
  "name" VARCHAR(20) NOT NULL
);

CREATE TABLE "location" (
  "id_location" SERIAL PRIMARY KEY NOT NULL,
  "coordenate" VARCHAR,
  "route" VARCHAR
);

CREATE TABLE "vaccine" (
  "id_vaccine" SERIAL PRIMARY KEY NOT NULL,
  "name" VARCHAR(20),
  "created_at" VARCHAR,
  "description" TEXT,
  "status" BOOLEAN
);

CREATE TABLE "activity" (
  "id_activity" SERIAL PRIMARY KEY NOT NULL,
  "duration_time" VARCHAR,
  "implements" TEXT
);

CREATE TABLE "careful" (
  "id_careful" SERIAL PRIMARY KEY NOT NULL,
  "id_activity" INTEGER REFERENCES "activity"("id_activity"),
  "feeding" TEXT,
  "bathroom" TEXT
);

CREATE TABLE "race" (
  "id_race" SERIAL PRIMARY KEY NOT NULL,
  "id_careful" INTEGER NOT NULL REFERENCES "careful"("id_careful"),
  "name" VARCHAR(20) NOT NULL,
  "description" TEXT
);

CREATE TABLE "animal" (
  "id_animal" SERIAL PRIMARY KEY NOT NULL,
  "id_type" INTEGER NOT NULL REFERENCES "type_animal"("id_type"),
  "id_race" INTEGER NOT NULL REFERENCES "race"("id_race"),
  "id_vaccine" INTEGER REFERENCES "vaccine"("id_vaccine"),
  "id_location" INTEGER REFERENCES "location"("id_location"),
  "id_owner" INTEGER NOT NULL REFERENCES "user"("id_user"),
  "name" VARCHAR NOT NULL,
  "weight" VARCHAR NOT NULL,
  "size" VARCHAR NOT NULL,
  "gender" CHAR NOT NULL,
  "img" VARCHAR,
  "birthdate" DATE NOT NULL,
  "status" BOOLEAN
);

CREATE TABLE "adoption_post" (
  "id_adoption_post" SERIAL PRIMARY KEY NOT NULL,
  "title" VARCHAR,
  "body" TEXT,
  "id_user" INTEGER REFERENCES "user"("id_user"),
  "status" BOOLEAN,
  "created_at" VARCHAR,
  "img" VARCHAR
);


-- Llenando la tabla user
INSERT INTO "user" ("user_name", "name", "birthdate", "mail", "password", "img_profile", "gender", "status", "phone", "codigo_login")
VALUES
('jose123', 'José Pérez', '1990-01-01', 'jose@example.com', '1234', 'perfil1.jpg', 'M', TRUE, '5551234567', 'AB12'),
('maria456', 'María Gómez', '1985-05-15', 'maria@example.com', '5678', 'perfil2.jpg', 'F', TRUE, '5559876543', 'CD34'),
('luis789', 'Luis Martínez', '1978-08-20', 'luis@example.com', '9012', 'perfil3.jpg', 'M', FALSE, '5551230987', 'EF56');

-- Llenando la tabla type_animal
INSERT INTO "type_animal" ("name")
VALUES
('Perro'),
('Gato'),
('Ave');

-- Llenando la tabla location
INSERT INTO "location" ("coordenate", "route")
VALUES
('19.432608, -99.133209', 'Ciudad de México'),
('40.712776, -74.005974', 'Nueva York');

-- Llenando la tabla vaccine
INSERT INTO "vaccine" ("name", "created_at", "description", "status")
VALUES
('Vacuna Rabia', '2022-01-01', 'Vacuna contra la rabia', TRUE),
('Vacuna Parvovirus', '2023-01-01', 'Vacuna contra el parvovirus canino', TRUE);

-- Llenando la tabla activity
INSERT INTO "activity" ("duration_time", "implements")
VALUES
('30 minutos', 'Juguetes, correa'),
('1 hora', 'Bicicleta, pelota');

-- Llenando la tabla careful
INSERT INTO "careful" ("id_activity", "feeding", "bathroom")
VALUES
(1, 'Alimentación regular dos veces al día', 'Baño una vez por semana'),
(2, 'Alimentación especializada', 'Baño una vez al mes');

-- Llenando la tabla race
INSERT INTO "race" ("id_careful", "name", "description")
VALUES
(1, 'Labrador Retriever', 'Raza amigable y enérgica, ideal para familias'),
(2, 'Siamés', 'Raza de gato conocida por su belleza y carácter independiente');

-- Llenando la tabla animal
INSERT INTO "animal" ("id_type", "id_race", "id_vaccine", "id_location", "id_owner", "name", "weight", "size", "gender", "img", "birthdate", "status")
VALUES
(1, 1, 1, 1, 1, 'Fido', '30 kg', 'Grande', 'M', 'fido.jpg', '2018-06-15', TRUE),
(2, 2, 2, 2, 2, 'Mimi', '5 kg', 'Pequeño', 'F', 'mimi.jpg', '2020-09-10', TRUE);

-- Llenando la tabla adoption_post
INSERT INTO "adoption_post" ("title", "body", "id_user", "status", "created_at", "img")
VALUES
('Adopción de Fido', 'Fido es un perro amigable y juguetón en busca de un hogar.', 1, TRUE, '2023-05-18', 'fido_adopcion.jpg'),
('Adopción de Mimi', 'Mimi es una gata cariñosa y tranquila en busca de una familia.', 2, TRUE, '2023-06-01', 'mimi_adopcion.jpg');

-- Seleccionar todos los usuarios
SELECT * FROM "user";

-- Seleccionar todos los tipos de animales
SELECT * FROM "type_animal";

-- Seleccionar todas las ubicaciones
SELECT * FROM "location";

-- Seleccionar todas las vacunas
SELECT * FROM "vaccine";

-- Seleccionar todas las actividades
SELECT * FROM "activity";

-- Seleccionar todos los cuidados
SELECT * FROM "careful";

-- Seleccionar todas las razas
SELECT * FROM "race";

-- Seleccionar todos los animales
SELECT * FROM "animal";

-- Seleccionar todas las publicaciones de adopción
SELECT * FROM "adoption_post";
