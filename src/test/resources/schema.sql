CREATE TABLE IF NOT EXISTS authors (
authorId  INT PRIMARY KEY AUTO_INCREMENT,
firstName 				VARCHAR(50),
lastName 				VARCHAR(50),
countryOfResidence	VARCHAR(50),
emailAddress 			VARCHAR(100),
deleted					BIT DEFAULT(0)
);

CREATE TABLE IF NOT EXISTS books (
bookId INT 			NOT NULL AUTO_INCREMENT,
authorId 				INT,
title 					VARCHAR(100),
publishedDate 			DATE,
publisher 				VARCHAR(50),
ISBN					VARCHAR(100),
deleted					BIT DEFAULT(0),
PRIMARY KEY (bookId),
FOREIGN KEY (authorId) REFERENCES authors(authorId)
);
