-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: appweb
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evenement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evenement`
--

LOCK TABLES `evenement` WRITE;
/*!40000 ALTER TABLE `evenement` DISABLE KEYS */;
INSERT INTO `evenement` VALUES (1,'2025-04-08','2025-04-10','Ce concour permet de selectionner la plus belle femme africaine','concour de miss Afrique'),(2,'2025-04-08','2025-04-10','Ce concour permet de selectionner la plus belle femme africaine','concour de miss Afrique'),(3,'2025-04-08','2025-04-10','Ce concour permet de selectionner la plus belle femme africaine','concour de miss Afrique'),(4,'2025-04-08','2025-04-10','ddd','Concour de Design'),(5,'2025-04-08','2025-04-10','ddd','Concour de Design'),(6,'2025-04-08','2025-04-09','skjdfnvl','qsdfv'),(7,'2025-04-09','2025-04-11','C\'est pour determiner le meilleures joueurs ','Concour football'),(8,'2025-04-09','2025-04-11','C\'est pour determiner le meilleures joueurs ','Concour football'),(9,'2025-04-09','2025-04-11','C\'est pour determiner le meilleures joueurs ','Concour football'),(10,'2025-04-09','2025-04-11','C\'est pour determiner le meilleures joueurs ','Concour football'),(11,'2025-04-09','2025-04-11','C\'est pour determiner le meilleures joueurs ','Concour football'),(12,'2025-04-09','2025-04-11','C\'est pour determiner le meilleures joueurs ','Concour football'),(13,'2025-04-09','2025-04-11','gggggggggg','Concour football'),(14,'2025-04-09','2025-04-11','gggggggggg','Concour football'),(15,'2025-04-09','2025-04-11','gggggggggg','Concour football'),(16,'2025-04-09','2025-04-11','gggggggggg','Concour football'),(17,'2025-04-09','2025-04-11','gggggggggg','Concour football'),(18,'2025-04-09','2025-04-11','gggggggggg','Concour football'),(19,'2025-04-09','2025-04-11','gggggggggg','Concour football'),(20,'2025-04-09','2025-04-11','gggggggggg','Concour football'),(21,'2025-04-09','2025-04-11','a','a'),(22,'2025-04-09','2025-04-11','a','a'),(23,'2025-04-09','2025-04-11','a','a'),(24,'2025-04-09','2025-04-11','a','a'),(25,'2025-04-09','2025-04-11','a','a'),(26,'2025-04-09','2025-04-11','a','a'),(27,'2025-04-09','2025-04-11','a','a'),(28,'2025-04-09','2025-04-11','a','a'),(29,'2025-04-10','2025-04-12','r','e'),(30,'2025-04-10','2025-04-12','r','e'),(31,'2025-04-10','2025-04-12','r','e'),(32,'2025-04-10','2025-04-12','r','e'),(33,'2025-04-10','2025-04-12','r','e'),(34,'2025-04-10','2025-04-12','r','e'),(35,'2025-04-10','2025-04-12','r','e'),(36,'2025-04-10','2025-04-12','r','e'),(37,'2025-04-10','2025-04-12','r','e'),(38,'2025-04-10','2025-04-12','r','e'),(39,'2025-04-10','2025-04-12','A','A'),(40,'2025-04-10','2025-04-12','r','e'),(41,'2025-04-10','2025-04-12','t','t'),(42,'2025-04-10','2025-04-12','t','t'),(43,'2025-04-11','2025-04-13','r','r'),(44,'2025-04-11','2025-04-13','t\r\n','t'),(45,'2025-04-11','2025-04-13','t\r\n','t'),(46,'2025-04-11','2025-04-13','t\r\n','t'),(47,'2025-04-11','2025-04-13','r','r'),(48,'2025-04-14','2025-04-16','t','Tt'),(49,'2025-04-14','2025-04-16','t','Tt'),(50,'2025-04-15','2025-04-17','Une party exceptionnelle avec des guests comme Neymar JR, Cristiano Ronaldo et Lebron James.','Iyad Party'),(51,'2025-04-14','2025-04-16','r','r'),(52,'2025-04-14','2025-04-17','Nul','CONCOURS DU MEILLEUR DES NULS'),(53,'2025-04-14','2025-04-17','BAR CO LA','BARCOLA EVENT'),(54,'2025-04-14','2025-04-16','r','r'),(55,'2025-04-15','2025-05-15','xcdfceatydguiddpf^lpjcoj jvjpf^vmf$^vf','test 1'),(56,'2025-04-16','2025-05-16','SQESFDHJPPJJKGYFGGFFML','test 1');
/*!40000 ALTER TABLE `evenement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-18 16:01:31
