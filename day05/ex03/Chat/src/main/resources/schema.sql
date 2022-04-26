CREATE SCHEMA IF NOT EXISTS chat;

DROP TABLE IF EXISTS chat.messages, chat.room, chat.users;

CREATE TABLE IF NOT EXISTS chat.users (
        id          SERIAL PRIMARY KEY,
        login       VARCHAR(100) NOT NULL ,
        passwd      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS chat.room (
       id          BIGSERIAL PRIMARY KEY,
       chat_owner  BIGINT NOT NULL REFERENCES chat.users(id) ,
       chat_name   VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.messages (
       id          SERIAL PRIMARY KEY,
       room_id     BIGINT REFERENCES chat.room(id),
       sender      BIGINT REFERENCES chat.users(id),
       message     VARCHAR(100) ,
       time        TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS Chat.users_in_rooms (
       room_id int NOT NULL,
       user_id int NOT NULL,

       PRIMARY KEY (room_id, user_id),
       FOREIGN KEY (room_id) REFERENCES Chat.users(id) ON UPDATE CASCADE ON DELETE CASCADE,
       FOREIGN KEY (user_id) REFERENCES Chat.room(id) ON UPDATE CASCADE
);