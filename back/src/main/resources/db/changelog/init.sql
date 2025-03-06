--liquibase formatted sql

--changeset Mohamdi:init/1
CREATE SEQUENCE hibernate_sequence START 1000 INCREMENT 1;

--changeset Mohamdi:init/product
CREATE TABLE product(
    id bigint primary key NOT NULL,
    code varchar(255) UNIQUE NOT NULL,
    name varchar(255) NOT NULL,
    description TEXT,
    image TEXT,
    category varchar(255) NOT NULL,
    price decimal(19,2) NOT NULL,
    quantity int NOT NULL,
    internal_reference varchar(255),
    shell_id int,
    inventory_status varchar(255),
    rating decimal(19,1) NOT NULL,
    created_at timestamp,
    updated_at timestamp
);
