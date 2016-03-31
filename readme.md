# Bienvenue sur le Projet ISI de Sébastien Gouverneur et Luc Giffon!

## Introduction

Le but du projet était de mettre en pratique les enseignements reçus au cours de l'Unité d'Enseignement ISI (Intégration de Systèmes d'Information) à Aix-Marseille universités. C'est à dire construire une application permettant le rassemblement et la visualisation de données issues de sources hétérogènes.


## Sources de données

Les données ont été obtenues sur le site [www.data.gouv.fr](www.data.gouv.fr) et concernent:

1. [Le taux de mortalité infantile (pour 1000 naissances vivantes) de 1960 à 2011](https://www.data.gouv.fr/fr/datasets/taux-de-mortalite-infantile-pour-1-000-naissances-vivantes-de-1960-a-2011-dp/)
2. [Le Chômage total (%de la population) de 1980 à 2010](https://www.data.gouv.fr/fr/datasets/chomage-total-de-la-population-de-1980-a-2010-dp/)
3. [Les Homicides intentionnels (pour 100 000 personnes) de 1995 à 2010](https://www.data.gouv.fr/fr/datasets/homicides-intentionnels-pour-100-000-personnes-de-1995-a-2010-dp/)

D'autre part, nous avons eu besoin des coordonnées des pays concernés pour procéder à l'affichage sur une carte.

Toutes les données ont été récupérées au format CSV et stockées dans le répertoire src/main/resources/sources .


## Installation

### Prérequis

* Git
* Java >= 1.8
* Maven >= 3.0.5
* Serveur MySQL


### Configuration

Le projet nécessite un accès à une base de données pour fonctionner. Tout est mis en place automatiquement mais vous devez indiquer à Maven quelques informations de base pour qu'il puisse fonctionner.

Allez dans le repertoire $HOME/.m2/

`cd $HOME/.m2/`

Creez un fichier settings.xml et ouvrez le avec votre éditeur de texte favori, ajoutez-y le code suivant:


**Attention!! Regardez bien le code et remplacez les champs qui le nécessitent**


	<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
	 					  https://maven.apache.org/xsd/settings-1.0.0.xsd">
	 	<profiles>
	 		<profile>
	 			<id>_idProfile (ex: votre nom)_</id>
				<properties>
	 				<mysql.host>_hôte (ex:localhost)_</mysql.host>
					<mysql.port>_port (ex:3306)_</mysql.port>
	 				<mysql.db>_base de données (ex:projetisiGouverneurGiffon)_</mysql.db>
	 				<mysql.login>_login (ex:root)_</mysql.login>
	 				<mysql.password>_password (ex:**aucun**)_</mysql.password>
	 <!-- Vous pouvez laisser la balise password vide -->
	 			</properties>
	 		</profile>
		</profiles>
	</settings>

### Récupération du projet 

`git clone https://github.com/lucgiffon/projet_isi`

### Installation

**Attention!! Regardez bien le code et remplacez les champs qui le nécessitent**

`mvn clean install assembly:single liquibase:update -PidProfile`

