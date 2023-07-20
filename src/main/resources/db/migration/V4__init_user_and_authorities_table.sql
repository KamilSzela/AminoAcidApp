drop table if exists users;
CREATE TABLE users(
id INT NOT NULL auto_increment,
email VARCHAR(50) NOT NULL,
password VARCHAR(200) NOT NULL,
PRIMARY KEY (`id`)
);
drop table if exists authorities;
CREATE TABLE authorities(
id INT NOT NULL auto_increment,
name VARCHAR(50) NOT NULL,
user_id INT NOT NULL,
PRIMARY key (`id`),
CONSTRAINT `authorities_fk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
INSERT into `users` (`email`, `password`) VALUES ('admin@ad.pl', '$2a$12$p0WCQND/fHKZjIYrdxshnO5Lit9zMi37Rc/OdmNSfY3JerhtoLDbq');
INSERT into `users` (`email`, `password`) VALUES ('user@us.pl', '$2a$12$p0WCQND/fHKZjIYrdxshnO5Lit9zMi37Rc/OdmNSfY3JerhtoLDbq');
INSERT into `authorities` (`name`, `user_id`) VALUES ('ROLE_ADMIN', 1);
INSERT into `authorities` (`name`, `user_id`) VALUES ('ROLE_USER', 2);