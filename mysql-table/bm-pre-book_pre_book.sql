CREATE TABLE `pre_book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `person_name` varchar(45) DEFAULT NULL,
  `book_name` varchar(128) DEFAULT NULL,
  `book_time` date DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
