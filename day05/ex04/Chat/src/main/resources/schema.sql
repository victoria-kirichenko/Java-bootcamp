create schema chat;

create table if not exists chat."User" (
	id serial primary key,
	login text unique not null,
	password text not null
);

create table if not exists chat."ChatRoom" (
	id serial primary key,
	name text not null,
	owner int references chat."User"(id)
);

create table if not exists chat."Message" (
	id serial primary key,
	chat_id int references chat."ChatRoom"(id),
	author int references chat."User"(id),
	message_text text not null,
	time_date timestamp without time zone not null
);