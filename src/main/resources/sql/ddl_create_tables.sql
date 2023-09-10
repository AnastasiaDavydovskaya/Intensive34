create table if not exists orders
(
    id  bigint auto_increment primary key,
    amount integer not null,
    unit_price decimal(19, 1) not null
);

create table if not exists users
(
    id  bigint auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    patronymic varchar(50) not null,
    phone_number varchar(50) not null,
    email varchar(50) not null,
    order_id bigint,
    constraint order_id_fk foreign key (order_id) references orders (id)
);