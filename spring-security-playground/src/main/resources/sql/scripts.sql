create database svb_bank;

use svb_bank;

CREATE TABLE `users` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(45) NOT NULL,
`password` VARCHAR(45) NOT NULL,
`enabled` INT NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE `authorities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`id`));
  
  
INSERT IGNORE INTO `users` VALUES (NULL, 'tom', '12345', '1');
INSERT IGNORE INTO `authorities` VALUES (NULL, 'tom', 'write');

select * from users;

select * from authorities;

CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);


  
  INSERT INTO `customer` (`email`, `pwd`, `role`)
 VALUES ('john.doe@example.com', '54321', 'admin');
 
 drop table customer;
 
 CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `customer` (`email`, `pwd`, `role`)
 VALUES ('john.doe@example.com', '$2a$12$N9OkG7sYh2Jgwheoc9USw.j6PpTtGF42ufyfANfTJ1UBkq/iiLTGu', 'admin');
 
 

 https://bcrypt-generator.com/
 
 -- CHanges for Authentication and Authorization -----
 
 Drop table customer;

CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `pwd` varchar(500) NOT NULL,
  `role` varchar(100) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
);

INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`)
 VALUES ('Tom','tom@example.com','9876548337', '$2a$12$IjYMmyZ3/cPo3MeKt8ZdMOeKk8Qz0TRE9gcTWofXkAXBMbFTgQxVm', 'admin',CURDATE());


INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`)
 VALUES ('Alex','alex@example.com','9876548337', '$2a$12$VQnUCqzQrEcDLFydVBuiyOFUnvG8Ki7eezX3UUS9aXiWCA1P5B5G2', 'admin',CURDATE());


INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`)
 VALUES ('Mike','mike@example.com','9876548337', '$2a$12$fi9lklOvkTitvJpQ8HJSSeXu/PEhiF.iFXBzeY4Npi/JMfu1n8w5e', 'admin',CURDATE());

drop table authorities;

CREATE TABLE `authorities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
);

INSERT INTO `authorities` (`customer_id`, `name`)
 VALUES (1, 'READ');
 
INSERT INTO `authorities` (`customer_id`, `name`)
 VALUES (1, 'WRITE');

  
  

