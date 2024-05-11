CREATE TABLE user
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    username   TEXT     NOT NULL,
    password   LONGTEXT NOT NULL,
    email      TEXT     NOT NULL,
    avatar     TEXT,
    bio        TEXT,
    first_name TEXT,
    last_name  TEXT,
    permission BIGINT NOT NULL DEFAULT 0
);