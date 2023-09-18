drop table if exists foods;
CREATE TABLE foods(
id INT Primary key auto_increment,
name VARCHAR(50),
protein DECIMAL(3,1),
energy DECIMAL(4,1),
carbs DECIMAL(3,1),
fat DECIMAL(3,1),
digestibility DECIMAL(3,2),
isoleucine INT,
leucine INT,
lysine INT,
methionine INT,
cysteine INT,
phenylalanine INT,
tyrosine INT,
threonine INT,
tryptophan INT,
valine INT,
histidine INT
)