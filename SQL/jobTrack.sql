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

CREATE TABLE activity (
  id INT NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(50) NULL,
  date_created DATE NULL,
  date_completed DATE NULL,
  activity_status TINYINT NULL,
  activity_deadline DATE NULL,
  activity_detail VARCHAR(400) NULL,
  PRIMARY KEY (id)
 );

CREATE TABLE notes (
  NoteID INT NOT NULL AUTO_INCREMENT,
  NoteDetails VARCHAR(400) NOT NULL,
  PRIMARY KEY (NoteID));
