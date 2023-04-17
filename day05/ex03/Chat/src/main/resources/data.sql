INSERT INTO chat.user (login, password) VALUES ('rchelsea', 'password') ON CONFLICT DO NOTHING;
INSERT INTO chat.user (login, password) VALUES ('user', 'passwordsafe') ON CONFLICT DO NOTHING;
INSERT INTO chat.user (login, password) VALUES ('student', 'qwerty') ON CONFLICT DO NOTHING;;
INSERT INTO chat.user (login, password) VALUES ('teacher', 'Descs23?@(#') ON CONFLICT DO NOTHING;;
INSERT INTO chat.user (login, password) VALUES ('director', 'BigBoss') ON CONFLICT DO NOTHING;;

INSERT INTO chat.chatroom (name, owner) VALUES ('WorkChat',       (SELECT id FROM chat.user WHERE login = 'rchelsea')) ON CONFLICT DO NOTHING;
INSERT INTO chat.chatroom (name, owner) VALUES ('StudyAtNight',   (SELECT id FROM chat.user WHERE login = 'student')) ON CONFLICT DO NOTHING;
INSERT INTO chat.chatroom (name, owner) VALUES ('Party',          (SELECT id FROM chat.user WHERE login = 'rchelsea')) ON CONFLICT DO NOTHING;
INSERT INTO chat.chatroom (name, owner) VALUES ('Picnic',         (SELECT id FROM chat.user WHERE login = 'rchelsea')) ON CONFLICT DO NOTHING;
INSERT INTO chat.chatroom (name, owner) VALUES ('booksToRead',    (SELECT id FROM chat.user WHERE login = 'user')) ON CONFLICT DO NOTHING;

INSERT INTO chat.message (room, author, text) VALUES ((SELECT id FROM chat.chatroom WHERE name = 'WorkChat'),        (SELECT id FROM chat.user WHERE login = 'user'),        'hello guys!') ON CONFLICT DO NOTHING;
INSERT INTO chat.message (room, author, text) VALUES ((SELECT id FROM chat.chatroom WHERE name = 'WorkChat'),        (SELECT id FROM chat.user WHERE login = 'director'),    'dont see you at your place') ON CONFLICT DO NOTHING;
INSERT INTO chat.message (room, author, text) VALUES ((SELECT id FROM chat.chatroom WHERE name = 'Party'),           (SELECT id FROM chat.user WHERE login = 'user'),        'last night bbq was amazing') ON CONFLICT DO NOTHING;
INSERT INTO chat.message (room, author, text) VALUES ((SELECT id FROM chat.chatroom WHERE name = 'Party'),           (SELECT id FROM chat.user WHERE login = 'rchelsea'),    'so sorry to miss that') ON CONFLICT DO NOTHING;
INSERT INTO chat.message (room, author, text) VALUES ((SELECT id FROM chat.chatroom WHERE name = 'booksToRead'),     (SELECT id FROM chat.user WHERE login = 'user'),        'can you please recommend some new detectives to read') ON CONFLICT DO NOTHING;
INSERT INTO chat.message (room, author, text) VALUES ((SELECT id FROM chat.chatroom WHERE name = 'booksToRead'),     (SELECT id FROM chat.user WHERE login = 'teacher'),     'sent you a list in pm') ON CONFLICT DO NOTHING;
INSERT INTO chat.message (room, author, text) VALUES ((SELECT id FROM chat.chatroom WHERE name = 'StudyAtNight'),    (SELECT id FROM chat.user WHERE login = 'student'),     'exam is already tomorrow! how to learn everything in 1 night?') ON CONFLICT DO NOTHING;
INSERT INTO chat.message (room, author, text) VALUES ((SELECT id FROM chat.chatroom WHERE name = 'StudyAtNight'),    (SELECT id FROM chat.user WHERE login = 'rchelsea'),    'too late, bro') ON CONFLICT DO NOTHING;
INSERT INTO chat.message (room, author, text) VALUES ((SELECT id FROM chat.chatroom WHERE name = 'StudyAtNight'),    (SELECT id FROM chat.user WHERE login = 'user'),        'just learn 1 thing and hope for the best') ON CONFLICT DO NOTHING;
INSERT INTO chat.message (room, author, text) VALUES ((SELECT id FROM chat.chatroom WHERE name = 'Picnic'),          (SELECT id FROM chat.user WHERE login = 'director'),    'what a nice weather today. Anyone for the lunch outside?') ON CONFLICT DO NOTHING;