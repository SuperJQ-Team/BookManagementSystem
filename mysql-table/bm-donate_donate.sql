CREATE TABLE `donate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `donor_no` varchar(32) DEFAULT NULL,
  `book_name` varchar(32) DEFAULT NULL,
  `author` varchar(32) DEFAULT NULL,
  `publisher` varchar(32) DEFAULT NULL,
  `publish_time` date DEFAULT NULL,
  `book_label` varchar(128) DEFAULT NULL,
  `get_score` int DEFAULT NULL,
  `donate_time` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  `del_flag` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
SELECT * FROM `bm-book`.book;