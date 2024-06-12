CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '学生名',
  `stu_no` varchar(16) DEFAULT NULL COMMENT '学号',
  `college` varchar(64) DEFAULT NULL COMMENT '学院',
  `major` varchar(64) DEFAULT NULL COMMENT '专业',
  `clazz` varchar(64) DEFAULT NULL COMMENT '班级',
  `gender` int DEFAULT NULL COMMENT '性别',
  `grade` varchar(4) DEFAULT NULL COMMENT '年级',
  `id_no` varchar(21) DEFAULT NULL COMMENT '身份证号',
  `credit_level` varchar(4) DEFAULT NULL COMMENT '信用等级（1、2、3、4..）',
  `is_block` int DEFAULT NULL COMMENT '是否禁用（0 正常   1 禁用）',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `score` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
