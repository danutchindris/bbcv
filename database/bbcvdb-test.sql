CREATE DATABASE  IF NOT EXISTS `BBCVDBTEST`;
USE `BBCVDBTEST`;

DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `USER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `FIRSTNAME` varchar(20) DEFAULT NULL,
  `LASTNAME` varchar(20) DEFAULT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL DEFAULT '0',
  `MOTTO` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

LOCK TABLES `USER` WRITE;

INSERT INTO `USER`
VALUES (1,'test.user','$2a$11$UBksT5RSLc3peExxGNMzc.aqGtpzy/6xjA6pR3p1eoW9LwUzAgzpS','Test','User','test.user@testuser.app',1),
(2,'test.user1','$2a$11$UBksT5RSLc3peExxGNMzc.aqGtpzy/6xjA6pR3p1eoW9LwUzAgzpS','Test','User1','test.user1@testuser.app',0);

UNLOCK TABLES;


DROP TABLE IF EXISTS `ARTICLE`;

CREATE TABLE `ARTICLE` (
  `ARTICLE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `STATUS` varchar(20) NOT NULL,
  `DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ARTICLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


LOCK TABLES `ARTICLE` WRITE;

INSERT INTO `ARTICLE` VALUES (1);

UNLOCK TABLES;


DROP TABLE IF EXISTS `ARTICLE_USER`;

CREATE TABLE `ARTICLE_USER` (
  `ARTICLE_ID` int(10) unsigned NOT NULL,
  `USER_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ARTICLE_ID`,`USER_ID`),
  KEY `FK_ARTICLE_USER_ARTICLE_ID_idx` (`ARTICLE_ID`),
  KEY `FK_ARTICLE_USER_USER_ID_idx` (`USER_ID`),
  CONSTRAINT `FK_ARTICLE_USER_ARTICLE_ID` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `ARTICLE` (`ARTICLE_ID`),
  CONSTRAINT `FK_ARTICLE_USER_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `ARTICLE_USER` WRITE;

INSERT INTO `ARTICLE_USER` VALUES (1, 1);

UNLOCK TABLES;


DROP TABLE IF EXISTS `ROLE`;

CREATE TABLE `ROLE` (
  `ROLE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


LOCK TABLES `ROLE` WRITE;

INSERT INTO `ROLE` VALUES (1,'admin'),(2,'user');

UNLOCK TABLES;


DROP TABLE IF EXISTS `USER_ROLE`;

CREATE TABLE `USER_ROLE` (
  `USER_ID` int(10) unsigned NOT NULL,
  `ROLE_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FK_USER_ROLE_USER_ID_idx` (`USER_ID`),
  KEY `FK_USER_ROLE_ROLE_ID_idx` (`ROLE_ID`),
  CONSTRAINT `FK_USER_ROLE_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`USER_ID`),
  CONSTRAINT `FK_USER_ROLE_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLE` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `USER_ROLE` WRITE;

INSERT INTO `USER_ROLE` VALUES (1,1);

UNLOCK TABLES;


DROP TABLE IF EXISTS `PERMISSION`;

CREATE TABLE `PERMISSION` (
  `PERMISSION_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


LOCK TABLES `PERMISSION` WRITE;

INSERT INTO `PERMISSION`
VALUES (1,'permission_admin_create_user'),
(2,'permission_admin_user_list');

UNLOCK TABLES;


DROP TABLE IF EXISTS `ROLE_PERMISSION`;

CREATE TABLE `ROLE_PERMISSION` (
  `ROLE_ID` int(10) unsigned NOT NULL,
  `PERMISSION_ID` int(10) unsigned NOT NULL,
  KEY `FK_ROLE_PERMISSION_ROLE_ID_idx` (`ROLE_ID`),
  KEY `FK_ROLE_PERMISSION_PERMISSION_ID_idx` (`PERMISSION_ID`),
  CONSTRAINT `FK_ROLE_PERMISSION_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLE` (`ROLE_ID`),
  CONSTRAINT `FK_ROLE_PERMISSION_PERMISSION_ID` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `PERMISSION` (`PERMISSION_ID`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `ROLE_PERMISSION` WRITE;

INSERT INTO `ROLE_PERMISSION` VALUES (1,1),(1,2);

UNLOCK TABLES;


DROP TABLE IF EXISTS `LINK`;

CREATE TABLE `LINK` (
  `LINK_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TEXT` varchar(200) DEFAULT NULL,
  `URL` varchar(200) NOT NULL,
  PRIMARY KEY (`LINK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `DICTIONARY`;

CREATE TABLE `DICTIONARY` (
  `DICTIONARY_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `OBJECT_TYPE` varchar(50) NOT NULL,
  `OBJECT_ID` int(10) unsigned NOT NULL,
  `CATEGORY` varchar(50) NOT NULL,
  `EN` varchar(100000) DEFAULT NULL,
  `RO` varchar(100000) DEFAULT NULL,
  PRIMARY KEY (`DICTIONARY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `ROLE_PERMISSION` WRITE;

INSERT INTO `DICTIONARY`
VALUES (1, 'ro.leje.model.entity.ArticleEntity', 1, 'title', 'Existing Article', 'Articol existent');

UNLOCK TABLES;

DROP TABLE IF EXISTS `IMAGE`;

CREATE TABLE `IMAGE` (
  `IMAGE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FILENAME` varchar(50) NOT NULL,
  `ARTICLE_ID` int(10) unsigned NOT NULL,
  `COVER` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IMAGE_ID`),
  CONSTRAINT `FK_IMAGE_ARTICLE_ID` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `ARTICLE` (`ARTICLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
