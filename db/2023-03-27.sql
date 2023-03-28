DROP DATABASE IF EXISTS library;
CREATE DATABASE IF NOT EXISTS library;
USE library;

DROP TABLE IF EXISTS 	books,
						authors;

CREATE TABLE authors (
author_id INT 			NOT NULL AUTO_INCREMENT,
first_name 				VARCHAR(50),
last_name 				VARCHAR(50),
country_of_residence	VARCHAR(50),
email_address 			VARCHAR(100),
deleted					BIT DEFAULT(0),
PRIMARY KEY(author_id),
UNIQUE(email_address)
);

CREATE TABLE books (
book_id INT 			NOT NULL AUTO_INCREMENT,
author_id 				INT,
title 					VARCHAR(100),
published_date 			DATE,
publisher 				VARCHAR(50),
ISBN					VARCHAR(100),
deleted					BIT DEFAULT(0),
PRIMARY KEY (book_id),
UNIQUE (ISBN),
FOREIGN KEY (author_id) REFERENCES authors(author_id)
);
