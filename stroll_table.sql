CREATE DATABASE stroll;
use stroll;
DROP TABLE IF EXISTS place;
CREATE TABLE place(
	no INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    content TEXT(500),
    category char(20),
    writtenDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    address CHAR(80) NOT NULL,
    detailAddress VARCHAR(80),
    x DECIMAL(8,6) NOT NULL,
    y DECIMAL(9,6) NOT NULL
);

CREATE TABLE user(
	id CHAR(20) PRIMARY KEY,
    password CHAR(20) NOT NULL,
    nickname CHAR(20) NOT NULL UNIQUE,
    email CHAR(50) NOT NULL
);

CREATE TABLE reply(
	userId CHAR(20) NOT NULL,
    placeTitle CHAR(100) NOT NULL,
    content TEXT(500),
    star TINYINT
);