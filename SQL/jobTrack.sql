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

CREATE TABLE Contact (
    ContactID BIGINT NOT NULL AUTO_INCREMENT,
    ContactName VARCHAR(45) NOT NULL,
    ContactEmail VARCHAR(45) NOT NULL,
    Company VARCHAR(45),
    JobPosition VARCHAR(45),
    PRIMARY KEY (ContactID)
 );
