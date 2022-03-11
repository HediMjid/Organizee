CREATE DATABASE  IF NOT EXISTS `organizee` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `organizee`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: organizee
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `id` int NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) DEFAULT NULL,
  `couleur` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7gyd9s84tx9eeuigeu3uv984x` (`team_id`),
  CONSTRAINT `FK7gyd9s84tx9eeuigeu3uv984x` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES 
(1,'7554 Messerschmidt Center',NULL,'2021-01-24','oogleasane0@cargocollective.com','Ophelia','O\'Gleasane','913-198-6499',1),
(2,'534 Jay Way',NULL,'2021-03-26','fmowett1@ocn.ne.jp','Fiann','Mowett','248-224-7233',1),
(3,'077 Buell Place',NULL,'2021-06-24','vlewknor2@spotify.com','Vladamir','Lewknor','922-822-3626',1),
(4,'6226 Esker Street',NULL,'2021-04-13','jbarmadier3@opensource.org','Jervis','Barmadier','838-581-8112',2),
(5,'28531 Luster Circle',NULL,'2021-06-15','tmee4@ameblo.jp','Tuesday','Mee','761-975-7324',2),
(6,'96 Hallows Avenue',NULL,'2021-08-13','tcolvine5@elegantthemes.com','Toni','Colvine','348-778-7679',2),
(7,'6401 Jay Crossing',NULL,'2021-01-14','rrielly6@netlog.com','Riane','Rielly','740-571-0835',3),
(8,'3273 Cascade Pass',NULL,'2021-03-22','jlauder7@rambler.ru','Juieta','Lauder','928-408-6855',3),
(23,'234 Hollywood boulvard','#ec09c2','1986-10-23','emilia@mail.com','Clarke','Emilia','0102030405',5),
(24,'789 avenue stark','#141010','1986-12-26','kit@mail.com','Harington','Kit','0807050408',5),
(25,'753 Boulevard lanister','#610505','1969-06-11','peter@mail.com','Dinklage','Peter','06080304',5),
(26,'546 rue StarkLanister','#f4ac10','1996-02-21','sophie@mail.com','Turner','Sophie','0800777798',5),
(28,'976 avenue des sans visages','#0ee1d3','1997-02-15','maisie@mail.com','Williams','Maisie','0807406389',5),
(29,'Avenue des dragons','#f19009','1986-10-23','Daenerys@mail.com','Targaryen','Daenerys','0302030203',5),
(30,'Port Réal  ','#44c40e','1969-06-11','Tyrion@mail.com','Lannister  ','Tyrion  ','0304040304  ',5),
(31,'Port Réal','#04af18','1981-05-29','Cersei@mail.com','Lannister','Cersei ','0706080706',5),
(32,'Night\'s Watch','#d21919','1983-01-21','Samwell@mail.com','Tarly!','Samwell','0907070606 ',5);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evenement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `all_day` int NOT NULL,
  `end` datetime DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `membre_id` int DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKka0m0p76kbi2rn5w79c82p7bs` (`membre_id`),
  KEY `FKpkcgf7antdybke0dut2wnahha` (`team_id`),
  CONSTRAINT `FKka0m0p76kbi2rn5w79c82p7bs` FOREIGN KEY (`membre_id`) REFERENCES `membre` (`id`),
  CONSTRAINT `FKpkcgf7antdybke0dut2wnahha` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evenement`
--

LOCK TABLES `evenement` WRITE;
/*!40000 ALTER TABLE `evenement` DISABLE KEYS */;
INSERT INTO `evenement` VALUES 
(1,0,'2022-02-04 13:00:00','2022-02-04 09:00:00','Simplon',1,1),
(2,0,'2022-02-03 13:00:00','2022-02-03 12:00:00','Footing',2,1),
(3,0,'2022-02-22 14:30:00','2022-02-22 11:30:00','reunion de guerre',7,4),
(4,0,'2022-02-21 15:30:00','2022-02-21 11:00:00','Conseil de guerre',9,5),
(5,0,'2022-02-23 17:30:00','2022-02-23 13:00:00','TUER MES ENNEMIS',10,5),
(6,0,'2022-02-27 13:00:00','2022-02-27 11:00:00','JOUER AVEC MON LOUP',10,5),
(7,0,'2022-02-24 15:30:00','2022-02-24 09:00:00','FUIR JOFFREY',11,5),
(8,0,'2022-02-22 14:00:00','2022-02-22 10:00:00','Parler a ma corneille trois yeux',12,5),
(9,0,'2022-03-02 14:30:00','2022-03-02 11:00:00','MONTER LA GARDE SUR LE MUR',9,5),
(15,0,'2022-03-06 18:00:00','2022-03-06 07:30:00','FUIR JEOFFREY',11,5),
(18,0,'2022-03-01 16:00:00','2022-03-01 14:30:00','hodor hodor hodor',13,5),
(22,0,'2022-03-18 16:00:00','2022-03-18 13:00:00','Entrevue avec Daenerys',9,5),
(25,0,'2022-03-17 10:30:00','2022-03-17 09:00:00','voir les sans visages',10,5),
(26,0,'2022-03-14 12:00:00','2022-03-14 10:30:00','detester ma vie',11,5),
(27,0,'2022-03-15 14:00:00','2022-03-15 12:30:00','APPRENDRE A TOMBER DES TOURS',12,5),
(28,0,'2022-03-16 12:00:00','2022-03-16 11:00:00','marcher en foret',14,5),
(29,0,'2022-03-15 14:00:00','2022-03-15 12:00:00','observer les marchers blanc',9,5),
(30,0,'2022-03-14 12:30:00','2022-03-14 11:00:00','faire du cheval',9,5);
/*!40000 ALTER TABLE `evenement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membre`
--

DROP TABLE IF EXISTS `membre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `couleur` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `is_admin` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `smiley` varchar(255) DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKll5mmgkw1h2kmxnuo4885x2fn` (`team_id`),
  CONSTRAINT `FKll5mmgkw1h2kmxnuo4885x2fn` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membre`
--

LOCK TABLES `membre` WRITE;
/*!40000 ALTER TABLE `membre` DISABLE KEYS */;
INSERT INTO `membre` VALUES 
(1,'#fcba03','2021-12-13','hedi@simplon.com','0','SKYWALKER','toto','Hédi',NULL,1),
(2,'#8df505','2021-07-03','aline@simplon.com','0','FETT','tototata','Aline',NULL,1),
(3,'#091ced','2021-01-20','isabelle@simplon.com','0','SOLO','titi','Isabelle',NULL,2),
(4,'#ed09de','2021-06-29','blandine@simplon.com','0','VADER','tutu','Blandine',NULL,3),
(5,'#ed09de','2021-08-29','sana@simplon.com','0','C3PO','riri','Sana',NULL,4),
(6,'#ed09de','2021-10-29','cecile@simplon.com','0','R2D2','loulou','Cecile',NULL,4),
(7,'#801fc1','1948-06-22','kurt@gmail.com',NULL,'Koben','$2a$10$M8f9m2/TXtlMgxX7i6frmOMj21LD.caMjMVSr8y7B7NcJyfK0e31K','Kurt',NULL,4),
(8,'#ae1919','2022-02-10','sana@mail.com',NULL,'BIBI','$2a$10$Oku3b.Ajc/3kq3WzcAWAZeP5o7.Iluk2T3FTnKUkiAnMGsxm0pVAC','Sana',NULL,4),
(9,'#faf9f9','1996-12-26','jon@mail.com',NULL,'SNOW','$2a$10$aN3CTopuggWoUiv1sPDTz.QI8A8x5/5MVUYYGKbPbC32A6zWA5fmm','JON',NULL,5),
(10,'#0feba9','1997-04-15','aria@mail.com',NULL,'STARK','$2a$10$I0TuyVRWxx5PEeMwG0ZXmefQiY.tEaSd2JQ99Ec5Z3J25qCO1Siwm','ARIA',NULL,5),
(11,'#e60a0a','1996-02-21','sansa@mail.com',NULL,'STARK','$2a$10$Pc0pyEVp8yRFb7Rzo6cpW.Ox5VLE8HvWYV485A6sqqT8hshT6zueO','SANSA',NULL,5),
(12,'#16d019','1999-04-09','bran@mail.com',NULL,'STARK','$2a$10$M5lEcoQdaJSDE/81oDciI.pb4reyv.Od7xfAB5Murp55ebejtooSi','BRAN',NULL,5),
(13,'#b11010','2022-02-09','hodor@mail.com',NULL,'HODOR','$2a$10$m1PsJ6gV/hPZMN.QJn0i1etsgMzIzhzk2GcFJiVV24lZWRCCtYa.y','HODOR',NULL,5),
(14,'#e61986','2022-02-18','robb@mail.com',NULL,'STARK','$2a$10$u5yCszTEhwLOIegXVeDJk.h7rXVpyTc1dFaH/3D07JGywhtC0Jkkq','ROBB',NULL,5);
/*!40000 ALTER TABLE `membre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membre_role_list`
--

DROP TABLE IF EXISTS `membre_role_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membre_role_list` (
  `membre_id` int NOT NULL,
  `role_list` varchar(255) DEFAULT NULL,
  KEY `FKls644q5c22xre4wge5aayf5kn` (`membre_id`),
  CONSTRAINT `FKls644q5c22xre4wge5aayf5kn` FOREIGN KEY (`membre_id`) REFERENCES `membre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membre_role_list`
--

LOCK TABLES `membre_role_list` WRITE;
/*!40000 ALTER TABLE `membre_role_list` DISABLE KEYS */;
INSERT INTO `membre_role_list` VALUES (7,'ROLE_PARENT'),(8,'ROLE_PARENT'),(9,'ROLE_PARENT'),(10,'ROLE_ENFANT'),(11,'ROLE_PARENT'),(12,'ROLE_ENFANT'),(13,'ROLE_PARENT'),(14,'ROLE_PARENT');
/*!40000 ALTER TABLE `membre_role_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_menu` date DEFAULT NULL,
  `repas_midi` varchar(255) DEFAULT NULL,
  `repas_soir` varchar(255) DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKky2j5l3syborv9dtqtprgpr28` (`team_id`),
  CONSTRAINT `FKky2j5l3syborv9dtqtprgpr28` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES 
(1,'2022-03-14','tourte au pigeon','sanglier roti aux champignons et aux pommes',5),
(2,'2022-03-15','cheval roti au miel et au poivre ','salade de Mereen mangue et poulet',5),
(3,'2022-03-16',' agneau cuit en croute ail et herbes','saucisse de Theon',5),
(4,'2022-03-17','Brouet a  la mode de Winterfell','fromage et pain dur',5),
(5,'2022-03-18','tarte aux oignons et fromage','civet de lapin aux epices',5),
(6,'2022-03-19','brochet sauce verte','potage au lait et amandes',5),
(7,'2022-03-20','picada de roquette','cygne a  la braise de Tyrion',5);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tache`
--

DROP TABLE IF EXISTS `tache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tache` (
  `id` int NOT NULL AUTO_INCREMENT,
  `etat` bit(1) DEFAULT NULL,
  `texte` varchar(255) DEFAULT NULL,
  `todolist_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK50q0ja9qvoud7ujsudc9jj9yk` (`todolist_id`),
  CONSTRAINT `FK50q0ja9qvoud7ujsudc9jj9yk` FOREIGN KEY (`todolist_id`) REFERENCES `todo_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tache`
--

LOCK TABLES `tache` WRITE;
/*!40000 ALTER TABLE `tache` DISABLE KEYS */;
INSERT INTO `tache` VALUES 
(7,_binary '','organiser la guerre',1),(8,_binary '','apprendre la vie a Samwell',1),
(9,_binary '\0','parler a Igrid',1),(10,_binary '\0','vider l\'écurie',NULL),(11,_binary '\0','nettoyer la salle de banquet',NULL),
(12,_binary '\0','nettoyer les cadavres dans la cour',NULL),(13,_binary '\0','chasser des sangliers',3),(14,_binary '\0','préparer le banquet',3),
(15,_binary '\0','trouver des cadeaux',3),(16,_binary '\0','faire la deco du chateau',3);
/*!40000 ALTER TABLE `tache` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'Team JAVA'),(2,'Team Angular'),(3,'Team PHP'),(4,'Team Bancal'),(5,'GAME OF THRONES');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todo_list`
--

DROP TABLE IF EXISTS `todo_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `todo_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6ty40hkdysbql7xaewhujsjg` (`team_id`),
  CONSTRAINT `FK6ty40hkdysbql7xaewhujsjg` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo_list`
--

LOCK TABLES `todo_list` WRITE;
/*!40000 ALTER TABLE `todo_list` DISABLE KEYS */;
INSERT INTO `todo_list` VALUES (1,'Pour Jon',5),(2,'Corvées',5),(3,'Preparer fete du printemps',5);
/*!40000 ALTER TABLE `todo_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-04 16:48:41
