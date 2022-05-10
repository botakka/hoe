CREATE DATABASE  IF NOT EXISTS `hoe` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hoe`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: hoe
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `ability`
--

DROP TABLE IF EXISTS `ability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ability` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brain` tinyint DEFAULT NULL,
  `description` longtext NOT NULL,
  `name` varchar(255) NOT NULL,
  `power` tinyint DEFAULT NULL,
  `skill` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ability`
--

LOCK TABLES `ability` WRITE;
/*!40000 ALTER TABLE `ability` DISABLE KEYS */;
INSERT INTO `ability` VALUES (1,100,'Valahogy mindig megússza','Találékonyság',10,30),(2,10,'Értelmetlen erőszak','Vérszomj',100,20),(3,80,'Egyedül nem csinál semmit','csapat szellem',50,70);
/*!40000 ALTER TABLE `ability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bard`
--

DROP TABLE IF EXISTS `bard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bard` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `empire_id` bigint DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq8exrao5v4d89vr2kxvng0bw6` (`empire_id`),
  CONSTRAINT `FKq8exrao5v4d89vr2kxvng0bw6` FOREIGN KEY (`empire_id`) REFERENCES `empire` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bard`
--

LOCK TABLES `bard` WRITE;
/*!40000 ALTER TABLE `bard` DISABLE KEYS */;
INSERT INTO `bard` VALUES (1,'Aranytorok',1,'5134448d-4bc2-4054-be9d-0991e2d2f1f5'),(2,'Ezüstnyelv',2,'3ea934dd-d8f8-4e87-975d-7bdbc376f927'),(3,'Érceshang',3,'5134448d-4bc2-4054-be9d-0991e2d2f1f5'),(4,'Duplatüdő',5,'f2608334-e466-4e7c-8806-af9714210edc');
/*!40000 ALTER TABLE `bard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bardcontest`
--

DROP TABLE IF EXISTS `bardcontest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bardcontest` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contest_name` varchar(255) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bardcontest`
--

LOCK TABLES `bardcontest` WRITE;
/*!40000 ALTER TABLE `bardcontest` DISABLE KEYS */;
INSERT INTO `bardcontest` VALUES (3,'microstar',1);
/*!40000 ALTER TABLE `bardcontest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contender`
--

DROP TABLE IF EXISTS `contender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contender` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bard_id` bigint DEFAULT NULL,
  `bard_contest_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdy2f6ldaadp7facb693o9urav` (`bard_contest_id`),
  CONSTRAINT `FKdy2f6ldaadp7facb693o9urav` FOREIGN KEY (`bard_contest_id`) REFERENCES `bardcontest` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contender`
--

LOCK TABLES `contender` WRITE;
/*!40000 ALTER TABLE `contender` DISABLE KEYS */;
INSERT INTO `contender` VALUES (30,1,3),(31,2,3),(32,3,3);
/*!40000 ALTER TABLE `contender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empire`
--

DROP TABLE IF EXISTS `empire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empire` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `protect_id` bigint DEFAULT NULL,
  `bard_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1xbgwt9iqotespx0ee9clknbf` (`protect_id`),
  KEY `FK9lgpclfhkdca42yxcoq0tky77` (`bard_id`),
  CONSTRAINT `FK1xbgwt9iqotespx0ee9clknbf` FOREIGN KEY (`protect_id`) REFERENCES `securityguard` (`id`),
  CONSTRAINT `FK9lgpclfhkdca42yxcoq0tky77` FOREIGN KEY (`bard_id`) REFERENCES `bard` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empire`
--

LOCK TABLES `empire` WRITE;
/*!40000 ALTER TABLE `empire` DISABLE KEYS */;
INSERT INTO `empire` VALUES (1,'I. Birodalom','Botond realm','5134448d-4bc2-4054-be9d-0991e2d2f1f5',1,1),(2,'II. Birodalom','TU2 realm','3ea934dd-d8f8-4e87-975d-7bdbc376f927',2,2),(3,'III. Birodalom','TU3 realm','f2608334-e466-4e7c-8806-af9714210edc',3,3),(5,'IV. Birodalom','TU4 realm','5134448d-4bc2-4054-be9d-0991e2d2f1f5',12,4);
/*!40000 ALTER TABLE `empire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `epicsong`
--

DROP TABLE IF EXISTS `epicsong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epicsong` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `attacker` varchar(255) NOT NULL,
  `protector` varchar(255) NOT NULL,
  `song_name` varchar(255) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `winner` varchar(255) NOT NULL,
  `bard_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9m8itkj9p4n2jle4go4wpm0cp` (`bard_id`),
  CONSTRAINT `FK9m8itkj9p4n2jle4go4wpm0cp` FOREIGN KEY (`bard_id`) REFERENCES `bard` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epicsong`
--

LOCK TABLES `epicsong` WRITE;
/*!40000 ALTER TABLE `epicsong` DISABLE KEYS */;
INSERT INTO `epicsong` VALUES (7,'user2','user1','user1 versus user2','A hős user1 és a gonosz user2 összecsapott és győzött user1.','user1',1),(8,'user3','user2','user2 versus user3','A hős user2 és a gonosz user3 összecsapott és győzött user2.','user2',2),(9,'user1','user4','user4 versus user1','A hős user4 és a gonosz user1 összecsapott és győzött user1.','user1',3);
/*!40000 ALTER TABLE `epicsong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hero`
--

DROP TABLE IF EXISTS `hero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hero` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hero`
--

LOCK TABLES `hero` WRITE;
/*!40000 ALTER TABLE `hero` DISABLE KEYS */;
INSERT INTO `hero` VALUES (1,'Baji Botond','user1','5134448d-4bc2-4054-be9d-0991e2d2f1f5'),(8,'Test User2','user2','3ea934dd-d8f8-4e87-975d-7bdbc376f927'),(9,'Test User3','user3','f2608334-e466-4e7c-8806-af9714210edc'),(10,'Test User4','user4','5134448d-4bc2-4054-be9d-0991e2d2f1f5');
/*!40000 ALTER TABLE `hero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hybrid`
--

DROP TABLE IF EXISTS `hybrid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hybrid` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `percent` tinyint DEFAULT NULL,
  `speciesid` bigint DEFAULT NULL,
  `hero_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3dr7ku4stt50jdcuysaxp7li2` (`hero_id`),
  CONSTRAINT `FK3dr7ku4stt50jdcuysaxp7li2` FOREIGN KEY (`hero_id`) REFERENCES `hero` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hybrid`
--

LOCK TABLES `hybrid` WRITE;
/*!40000 ALTER TABLE `hybrid` DISABLE KEYS */;
INSERT INTO `hybrid` VALUES (1,10,1,1),(2,90,2,1),(9,100,3,8),(10,30,3,9),(11,50,3,10);
/*!40000 ALTER TABLE `hybrid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `securityguard`
--

DROP TABLE IF EXISTS `securityguard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securityguard` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `heroid` bigint DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `starttime` datetime(6) DEFAULT NULL,
  `stoptime` datetime(6) DEFAULT NULL,
  `empire_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1s4g1qkcsg759tmptfjsqxfuf` (`empire_id`),
  CONSTRAINT `FK1s4g1qkcsg759tmptfjsqxfuf` FOREIGN KEY (`empire_id`) REFERENCES `empire` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securityguard`
--

LOCK TABLES `securityguard` WRITE;
/*!40000 ALTER TABLE `securityguard` DISABLE KEYS */;
INSERT INTO `securityguard` VALUES (1,_binary '',1,10,'2022-05-14 13:29:14.886000','2022-06-30 13:29:14.886000',1),(2,_binary '',8,10,'2022-05-14 13:29:14.886000','2022-06-30 13:29:14.886000',2),(3,_binary '',9,30,'2022-05-14 13:29:14.886000','2022-06-30 13:29:14.886000',3),(12,_binary '',10,30,'2022-05-14 13:29:14.886000','2022-06-30 13:29:14.886000',5);
/*!40000 ALTER TABLE `securityguard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `species` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` VALUES (1,'Kicsi vicces lény','Manó'),(2,'Nagy büdös erőszakos lény','Ork'),(3,'Azt hiszi ő a főnök.....','Ember');
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `species_endowments`
--

DROP TABLE IF EXISTS `species_endowments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `species_endowments` (
  `species_id` bigint NOT NULL,
  `endowments_id` bigint NOT NULL,
  KEY `FK4jmeg109v1wi7s88na8hq6gl7` (`endowments_id`),
  KEY `FKqh58nkrqe66cok5qohpmw7lgn` (`species_id`),
  CONSTRAINT `FK4jmeg109v1wi7s88na8hq6gl7` FOREIGN KEY (`endowments_id`) REFERENCES `ability` (`id`),
  CONSTRAINT `FKqh58nkrqe66cok5qohpmw7lgn` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species_endowments`
--

LOCK TABLES `species_endowments` WRITE;
/*!40000 ALTER TABLE `species_endowments` DISABLE KEYS */;
INSERT INTO `species_endowments` VALUES (1,1),(2,2),(3,3),(1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `species_endowments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-14 16:46:22
