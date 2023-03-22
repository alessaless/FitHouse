-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: fithouse
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Voto` int NOT NULL,
  `Titolo` varchar(200) DEFAULT NULL,
  `Testo` varchar(10000) DEFAULT NULL,
  `ID_prodotto` varchar(30) NOT NULL,
  `ID_utente` int NOT NULL,
  `Data` date NOT NULL,
  `Nome_utente` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `id_prod_idx` (`ID_prodotto`),
  KEY `id_u_idx` (`ID_utente`),
  CONSTRAINT `id_prodddd` FOREIGN KEY (`ID_prodotto`) REFERENCES `prodotto` (`ID_prod`) ON UPDATE CASCADE,
  CONSTRAINT `id_u` FOREIGN KEY (`ID_utente`) REFERENCES `utente` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES (3,4,'E\' SQUISIT','molto buono','A3',1,'2022-05-31','Mario'),(4,1,'NON LO CONSIGLIO','Sapore davvero orrendo','A3',1,'2022-05-31','Paolo'),(5,5,'TOP','uno dei migliori del sito','A3',1,'2022-05-31','Alessandro'),(6,3,'Prova','Prova','B2',1,'2022-06-22','Mario'),(7,4,'','','A3',1,'2022-06-22','Mario'),(8,4,'Poteva andare peggio','Buono ma non per i miei gusti','B1',4,'2022-06-24','Vincenzo');
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-28 22:39:02
