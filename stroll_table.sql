CREATE DATABASE stroll;
use stroll;

DROP TABLE IF EXISTS user;
CREATE TABLE user(
	id CHAR(20) PRIMARY KEY,
    password CHAR(20) NOT NULL,
    nickname CHAR(20) NOT NULL UNIQUE,
    email CHAR(50) NOT NULL
);

DROP TABLE IF EXISTS place;
CREATE TABLE place(
	no INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    content TEXT(500),
    category char(20),
    writtenDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    address CHAR(80) NOT NULL,
    detailAddress VARCHAR(80),
    x double NOT NULL,
    y double NOT NULL,
    userId CHAR(20) NOT NULL,
    CONSTRAINT fk_place_user_id FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE,
    UNIQUE KEY unique_title_address_detailAddress (title, address, detailAddress)
);
CREATE INDEX ix_place_userId ON place(userId);
CREATE INDEX ix_place_address ON place(address);

CREATE TABLE image(
	no INT PRIMARY KEY AUTO_INCREMENT,
    place_no INT,
    image_path varchar(200),
    CONSTRAINT fk_image_place_id FOREIGN KEY (place_no) REFERENCES place(no) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS reply;
CREATE TABLE reply(
	no INT PRIMARY KEY AUTO_INCREMENT,
	userId CHAR(20) NOT NULL,
    placeNo int NOT NULL,
    content TEXT(500),
    star TINYINT,
    writtenDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT fk_reply_user_id FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT fk_reply_place_no FOREIGN KEY (placeNo) REFERENCES place(no) ON DELETE CASCADE
);
CREATE INDEX ix_reply_placeNo ON reply(placeNo);

CREATE TABLE wish(
	no INT PRIMARY KEY AUTO_INCREMENT,
    userId CHAR(20) NOT NULL,
    placeNo int NOT NULL,
    CONSTRAINT fk_wishList_user_id FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT fk_wishList_place_no FOREIGN KEY (placeNo) REFERENCES place(no) ON DELETE CASCADE
);
ALTER TABLE wish ADD UNIQUE (userId, placeNo);
CREATE INDEX ix_wish_userId ON wish(userId);