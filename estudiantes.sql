CREATE DATABASE ALUMNOS;
USE ALUMNOS;

CREATE TABLE IF NOT EXISTS estudiante
(
	id numeric(5) not null,
    nombre varchar(50) not null,
    celular varchar(7) not null, 
    correo varchar(30) not null,
    carrera varchar(20) not null,
    genero varchar(1)
);

INSERT INTO estudiante(id, nombre, celular, correo, carrera,genero)
VALUES (12345,'JULIE','3698740','EPN.EDU.EC','MECÁNICA','F'),
	   (12346,'MARCO','3698741','EPN.EDU.EC','MECÁNICA','M');
       
SELECT * FROM ESTUDIANTE