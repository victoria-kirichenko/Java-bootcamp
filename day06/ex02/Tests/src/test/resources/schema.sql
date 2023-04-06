drop table if exists product;

create table product (
id int primary key,
name varchar(256) not null,
price integer not null check (price > 0)
);