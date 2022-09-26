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

  
  

