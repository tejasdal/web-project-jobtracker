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


