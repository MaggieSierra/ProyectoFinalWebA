DROP DATABASE IF EXISTS proyecto_tec_mvc;
CREATE DATABASE proyecto_tec_mvc;

USE proyecto_tec_mvc;

-- ------------------------------------------------------
-- ----Estructura para la tabla `rol`----------
-- -------------------------------------------------------

CREATE TABLE rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre_rol VARCHAR(100) NOT NULL,
  
  PRIMARY KEY (id_rol)
);

-- ------------------------------------------------------
-- Estructura para la tabla `departamento`-----
-- -------------------------------------------------------

CREATE TABLE departamento (
  id_departamento INT NOT NULL AUTO_INCREMENT,
  nombre_departamento VARCHAR(255) NOT NULL,
  
  PRIMARY KEY (id_departamento)
);

-- ------------------------------------------------------
-- Estructura para la tabla `turno`
-- -------------------------------------------------------

CREATE TABLE turno (
  id_turno INT NOT NULL AUTO_INCREMENT,
  turno VARCHAR(255) NOT NULL, 			-- (Matutivo, Vespertino)
  
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
);

-- --------------------------------------------------------
-- Estructura para la tabla `usuario_carrera` **asignar profesores a una carrera**
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
-- Estructura para la tabla `jefe_carrera` **asignar jefes de carrera**
-- --------------------------------------------------------
CREATE TABLE jefe_carrera (
    id_jefe_carrera INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_carrera INT NOT NULL,

    PRIMARY KEY (id_jefe_carrera),
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
  id_horario INT NOT NULL AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  id_materia INT NOT NULL,
  periodo VARCHAR(255) NOT NULL,
  grupo VARCHAR(255) NOT NULL,
  num_alumnos INT NOT NULL,
  aula VARCHAR(255) DEFAULT NULL,
  lunes VARCHAR(255) DEFAULT NULL,
  martes VARCHAR(255) DEFAULT NULL,
  miercoles VARCHAR(255) DEFAULT NULL,
  jueves VARCHAR(255) DEFAULT NULL,
  viernes VARCHAR(255) DEFAULT NULL,
  
   PRIMARY KEY (id_horario),
   FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
   FOREIGN KEY (id_materia) REFERENCES materia (id_materia)
);
-- ------------------
-- -inserts-----
-- -------------------
INSERT INTO rol (nombre_rol) 
VALUES ('Administrador'), 
		('Jefe Carrera'), 
		('Docente');

INSERT INTO departamento (nombre_departamento)
VALUES	('CIENCIAS BASICAS'),
		('SISTEMAS Y COMPUTACIÓN'),
		('METAL MECÁNICA'),
		('INGENIERÍA INDUSTRIAL'),
		('CIENCIAS ECONÓMICO ADMINISTRATIVAS');
		
INSERT INTO turno (turno)
VALUES	('MATUTINO'),
		('VESPERTINO');
		
INSERT INTO carrera (id_turno, clave_carrera, nombre)
VALUES 	(1,'ISX','INGENIERÍA EN SISTEMAS COMPUTACIONALES'),
		(1,'GE9','INGENIERÍA EN GESTIÓN EMPRESARIAL'),
		(1,'IIX','INGENIERÍA INDUSTRIAL'),
	   	(1,'LOX','INGENIERÍA EN LOGÍSTICA'),
	   	(2,'ELX','INGENIERÍA ELECTRÓNICA'),
	   	(2,'EMX','INGENIERÍA ELECTROMECÁNICA'),
	   	(2,'MCX','INGENIERÍA MECATRÓNICA'),
	   	(2,'TIX','INGENIERÍA EN TECNOLOGÍAS DE LA INFORMACIÓN Y COMPUTACIÓN');
		
INSERT INTO usuario (id_rol, id_departamento, clave_usuario, prefijo, nombre, primer_apellido, segundo_apellido, usuario, contraseña, correo, telefono, hrs_trabajo)
VALUES (1, 2, 'A01', 'ING.', 'ANDRES', 'DIAZ', 'CARPIO', 'admin01', 'admin1234', 'admin01@leon.tecnm.mx', '4771234567', 0),
		(1, 2, 'A02', 'ING.', 'MARGARITA', 'SIERRA', 'MUÑOZ', 'admin02', 'admin1234', 'admin02@leon.tecnm.mx', '4771234767', 0),
		(2, 2, 'A03', 'ING.', 'ANTONIO', 'AGUILA', 'REYES','aaguila01', '123','antonio.aguila@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A06', 'ING.', 'EFRAIN', 'BERMUDEZ', 'GUADALUPE', 'ebermudez12', '123', 'efrain.bermudez@leon.tecnm.mx', '4771234566', 5),
	  	(3, 2, 'A05', 'ING.', 'JOSÉ FERNANDO', 'HERNÁNDEZ', 'RODRÍGUEZ', 'jfhernandez01', '123', 'josef.hernandez@leon.tecnm.mx', '4771234569', 0),
	  	(3, 2, 'A06', 'ING.', 'MARÍA MINERVA', 'SAUCEDO', 'TORRES ', 'mmsaucedo01', '123', 'mariam.saucedo@leon.tecnm.mx', '4771234567', 0),
	 	(3, 2, 'A07', 'ING.', 'JOSE GERARDO', 'CARPIO', 'FLORES', 'jgcarpio01', '123', 'josec.carpio@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A08', 'ING.', 'LUIS EDUARDO', 'GUTIERREZ', 'AYALA', 'legutierrez01', '123', 'luis.guitierrez@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A09', 'ING.', 'RUTH', 'SAEZ DE NANCLARES', 'RODRIGUEZ', 'rsaez01', '123', 'ruth.saez@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A10', 'ING.', 'DENY', 'MARTINEZ', 'TREJO', 'dmartinez08', '123', 'correo@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A11', 'ING.', 'PAOLA VIRGINIA', 'GALVAN', 'JARAMILLO', 'pvgalvan01', '123', 'correo@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A12', 'ING.', 'JOSE ALEJANDRO', 'RODRIGUEZ', 'RENTERIA', 'jarodriguez01', '123', 'correo@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A13', 'LIC.', 'IRMA DE JESUS', 'RAMIREZ', 'ALVAREZ', 'iramirez01', '123', 'correo@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A14', 'ING.', 'ANA COLUMBA ZURITA', 'MARTINEZ', 'AGUILAR', 'aczmartinez02', '123', 'correo@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A15', 'ING.', 'JOSE ELIAS', 'MARTINEZ', 'ARIAS', 'jemartinez01', '123', 'correo@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A16', 'ING.', 'LAURA PATRICIA', 'GUEVARA', 'RANGEL', 'lpguevara14', '123', 'correo@leon.tecnm.mx', '4771234567', 0),
		(3, 2, 'A17', 'LIC.', 'ROXANA NOEMI', 'MORENO', 'REAL', 'rnmoreno15', '123', 'correo@leon.tecnm.mx', '4771234567', 0);
	
INSERT INTO jefe_carrera (id_usuario, id_carrera)
VALUES (3, 1),(3, 8);

INSERT INTO usuario_carrera (id_usuario, id_carrera)
VALUES  (3,1),(3,8),(4,1),(5,1);

INSERT INTO materia (id_carrera, clave_materia, nombre, semestre, hrs_teoria, hrs_practica, creditos)
VALUES  (1,'ACF0903', 'Álgebra Lineal', 2, 3, 2, 5),
		(1 ,'ACF0901', 'Cálculo Diferencial', 1, 2, 3, 5),
		(1, 'DAD1404', 'Arquitectura de Aplicaciones Empresariales', 9, 2, 3, 5), 
        (1, 'SCD1003', 'Arquitectura de Computadoras', 5, 2, 3, 5),
		(1 ,'DAD1401', 'Bases de Datos Avanzadas', 7, 2, 3, 5);

INSERT INTO horario (id_usuario, id_materia, periodo, grupo, num_alumnos, aula,lunes, martes, miercoles, jueves, viernes)
VALUES (4, 1,'Agosto-Diciembre/2020','A', 25,'D1', '07:00-08:40', '', '07:00-08:40', '', '07:00-07:50');
