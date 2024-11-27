CREATE TABLE IF NOT EXISTS configs(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    user_id BIGINT NOT NULL ,
    reg_exp VARCHAR(255) NOT NULL,
    message VARCHAR(255),
    active BOOLEAN
);