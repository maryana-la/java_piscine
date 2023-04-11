CREATE SCHEMA Chat;

CREATE TABLE Chat.User (
    id int PRIMARY KEY UNIQUE,
    login varchar (50) NOT NULL,
    password varchar (50) NOT NULL,
);

CREATE TABLE Chat.Chatroom (
    id int PRIMARY KEY UNIQUE,
    name varchar (50) NOT NULL,
    owner varchar NOT NULL,
    FOREIGN KEY (owner) REFERENCES Chat.User (login)
);

CREATE TABLE Chat.Message (
    id int PRIMARY KEY UNIQUE,
    author varchar,
    room varchar,
    text text NOT NULL,
    dateTime TIMESTAMP default current_timestamp,
    FOREIGN KEY (author) REFERENCES Chat.User (login),
    FOREIGN KEY (room) REFERENCES Chat.Chatroom (name)
);