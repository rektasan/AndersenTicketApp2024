CREATE DATABASE my_ticket_service_db;

CREATE TABLE users (
id VARCHAR(10) PRIMARY KEY UNIQUE,
name VARCHAR(50) NOT NULL,
role VARCHAR(10),
creation_date TIMESTAMP);

CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE tickets (
    id VARCHAR(10) PRIMARY KEY UNIQUE,
    user_id VARCHAR(10) REFERENCES users (id) ON DELETE CASCADE,
    ticket_type ticket_type NOT NULL,
    creation_date TIMESTAMP);