-- Active: 1745458240449@@127.0.0.1@3306@aloha
TRUNCATE TABLE user;

-- 기본 데이터
-- NoOpPasswordEncoder - 암호화 없이 로그인
-- 사용자
-- INSERT INTO user ( username, password, name, email )
-- VALUES ( 'user', '123456', '사용자', 'user@mail.com' );

-- -- 관리자
-- INSERT INTO user ( username, password, name, email )
-- VALUES ( 'admin', '123456', '관리자', 'admin@mail.com' );


-- BCryptPasswordEncoder - 암호화 시
-- 사용자
INSERT INTO user ( username, password, name, email )
VALUES ( 'user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 'user@mail.com' );

-- 관리자
INSERT INTO user ( username, password, name, email )
VALUES ( 'admin', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '관리자', 'admin@mail.com' );


-- 사용자
INSERT INTO user ( username, password, name, email )
VALUES ( 'test', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '테스트', 'user@mail.com' );


TRUNCATE TABLE user_auth;
-- 권한
-- 사용자 
-- * 권한 : ROLE_USER
INSERT INTO user_auth ( username,  auth )
VALUES ( 'user', 'ROLE_USER' );

-- 관리자
-- * 권한 : ROLE_USER, ROLE_ADMIN
INSERT INTO user_auth ( username,  auth )
VALUES ( 'admin', 'ROLE_USER' );

INSERT INTO user_auth ( username,  auth )
VALUES ( 'admin', 'ROLE_ADMIN' );

INSERT INTO user_auth ( username,  auth )
VALUES ( 'test', 'ROLE_USER' );