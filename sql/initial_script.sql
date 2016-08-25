
drop schema if exists test;
create schema test;

use test;

drop table if exists employee;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `second_name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `experience` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
