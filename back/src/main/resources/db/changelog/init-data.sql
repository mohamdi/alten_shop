--liquibase formatted sql

--changeset Mohamdi:init-data/admin_user
insert into app_user(id, firstname, username, email, password, admin)
values (nextval('hibernate_sequence'), 'Admin', 'admin', 'admin@admin.com', '$2a$12$AeSrTCM52YvBnwn50zJGiu4UVKE1VQxuAhKXRN0nmFdJTm11uNLC6', true);
