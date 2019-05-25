CREATE DATABASE  IF NOT EXISTS `cms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `cms`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cms
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `text` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` VALUES (9,'Article 1',' Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vestibulum id dui eu rutrum. Aliquam id lectus vitae lectus pellentesque commodo et sed magna. Morbi suscipit erat id mauris rutrum accumsan. Mauris quis ipsum sit amet mi venenatis fermentum. Phasellus blandit sodales risus nec tincidunt. Vivamus eu placerat leo. Curabitur sed lacus est. Sed hendrerit, metus eu volutpat volutpat, turpis tellus imperdiet sem, non rutrum ligula velit sit amet odio. In sem diam, imperdiet vehicula magna vitae, molestie iaculis dolor.\r\n\r\nCurabitur ut feugiat dui, non varius enim. Nullam a diam pretium, tincidunt dui quis, finibus ante. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam non quam nec mi aliquam mollis. Integer dui odio, hendrerit ut nulla eu, egestas posuere est. Aliquam tempus, sapien et dapibus venenatis, nibh sapien auctor nibh, vel vehicula ex odio id mi. Aliquam venenatis ligula ut tortor ornare, vitae suscipit lorem dapibus. Curabitur eu auctor dolor. Aliquam egestas odio sed urna blandit, vel commodo metus malesuada. Proin cursus, elit sit amet maximus hendrerit, tellus tortor eleifend arcu, ac rutrum nulla nunc eu nulla. Pellentesque euismod aliquam urna, non sodales metus eleifend egestas. Praesent pretium tincidunt nisl id ullamcorper. Cras tincidunt mi sit amet nibh mollis tristique sit amet a ex. '),(10,'Article 2',' Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vestibulum id dui eu rutrum. Aliquam id lectus vitae lectus pellentesque commodo et sed magna. Morbi suscipit erat id mauris rutrum accumsan. Mauris quis ipsum sit amet mi venenatis fermentum. Phasellus blandit sodales risus nec tincidunt. Vivamus eu placerat leo. Curabitur sed lacus est. Sed hendrerit, metus eu volutpat volutpat, turpis tellus imperdiet sem, non rutrum ligula velit sit amet odio. In sem diam, imperdiet vehicula magna vitae, molestie iaculis dolor.\r\n\r\nCurabitur ut feugiat dui, non varius enim. Nullam a diam pretium, tincidunt dui quis, finibus ante. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam non quam nec mi aliquam mollis. Integer dui odio, hendrerit ut nulla eu, egestas posuere est. Aliquam tempus, sapien et dapibus venenatis, nibh sapien auctor nibh, vel vehicula ex odio id mi. Aliquam venenatis ligula ut tortor ornare, vitae suscipit lorem dapibus. Curabitur eu auctor dolor. Aliquam egestas odio sed urna blandit, vel commodo metus malesuada. Proin cursus, elit sit amet maximus hendrerit, tellus tortor eleifend arcu, ac rutrum nulla nunc eu nulla. Pellentesque euismod aliquam urna, non sodales metus eleifend egestas. Praesent pretium tincidunt nisl id ullamcorper. Cras tincidunt mi sit amet nibh mollis tristique sit amet a ex. ');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idArticle` int(11) DEFAULT NULL,
  `text` longtext,
  PRIMARY KEY (`id`),
  KEY `idArticle_idx` (`idArticle`),
  CONSTRAINT `FK_article` FOREIGN KEY (`idArticle`) REFERENCES `articles` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentaire`
--

LOCK TABLES `commentaire` WRITE;
/*!40000 ALTER TABLE `commentaire` DISABLE KEYS */;
INSERT INTO `commentaire` VALUES (9,9,'COmmentaire');
/*!40000 ALTER TABLE `commentaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'jean@jean.jean','jean');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cms'
--

--
-- Dumping routines for database 'cms'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-25 17:45:26
