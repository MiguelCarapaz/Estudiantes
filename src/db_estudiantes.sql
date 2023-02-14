CREATE DATABASE ALUMNOS;
USE ALUMNOS;

CREATE TABLE IF NOT EXISTS estudiante
(
	id numeric(5) not null,
    nombre varchar(50) not null,
    celular varchar(7) not null, 
    correo varchar(30) not null,
    carrera varchar(20)

);

INSERT INTO estudiante(id, nombre, celular, correo, carrera)
VALUES (12345,'JULIE','3698740','EPN.EDU.EC','MECÁNICA'),
	   (12346,'MARCO','3698741','EPN.EDU.EC','MECÁNICA');
       
SELECT * FROM ESTUDIANTE