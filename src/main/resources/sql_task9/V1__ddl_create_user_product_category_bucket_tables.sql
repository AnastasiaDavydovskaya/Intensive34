create table if not exists category
(
    id    bigint auto_increment primary key,
    title varchar(255)
);

create table if not exists product
(
    id            bigint auto_increment primary key,
    price         decimal,
    title         varchar(255),
    color         varchar(255),
    category_id   bigint,
    constraint category_id_fk foreign key (category_id) references category (id)
);

create table if not exists user
(
    id       bigint auto_increment primary key,
    login    varchar(255),
    password varchar(255),
    role     varchar(255),
    meta JSON
);

create table if not exists bucket
(
    id      bigint auto_increment primary key,
    user_id bigint,
    constraint user_id_fk foreign key (user_id) references user (id)
);

create table if not exists bucket_products
(
    bucket_id  bigint,
    product_id bigint,
    constraint product_id_fk foreign key (product_id) references product (id),
    constraint bucket_id_fk foreign key (bucket_id) references bucket (id)
);