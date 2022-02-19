drop table if exists users;
drop table if exists addresses;
drop table if exists samples;
drop table if exists payments;

drop sequence if exists user_id;
drop sequence if exists address_id;
drop sequence if exists sample_id;
drop sequence if exists payment_id;

create sequence user_id start 1000001 minvalue 1000001;
create sequence address_id start 2000001 minvalue 2000001;
create sequence sample_id start 120001 minvalue 120001;
create sequence payment_id start 200001 minvalue 200001;

create table users
(
    user_id bigint primary key default (nextval('user_id')),
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

create table samples
(
    sample_id bigint primary key default (nextval('sample_id')),
    name varchar(100) not null,
    iban varchar(29) not null,
    okpo varchar(8) not null,
    appointment varchar(160) not null,
    address_id bigint references addresses (address_id) on delete cascade
);

create table payments
(
    payment_id bigint primary key default (nextval('payment_id')),
    sample_id bigint references samples (sample_id) on delete cascade,
    card_number bigint not null,
    sum_payment numeric(9,2) not null,
    status varchar(8) not null default ('New'),
    date_create timestamp(0) default now(),
    date_status timestamp(0) default now()
);