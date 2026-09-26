CREATE DATABASE IF NOT EXISTS biblioteca_ebac
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE biblioteca_ebac;

CREATE TABLE IF NOT EXISTS authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    biography TEXT,
    UNIQUE KEY uk_author_name (first_name, last_name)
);

CREATE TABLE IF NOT EXISTS libraries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author_id BIGINT NOT NULL,
    library_id BIGINT NOT NULL,
    publication_year INT NOT NULL,
    isbn VARCHAR(30) NOT NULL UNIQUE,
    available BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_book_author
        FOREIGN KEY (author_id)
        REFERENCES authors(id),

    CONSTRAINT fk_book_library
        FOREIGN KEY (library_id)
        REFERENCES libraries(id)
);
