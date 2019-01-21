CREATE TABLE `jobinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` varchar(20) DEFAULT NULL,
  `company_name` varchar(45) DEFAULT NULL,
  `job_title` varchar(45) DEFAULT NULL,
  `job_url` varchar(45) DEFAULT NULL,
  `create_date` varchar(10) DEFAULT NULL,
  `create_time` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
