CREATE TABLE JobBoard (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    name VARCHAR(200),
    created_date DATE,
    updated_date DATE
);

CREATE TABLE JobApplication (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(300),
    company VARCHAR(300),
    status_id INT,
    created_date DATE,
    updated_date DATE
);

