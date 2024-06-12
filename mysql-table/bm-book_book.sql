CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(128) DEFAULT NULL,
  `author` varchar(128) DEFAULT NULL,
  `book_no` varchar(128) DEFAULT NULL,
  `publisher` varchar(128) DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `durability` int DEFAULT NULL,
  `storage_time` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  `book_label` varchar(128) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(128) DEFAULT NULL,
  `get_score` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb3;
