CREATE SCHEMA IF NOT EXISTS chat;

DROP TABLE IF EXISTS chat.messages, chat.room, chat.users;

CREATE TABLE IF NOT EXISTS chat.users (
    id          BIGSERIAL PRIMARY KEY,
    login       VARCHAR(100) NOT NULL ,
    passwd      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS chat.room (
    id          BIGSERIAL PRIMARY KEY,
    chat_owner  BIGINT NOT NULL REFERENCES chat.users(id) ,
    chat_name   VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.messages (
    id          BIGSERIAL PRIMARY KEY,
    room_id     BIGINT NOT NULL REFERENCES chat.room(id),
    sender      BIGINT NOT NULL REFERENCES chat.users(id),
    message     VARCHAR(100) NOT NULL ,
    time        TIMESTAMP NOT NULL DEFAULT NOW()
);