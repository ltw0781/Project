-- Active: 1745458240449@@127.0.0.1@3306@aloha
-- user
CREATE TABLE `user` (
  `NO` bigint NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(200) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ENABLED` int DEFAULT 1,
  PRIMARY KEY (`NO`)
) COMMENT='회원';


-- user_auth
CREATE TABLE `user_auth` (
      no bigint NOT NULL AUTO_INCREMENT       -- 권한번호
    , username varchar(100) NOT NULL             -- 아이디
    , auth varchar(100) NOT NULL                -- 권한 (ROLE_USER, ROLE_ADMIN, ...)
    , PRIMARY KEY(no)                      
);


