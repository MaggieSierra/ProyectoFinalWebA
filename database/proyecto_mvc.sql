DROP DATABASE IF EXISTS proyecto_tec_mvc;
CREATE DATABASE proyecto_tec_mvc;

USE proyecto_tec_mvc;

-- ------------------------------------------------------
------Estructura para la tabla `rol`----------
---------------------------------------------------------

CREATE TABLE rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre_rol VARCHAR(100) NOT NULL,
  
  PRIMARY KEY (id_rol)
);

-- ------------------------------------------------------
--_Estructura para la tabla `departamento`-----
---------------------------------------------------------

CREATE TABLE departamento (
  id_departamento INT NOT NULL AUTO_INCREMENT,
  nombre_departamento VARCHAR(255) NOT NULL,
  
  PRIMARY KEY (id_departamento)
);

-- ------------------------------------------------------
--_Estructura para la tabla `turno`
---------------------------------------------------------

CREATE TABLE turno (
  id_turno INT NOT NULL AUTO_INCREMENT,
  turno VARCHAR(255) NOT NULL, 			--(Matutivo, Vespertino)
  
  PRIMARY KEY (id_turno)
);

-- --------------------------------------------------------
-- Estructura para la tabla `usuario`
-- --------------------------------------------------------

CREATE TABLE usuario(
  id_usuario INT NOT NULL AUTO_INCREMENT,
  id_rol INT NOT NULL,
  id_departamento INT NOT NULL,
  clave_usuario VARCHAR(20) NOT NULL,
  prefijo VARCHAR(20) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  primer_apellido VARCHAR(255) NOT NULL,
  segundo_apellido VARCHAR(255) DEFAULT NULL,
  correo VARCHAR(255) NOT NULL,
  telefono VARCHAR(10) NOT NULL,
  usuario VARCHAR(255) NOT NULL,
  contraseña VARCHAR(255) NOT NULL,
  hrs_trabajo INT NULL DEFAULT 0,
  
  PRIMARY KEY (id_usuario),
  FOREIGN KEY (id_rol) REFERENCES rol (id_rol),
  FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento)
);

-- --------------------------------------------------------
-- Estructura para la tabla `carrera`
-- --------------------------------------------------------
CREATE TABLE carrera (
  id_carrera INT NOT NULL AUTO_INCREMENT,
  id_turno INT NOT NULL,
  clave_carrera VARCHAR(20) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  
  PRIMARY KEY (id_carrera),
  FOREIGN KEY (id_turno) REFERENCES turno (id_turno)
) 

-- --------------------------------------------------------
-- Estructura para la tabla `usuario_carrera`
-- --------------------------------------------------------
CREATE TABLE usuario_carrera (
    id_usuario_carrera INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_carrera INT NOT NULL,

    PRIMARY KEY (id_usuario_carrera),
    FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
    FOREIGN KEY (id_carrera) REFERENCES carrera (id_carrera)
);

-- --------------------------------------------------------
-- Estructura para la tabla `materia`
-- --------------------------------------------------------
CREATE TABLE materia (
  id_materia INT NOT NULL AUTO_INCREMENT,
  id_carrera INT NOT NULL,
  clave_materia VARCHAR(20) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  semestre INT NOT NULL,
  hrs_teoria INT NOT NULL,
  hrs_practica INT NOT NULL,
  creditos INT NOT NULL,
  
  PRIMARY KEY (id_materia),
  FOREIGN KEY (id_carrera) REFERENCES carrera (id_carrera)
);

-- --------------------------------------------------------
-- Estructura para la tabla `horario`
-- --------------------------------------------------------

CREATE TABLE horario (
  id_horario int(11) NOT NULL AUTO_INCREMENT,
  id_usuario int(11) NOT NULL,
  id_materia int(11) NOT NULL,
  periodo VARCHAR(255) NOT NULL,
  grupo VARCHAR(255) NOT NULL,
  num_alumnos int(11) NOT NULL,
  aula VARCHAR(255) DEFAULT NULL,
  lunes VARCHAR(255) DEFAULT NULL,
  martes VARCHAR(255) DEFAULT NULL,
  miercoles VARCHAR(255) DEFAULT NULL,
  jueves VARCHAR(255) DEFAULT NULL,
  viernes VARCHAR(255) DEFAULT NULL,
  
   PRIMARY KEY (id_horario),
   FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
   FOREIGN KEY (id_materia) REFERENCES subject (id_materia)
);
-- ------------------
-- -inserts-----
---------------------
INSERT INTO rol (nombre_rol) 
VALUES ('Administrador'), 
		('Jefe Carrera'), 
		('Docente');

INSERT INTO departamento (departamento)
VALUES	('Ciencias Basicas'),
		('Sistemas y Computación'),
		('Metal Mecánica'),
		('Ingeniería Industrial'),
		('Ciencias Económico Administrativas');
		
INSERT INTO turno (turno)
VALUES	('Matutino'),
		('Vespertino');
