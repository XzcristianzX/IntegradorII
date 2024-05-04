CREATE TABLE "user" (
  "id_user" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "user_name" varchar(20) UNIQUE NOT NULL,
  "name" varchar(50) NOT NULL,
  "birthdate" date NOT NULL,
  "mail" varchar(50) UNIQUE NOT NULL,
  "password" varchar(64) NOT NULL,
  "img_profile" varchar,
  "gender" char(1) NOT NULL
);

CREATE TABLE "animal" (
  "id_animal" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "id_type" SERIAL NOT NULL,
  "id_race" SERIAL NOT NULL,
  "id_location" SERIAL NOT NULL,
  "id_owner" SERIAL NOT NULL,
  "name" varchar(50) NOT NULL,
  "weight" varchar(20) NOT NULL,
  "size" varchar(20) NOT NULL,
  "gender" char(1) NOT NULL,
  "img" varchar,
  "birthdate" date NOT NULL
);

CREATE TABLE "type_animal" (
  "id_type" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "name" varchar(20) NOT NULL
);

CREATE TABLE "race" (
  "id_race" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "id_careful" SERIAL NOT NULL,
  "name" varchar(20) NOT NULL,
  "description" text
);

CREATE TABLE "careful" (
  "id_careful" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "id_vaccine" SERIAL NOT NULL,
  "id_activity" SERIAL NOT NULL,
  "feeding" text,
  "bathroom" text
);

CREATE TABLE "location" (
  "id_location" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "id_neighborhood" SERIAL NOT NULL,
  "coordenate" varchar(50),
  "route" varchar(255),
  "created_at" timestamp
);

CREATE TABLE "neighborhood" (
  "id_neighborhood" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "name" varchar(50) UNIQUE NOT NULL
);

CREATE TABLE "donation" (
  "id_donation" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "id_user" SERIAL NOT NULL,
  "amount" money,
  "description" text,
  "created_at" timestamp
);

CREATE TABLE "vaccine" (
  "id_vaccine" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "name" varchar(20),
  "created_at" timestamp,
  "description" text
);

CREATE TABLE "adoption_post" (
  "id_adoption_post" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "title" varchar(255),
  "body" text,
  "id_user" SERIAL NOT NULL,
  "status" varchar(20),
  "created_at" timestamp,
  "img" varchar
);

CREATE TABLE "activity" (
  "id_activity" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "name" varchar,
  "duration_time" varchar(20),
  "implements" text
);

COMMENT ON COLUMN "race"."description" IS 'Descripción breve de la raza del animal';

COMMENT ON COLUMN "careful"."feeding" IS 'El tipo de alimentación';

COMMENT ON COLUMN "careful"."bathroom" IS 'El tipo de baños ';

COMMENT ON COLUMN "donation"."description" IS 'Descripción de la donación';

COMMENT ON COLUMN "vaccine"."description" IS 'Descripción de la vacuna';

COMMENT ON COLUMN "adoption_post"."body" IS 'Contenido del post';




ALTER TABLE "animal" ADD FOREIGN KEY ("id_owner") REFERENCES "user" ("id_user");

ALTER TABLE "animal" ADD FOREIGN KEY ("id_type") REFERENCES "type_animal" ("id_type");

ALTER TABLE "animal" ADD FOREIGN KEY ("id_race") REFERENCES "race" ("id_race");

ALTER TABLE "animal" ADD FOREIGN KEY ("id_location") REFERENCES "location" ("id_location");

ALTER TABLE  "race"  ADD FOREIGN KEY ("id_careful") REFERENCES "careful" ("id_careful");

ALTER TABLE "location" ADD FOREIGN KEY ("id_neighborhood") REFERENCES "neighborhood" ("id_neighborhood");

ALTER TABLE "careful"  ADD FOREIGN KEY  ("id_vaccine") REFERENCES "vaccine" ("id_vaccine") ;

ALTER TABLE "adoption_post" ADD FOREIGN KEY ("id_user") REFERENCES "user" ("id_user");

ALTER TABLE "donation" ADD FOREIGN KEY ("id_user") REFERENCES "user" ("id_user");

ALTER TABLE  "careful"  ADD FOREIGN KEY ("id_activity") REFERENCES "activity" ("id_activity");