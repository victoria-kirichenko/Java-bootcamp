drop table if exists users;

create table users (
    id integer primary key generated always as identity,
    email varchar(256) not null,
    password varchar(256) unique not null
);