drop table if exists users;
drop table if exists addresses;

create table users
(
    user_id bigint primary key default (nextval('auto_id')),
    first_name varchar(30) not null,
    middle_name varchar(30),
    last_name varchar(30) not null,
    email varchar(64) not null unique,
    tel varchar(15) not null,
    date_registration timestamp default now()
);

create table addresses
(
    address_id bigint primary key default (nextval('address_id')),
    city varchar(30) not null,
    street varchar(30) not null,
    house_num varchar(5) not null,
    apartment_num int,
    user_id bigint references users (user_id) on delete cascade
);






