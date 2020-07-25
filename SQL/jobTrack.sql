//DML queries for application database.
CREATE TABLE `jobboard` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `jobapplication` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role` varchar(300) DEFAULT NULL,
  `company` varchar(300) DEFAULT NULL,
  `user_id` varchar(200) DEFAULT NULL,
  `job_board_id` bigint DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE Contact (
    ContactID BIGINT NOT NULL AUTO_INCREMENT,
    ContactName VARCHAR(45) NOT NULL,
    ContactEmail VARCHAR(45) NOT NULL,
    Company VARCHAR(45),
    JobPosition VARCHAR(45),
    UserID VARCHAR(45) NOT NULL,
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
  UserID VARCHAR(50) NOT NULL,
  PRIMARY KEY (NoteID));
