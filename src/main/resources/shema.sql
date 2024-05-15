create schema insurance;

alter schema insurance owner to postgres;


create table if not exists insurance (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    department_id INTEGER NOT NULL,
    type_id INTEGER NOT NULL,
    price INTEGER,
    start_date TIMESTAMP DEFAULT NOW() NOT NULL,
    end_date TIMESTAMP DEFAULT NOW() NOT NULL,
    contract_number VARCHAR(100) UNIQUE
);


CREATE TABLE IF NOT EXISTS department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(100)
);


CREATE TABLE IF NOT EXISTS type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);


ALTER TABLE insurance
    ADD CONSTRAINT fk_department_id FOREIGN KEY (department_id) REFERENCES department (id);

ALTER TABLE insurance
    ADD CONSTRAINT fk_type_id FOREIGN KEY (type_id) REFERENCES type (id);