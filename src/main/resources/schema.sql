CREATE DATABASE my_ticket_service_db;

CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');
CREATE TYPE status as ENUM ('ACTIVATED', 'DEACTIVATED')

CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY UNIQUE,
    name VARCHAR(255),
    role VARCHAR(255),
    status status,
    creation_date TIMESTAMP);

CREATE TABLE tickets (
    id VARCHAR(255) PRIMARY KEY UNIQUE,
    user_id VARCHAR(255) REFERENCES users (id) ON DELETE CASCADE,
    ticket_type ticket_type,
    creation_date TIMESTAMP);