INSERT INTO chat.users (login, passwd) VALUES ('rosfryd', '123456');
INSERT INTO chat.users (login, passwd) VALUES ('elevante', '45345');
INSERT INTO chat.users (login, passwd) VALUES ('mihdev', '343');
INSERT INTO chat.users (login, passwd) VALUES ('meunostu', '44');
INSERT INTO chat.users (login, passwd) VALUES ('mcarry', '123456453');
INSERT INTO chat.users (login, passwd) VALUES ('login', 'login');

INSERT INTO chat.room (chat_owner, chat_name) VALUES (1, 'chat1');
INSERT INTO chat.room (chat_owner, chat_name) VALUES (2, 'chat2');
INSERT INTO chat.room (chat_owner, chat_name) VALUES (3, 'chat3');
INSERT INTO chat.room (chat_owner, chat_name) VALUES (4, 'chat4');
INSERT INTO chat.room (chat_owner, chat_name) VALUES (5, 'chat5');

INSERT INTO chat.messages (room_id, sender, message, time) VALUES (1, 2, 'hello friends', to_timestamp('2017     Aug','YYYY MON'));
INSERT INTO chat.messages (room_id, sender, message, time) VALUES (3, 2, 'hello friends', to_timestamp('2017     Aug','YYYY MON'));
INSERT INTO chat.messages (room_id, sender, message, time) VALUES (4, 2, 'hello friends', to_timestamp('2017     Aug','YYYY MON'));
INSERT INTO chat.messages (room_id, sender, message, time) VALUES (1, 2, 'hello friends', to_timestamp('2017     Aug','YYYY MON'));
INSERT INTO chat.messages (room_id, sender, message, time) VALUES (1, 3, 'hello friends', to_timestamp('2017     Aug','YYYY MON'));
INSERT INTO chat.messages (room_id, sender, message, time) VALUES (2, 3, 'hello friends', to_timestamp('2017     Aug','YYYY MON'));

INSERT INTO Chat.users_in_rooms SELECT Chat.room.id, Chat.room.chat_owner FROM Chat.room;