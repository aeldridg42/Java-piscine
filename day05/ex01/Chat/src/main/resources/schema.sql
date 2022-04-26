CREATE SCHEMA IF NOT EXISTS chat01;

DROP TABLE IF EXISTS chat01.messages, chat01.room, chat01.users;

CREATE TABLE IF NOT EXISTS chat01.users (
        id          SERIAL PRIMARY KEY,
        login       VARCHAR(100) NOT NULL ,
        passwd      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS chat01.room (
       id          BIGSERIAL PRIMARY KEY,
       chat_owner  BIGINT NOT NULL REFERENCES chat01.users(id) ,
       chat_name   VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat01.messages (
       id          SERIAL PRIMARY KEY,
       room_id     BIGINT NOT NULL REFERENCES chat01.room(id),
       sender      BIGINT NOT NULL REFERENCES chat01.users(id),
       message     VARCHAR(100) NOT NULL ,
       time        TIMESTAMP NOT NULL DEFAULT NOW()
);