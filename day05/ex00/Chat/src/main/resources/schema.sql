DROP SCHEMA IF EXISTS Chat CASCADE;

CREATE SCHEMA IF NOT EXISTS Chat;

DROP TABLE IF EXISTS Chat.User, Chat.Chatroom, Chat.Message;

CREATE TABLE Chat.User (
                           usr_id serial UNIQUE PRIMARY KEY,
                           login varchar (50) UNIQUE,
                           password varchar (50) NOT NULL
--                            CONSTRAINT User_key
);

CREATE TABLE Chat.Chatroom (
                               room_id serial UNIQUE PRIMARY KEY,
                               name varchar (50) UNIQUE,
                               owner int NOT NULL,
                               FOREIGN KEY (owner) REFERENCES Chat.User(usr_id) ON DELETE CASCADE
--                                CONSTRAINT Chatroom_key PRIMARY KEY(room_id)
);

CREATE TABLE Chat.Message (
                              msg_id serial UNIQUE PRIMARY KEY,
                              room int,
                              author int,
                              text text NOT NULL,
                              dateTime TIMESTAMP default current_timestamp,
                              FOREIGN KEY (author) REFERENCES Chat.User (usr_id),
                              FOREIGN KEY (room) REFERENCES Chat.Chatroom (room_id)
--                               CONSTRAINT Message_key PRIMARY KEY(msg_id)
);