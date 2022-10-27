drop table if exists requirements;
CREATE TABLE requirements(
id INT Primary key auto_increment,
isoleucine INT,
leucine INT,
lysine INT,
methionine INT,
cysteine INT,
phenylalanine_plus_tyrosine INT,
threonine INT,
tryptophan INT,
valine INT,
histidine INT
);
Insert into requirements (isoleucine, leucine, lysine, methionine, cysteine, phenylalanine_plus_tyrosine, threonine,
tryptophan, valine, histidine) VALUES (20, 39, 30, 10, 4, 25, 15, 4, 26, 10);