-- ============================================
-- ACTIVIDAD: FUNDAMENTOS DE BASES DE DATOS
-- ============================================

-- ============================================
-- I. DDL - DATA DEFINITION LANGUAGE
-- ============================================

CREATE DATABASE tienda;

USE tienda;

CREATE TABLE clientes (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) UNIQUE NOT NULL,
    telefono VARCHAR(20)
);

CREATE TABLE productos (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE pedidos (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

-- Ejemplo adicional de DDL
ALTER TABLE clientes
ADD COLUMN ciudad VARCHAR(100);

-- ============================================
-- II. DML - DATA MANIPULATION LANGUAGE
-- ============================================

INSERT INTO clientes (nombre, correo, telefono, ciudad)
VALUES
('Marco Hernandez', 'marco@email.com', '7711234567', 'Pachuca'),
('Ana Lopez', 'ana@email.com', '7712345678', 'Pachuca'),
('Carlos Ramirez', 'carlos@email.com', '5551234567', 'Ciudad de Mexico');

INSERT INTO productos (nombre, precio, stock)
VALUES
('Laptop', 15000.00, 10),
('Teclado', 850.00, 25),
('Mouse', 450.00, 40),
('Monitor', 4500.00, 15);

INSERT INTO pedidos (id_cliente, id_producto, cantidad, fecha)
VALUES
(1, 1, 1, '2026-09-25'),
(2, 2, 2, '2026-09-25'),
(3, 4, 1, '2026-09-24');

UPDATE productos
SET precio = 900.00
WHERE id_producto = 2;

DELETE FROM productos
WHERE id_producto = 3;

-- ============================================
-- III. DQL - DATA QUERY LANGUAGE
-- ============================================

SELECT * FROM clientes;

SELECT * FROM productos;

SELECT * FROM pedidos;

SELECT nombre, precio
FROM productos
WHERE precio > 1000
ORDER BY precio DESC;

SELECT
    pedidos.id_pedido,
    clientes.nombre AS cliente,
    productos.nombre AS producto,
    pedidos.cantidad,
    pedidos.fecha
FROM pedidos
INNER JOIN clientes
    ON pedidos.id_cliente = clientes.id_cliente
INNER JOIN productos
    ON pedidos.id_producto = productos.id_producto
ORDER BY pedidos.id_pedido;

-- ============================================
-- IV. DCL - DATA CONTROL LANGUAGE
-- ============================================

CREATE USER 'usuario_tienda'@'localhost'
IDENTIFIED BY 'Ebac2026!';

GRANT SELECT, INSERT, UPDATE
ON tienda.*
TO 'usuario_tienda'@'localhost';

SHOW GRANTS FOR 'usuario_tienda'@'localhost';

REVOKE INSERT
ON tienda.*
FROM 'usuario_tienda'@'localhost';

SHOW GRANTS FOR 'usuario_tienda'@'localhost';
