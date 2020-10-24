DROP DATABASE IF EXISTS lolHi;

CREATE DATABASE lolHi;

USE lolHi;
CREATE TABLE article (
	id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
    memberId INT(10) UNSIGNED NOT NULL,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` CHAR(100) NOT NULL
);

INSERT INTO `article`
SET regDate = NOW(),
updateDate=NOW(),
title='제목1',
`body`='내용1';

INSERT INTO `article`
SET regDate = NOW(),
updateDate=NOW(),
title='제목2',
`body`='내용2';

# 게시물 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(100) NOT NULL,
    `name` CHAR(100) NOT NULL
);