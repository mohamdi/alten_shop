--liquibase formatted sql

--changeset Mohamdi:init/1
CREATE SEQUENCE hibernate_sequence START 1000 INCREMENT 1;
