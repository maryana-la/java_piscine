DROP SCHEMA IF EXISTS Chat CASCADE;

CREATE SCHEMA IF NOT EXISTS Chat;

DROP TABLE IF EXISTS Chat.User, Chat.Chatroom, Chat.Message;

CREATE TABLE Chat.User (
                           id serial UNIQUE PRIMARY KEY,
                           login varchar (50) UNIQUE,
                           password varchar (50) NOT NULL
);

CREATE TABLE Chat.Chatroom (
                               id serial UNIQUE PRIMARY KEY,
                               name varchar (50) UNIQUE,
                               owner int NOT NULL,
                               FOREIGN KEY (owner) REFERENCES Chat.User(id) ON DELETE CASCADE
);

CREATE TABLE Chat.Message (
                              id serial UNIQUE PRIMARY KEY,
                              room int,
                              author int,
                              text text NOT NULL,
                              dateTime TIMESTAMP default current_timestamp,
                              FOREIGN KEY (author) REFERENCES Chat.User (id),
                              FOREIGN KEY (room) REFERENCES Chat.Chatroom (id)
);