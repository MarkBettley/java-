-- ============================================
-- ACTIVIDAD: JOINS EN BASES DE DATOS
-- ============================================

CREATE DATABASE relaciones_db;

USE relaciones_db;

CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL
);

CREATE TABLE telefonos (
    id_telefono INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE direcciones (
    id_direccion INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    direccion VARCHAR(200) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- ============================================
-- REGISTROS
-- ============================================

INSERT INTO usuarios (nombre, correo)
VALUES
('Marco Hernandez', 'marco@email.com'),
('Ana Lopez', 'ana@email.com'),
('Carlos Ramirez', 'carlos@email.com');

INSERT INTO telefonos (id_usuario, telefono)
VALUES
(1, '7711234567'),
(2, '7712345678'),
(NULL, '5559999999');

INSERT INTO direcciones (id_usuario, direccion, ciudad)
VALUES
(1, 'Avenida Universidad 100', 'Pachuca'),
(3, 'Avenida Reforma 250', 'Ciudad de Mexico'),
(NULL, 'Calle Independencia 50', 'Puebla');

-- ============================================
-- INNER JOIN
-- ============================================

SELECT
    usuarios.nombre,
    telefonos.telefono
FROM usuarios
INNER JOIN telefonos
    ON usuarios.id_usuario = telefonos.id_usuario;

-- ============================================
-- LEFT JOIN
-- ============================================

SELECT
    usuarios.nombre,
    telefonos.telefono
FROM usuarios
LEFT JOIN telefonos
    ON usuarios.id_usuario = telefonos.id_usuario;

-- ============================================
-- RIGHT JOIN
-- ============================================

SELECT
    usuarios.nombre,
    direcciones.direccion,
    direcciones.ciudad
FROM usuarios
RIGHT JOIN direcciones
    ON usuarios.id_usuario = direcciones.id_usuario;

-- ============================================
-- FULL JOIN
-- MySQL no implementa FULL OUTER JOIN directamente.
-- Se obtiene combinando LEFT JOIN y RIGHT JOIN.
-- ============================================

SELECT
    usuarios.id_usuario,
    usuarios.nombre,
    telefonos.telefono
FROM usuarios
LEFT JOIN telefonos
    ON usuarios.id_usuario = telefonos.id_usuario

UNION

SELECT
    usuarios.id_usuario,
    usuarios.nombre,
    telefonos.telefono
FROM usuarios
RIGHT JOIN telefonos
    ON usuarios.id_usuario = telefonos.id_usuario;
