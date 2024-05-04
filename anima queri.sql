ALTER TABLE "user"
ALTER COLUMN img_profile TYPE TEXT;


INSERT INTO "user" (user_name, name, birthdate, mail, password, gender)
VALUES ('prueba1', 'John Doe', '1980-01-01', 'john.doe1@email.com', '1234', 'M'),
       ('janeblair2', 'Jane Blair', '1985-02-14', 'jane.blair1@email.com', 'secretpass', 'F'),
       ('petlover1233', 'Alice Johnson', '1990-03-10', 'alice.johnson1@email.com', 'ilovepets!', 'F');
select * from public.user;


INSERT INTO "type_animal" (name)
VALUES ('Dog'),
       ('Cat'),
       ('Rabbit');
select * from public.type_animal;

INSERT INTO "race" (id_careful, name, description)
VALUES (1, 'Golden Retriever', 'Large, friendly dog breed'),  -- Foreign Key to careful 1 (placeholder for now)
       (1, 'Persian Cat', 'Longhaired, flat-faced cat breed'),  -- Foreign Key to careful 1 (placeholder for now)
       (2, 'Holland Lop', 'Small, docile rabbit breed');       -- Foreign Key to careful 3 (placeholder for now)
select * from public.race;	   

	   
INSERT INTO "vaccine" (name, created_at, description)
VALUES ('Rabies', '2024-03-15', 'Protects against rabies'),
       ('Distemper', '2024-03-15', 'Protects against distemper virus');
select * from public.vaccine;


INSERT INTO "activity" (name, duration_time, implements)
VALUES ('Walking', '30 minutes', 'Leash, collar'),
       ('Playing fetch', '15 minutes', 'Ball');
select * from public.activity;

	   

INSERT INTO "careful" (id_vaccine, id_activity, feeding, bathroom)
VALUES (1, 1, 'Dry kibble, twice a day', 'Daily litter box cleaning'),  -- Foreign Keys are placeholders
       (2, 2, 'Wet food, once a day', 'Sand bath every other day');     -- Foreign Keys are placeholders
select * from public.careful;   


INSERT INTO "neighborhood" (name)
VALUES ('Greenwich'),
       ('Central Park'),
       ('Soho');
select * from public.neighborhood;   
	 
	  
INSERT INTO "location" (id_neighborhood, coordenate, route, created_at)
VALUES (1, '40.7128° N, 74.0059° W', '123 Main St, New York, NY', '2024-03-15'),
       (2, '40.7850° N, 73.9667° W', '456 Central Park Ave, New York, NY', '2024-03-15'),
       (3, '40.7228° N, 74.0000° W', '789 Broadway, New York, NY', '2024-03-15');
select * from public.location; 


INSERT INTO "activity" (name, duration_time, implements)
VALUES ('Walking', '30 minutes', 'Leash, collar'),
       ('Playing fetch', '15 minutes', 'Ball');
select * from public.activity; 


INSERT INTO "vaccine" (name, created_at, description)
VALUES ('Rabies', '2024-03-15', 'Protects against rabies'),
       ('Distemper', '2024-03-15', 'Protects against distemper virus');
select * from public.vaccine; 

	   
INSERT INTO "animal" (id_type, id_race, id_location, id_owner, name, weight, size, gender, birthdate, img)
VALUES (1, 10, 1, 1, 'Buddy', '25 kg', 'Medium', 'M', '2022-12-25', 'image.jpg'),  -- Foreign Keys referencing previous tables
       (2, 11, 2, 2, 'Luna', '5 kg', 'Small', 'F', '2023-01-01', 'image2.jpg'),  -- Foreign Keys referencing previous tables
       (3, 12, 3, 3, 'Snowball', '2 kg', 'Small', 'M', '2023-05-10', 'image3.jpg');  -- Foreign Keys referencing previous tables
select * from public.animal; 




select * from public.animal; 
select * from public.vaccine; 
select * from public.activity; 
select * from public.location; 
select * from public.neighborhood;   
select * from public.careful;   
select * from public.activity;
select * from public.vaccine;
select * from public.race;	   
select * from public.type_animal;
select * from public.user;




CREATE TABLE "user" (
  "id_user" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "user_name" varchar(20) UNIQUE NOT NULL,
  "name" varchar(50) NOT NULL,
  "birthdate" date NOT NULL,
  "mail" varchar(50) UNIQUE NOT NULL,
  "password" varchar(64) NOT NULL,
  "img_profile" varchar,
  "phone" varchar (10),
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