CREATE DATABASE projetisiGouverneurGiffon;

USE projetisiGouverneurGiffon;

CREATE TABLE TmpCoordCountries
(
  code CHAR(3) PRIMARY KEY NOT NULL,
  countryName VARCHAR(255) NOT NULL,
  longitude FLOAT,
  lattitude FLOAT
);

CREATE TABLE TmpValues
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  date INT NOT NULL,
  value FLOAT
);

CREATE TABLE TmpMortaliteInfantile
(
  countryCode CHAR(3) NOT NULL,
  idValue INT,
  FOREIGN KEY (countryCode) REFERENCES TmpCoordCountries(code),
  FOREIGN KEY (idValue) REFERENCES TmpValues(id)
);

CREATE TABLE TmpChomage
(
  countryCode CHAR(3) NOT NULL,
  idValue INT,
  FOREIGN KEY (countryCode) REFERENCES TmpCoordCountries(code),
  FOREIGN KEY (idValue) REFERENCES TmpValues(id)
);

CREATE TABLE TmpHomicidesIntentionnels
(
  countryCode CHAR(3) NOT NULL,
  idValue INT,
  FOREIGN KEY (countryCode) REFERENCES TmpCoordCountries(code),
  FOREIGN KEY (idValue) REFERENCES TmpValues(id)
);

