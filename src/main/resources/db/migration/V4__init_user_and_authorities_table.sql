drop table if exists users;
CREATE TABLE users(
id INT NOT NULL auto_increment,
email VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`)
);
drop table if exists authorities;
CREATE TABLE authorities(
id INT NOT NULL auto_increment,
name VARCHAR(50) NOT NULL,
customer_id INT NOT NULL,
PRIMARY key (`id`),
CONSTRAINT `authorities_fk_1` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`)
);