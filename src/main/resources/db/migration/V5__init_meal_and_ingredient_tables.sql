drop table if exists meals;
CREATE TABLE meals(
id INT NOT NULL auto_increment,
user_id INT NOT NULL,
date_saved DATE NOT NULL,
caloricity int not null,
PRIMARY KEY (`id`),
CONSTRAINT `meals_fk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
drop table if exists meal_ingredients;
CREATE TABLE meal_ingredients(
id INT NOT NULL auto_increment,
ingredient_id INT NOT NULL,
meal_id INT NOT NULL,
name Varchar(50) NOT NULL,
mass INT not null,
PRIMARY KEY (`id`),
CONSTRAINT `meal_ingredients_fk_1` FOREIGN KEY (`ingredient_id`) REFERENCES `foods` (`id`),
CONSTRAINT `meal_ingredients_fk_2` FOREIGN KEY (`meal_id`) REFERENCES `meals` (`id`)
);