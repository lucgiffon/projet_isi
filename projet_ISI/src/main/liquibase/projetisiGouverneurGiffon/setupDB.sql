--liquibase formatted sql

--changeset luc:1 dbms:mysql
CREATE TABLE TmpCoordCountries
(
  code CHAR(3) PRIMARY KEY NOT NULL,
  name VARCHAR(255),
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

--changeset luc:2 dbms:mysql

CREATE VIEW ViewCountries AS
SELECT CC.name, CC.code, CC.longitude, CC.lattitude
FROM TmpCoordCountries CC;

CREATE VIEW ViewMortaliteInfantile AS
SELECT countryCode, date, value 
FROM TmpMortaliteInfantile MI, TmpValues V
WHERE MI.idValue = V.id;

CREATE VIEW ViewChomage AS
SELECT countryCode, date, value 
FROM TmpChomage C, TmpValues V
WHERE C.idValue = V.id;

CREATE VIEW ViewHomicidesIntentionnels AS
SELECT countryCode, date, value 
FROM TmpHomicidesIntentionnels HI, TmpValues V
WHERE HI.idValue = V.id;
