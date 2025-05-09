CREATE DATABASE IF NOT EXISTS studentsDb
DEFAULT CHARACTER SET utf8;
USE studentsDb;

CREATE TABLE IF NOT EXISTS estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR (50),
    apellido VARCHAR (50),
    correo VARCHAR (100),
    estado TINYINT to BOOLEAN
);



-- Inserción --
INSERT INTO product (product_id, name, description, price, stock, estate) VALUES
    (11, 'Camiseta Oversize Blanca', 'Camiseta de corte amplio, ideal para estilo urbano y relajado', 54.90, 60, TRUE);


-- Actualización --
UPDATE product
SET price = 74.90,
    stock = 45
WHERE product_id = 4;
