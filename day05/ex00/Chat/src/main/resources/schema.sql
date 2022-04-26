CREATE SCHEMA IF NOT EXISTS chat00;

DROP TABLE IF EXISTS chat00.messages, chat00.room, chat00.users;

CREATE TABLE IF NOT EXISTS chat00.users (
    id          BIGSERIAL PRIMARY KEY,
    login       VARCHAR(100) NOT NULL ,
    passwd      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS chat00.room (
    id          BIGSERIAL PRIMARY KEY,
    chat_owner  BIGINT NOT NULL REFERENCES chat00.users(id) ,
    chat_name   VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat00.messages (
    id          BIGSERIAL PRIMARY KEY,
    room_id     BIGINT NOT NULL REFERENCES chat00.room(id),
    sender      BIGINT NOT NULL REFERENCES chat00.users(id),
    message     VARCHAR(100) NOT NULL ,
    time        TIMESTAMP NOT NULL DEFAULT NOW()
);