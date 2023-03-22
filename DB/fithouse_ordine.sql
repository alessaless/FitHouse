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
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `Num_Ordine` int NOT NULL AUTO_INCREMENT,
  `Data_ordine` date NOT NULL,
  `Num_Carta` varchar(45) NOT NULL,
  `Intestatario_Carta` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Totale` double NOT NULL,
  `Fattura` int NOT NULL,
  `Stato` varchar(45) NOT NULL,
  `ID_indirizzo` int DEFAULT NULL,
  `ID_utente` int DEFAULT NULL,
  PRIMARY KEY (`Num_Ordine`),
  KEY `ID_indirizzo_idx` (`ID_indirizzo`),
  KEY `ID_utente_idx` (`ID_utente`),
  CONSTRAINT `ID_indirizzo` FOREIGN KEY (`ID_indirizzo`) REFERENCES `indirizzo` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `ID_utente` FOREIGN KEY (`ID_utente`) REFERENCES `utente` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (25,'2022-05-19','23','JI','mariocicalese@libero.it',17.24,5,'In elaborazione',7,NULL),(27,'2022-05-19','12','2dw','mariocicalese@libero.it',18.89,6,'In elaborazione',9,NULL),(28,'2022-05-19','23','d','mariocicalese@libero.it',29.99,7,'In elaborazione',10,NULL),(29,'2022-05-19','35235235','cgbv','zoccolaaless@gmail.com',18.89,8,'In elaborazione',11,NULL),(31,'2022-05-19','45648161','Alessandro Zoccola','mario@libero.it',29.99,10,'In elaborazione',12,1),(32,'2022-05-31','45648161','Alessandro Zoccola','mario@libero.it',18.89,11,'In elaborazione',2,1),(33,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',18.89,12,'In elaborazione',13,1),(34,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',18.89,13,'In elaborazione',13,1),(35,'2022-05-31','1234 1231 1231 1231','sium sium','mario@libero.it',37.78,14,'In elaborazione',13,1),(36,'2022-05-31','1234 1231 1231 1231','sium sium','mario@libero.it',29.99,15,'In elaborazione',13,1),(37,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',48.879999999999995,16,'In elaborazione',12,1),(38,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',48.879999999999995,17,'In elaborazione',12,1),(39,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',48.879999999999995,18,'In elaborazione',12,1),(40,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',48.879999999999995,19,'In elaborazione',12,1),(41,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',48.879999999999995,20,'In elaborazione',13,1),(42,'2022-05-31','1234 1231 1231 1231','sium sium','mario@libero.it',29.99,21,'In elaborazione',1,1),(43,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',48.879999999999995,22,'In elaborazione',12,1),(44,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',62.07,23,'In elaborazione',13,1),(45,'2022-05-31','1234 1231 1231 1231','sium sium','mario@libero.it',18.89,24,'In elaborazione',12,1),(46,'2022-05-31','1234 1231 1231 1231','sium sium','mario@libero.it',29.99,25,'In elaborazione',2,1),(47,'2022-05-31','1234 1231 1231 1231','sium sium','mario@libero.it',77.22,26,'In elaborazione',1,1),(48,'2022-05-31','1234 1231 1231 1231','sium sium','mario@libero.it',78.87,27,'In elaborazione',1,1),(49,'2022-05-31','1234 1231 1231 1231','sium sium','mario@libero.it',78.87,28,'In elaborazione',1,1),(50,'2022-05-31','1234 1231 1231 1231','sium sium','mario@libero.it',78.87,29,'In elaborazione',1,1),(51,'2022-05-31','45648161','Alessandro Zoccola','mario@libero.it',67.77,30,'In elaborazione',13,1),(52,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',17.24,31,'In elaborazione',2,1),(53,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',59.98,32,'In elaborazione',2,1),(54,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',107.21,33,'In elaborazione',2,1),(55,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',51.18,34,'In elaborazione',2,1),(56,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',36.129999999999995,35,'In elaborazione',2,1),(57,'2022-05-31','23234354212343212','Mario Cicalese','mario@libero.it',47.23,36,'In elaborazione',2,1),(58,'2022-06-22','1234 1231 1231 1231','sium sium','mario@libero.it',37.78,32,'In elaborazione',13,1),(59,'2022-06-22','1234 1231 1231 1231','sium sium','mario@libero.it',29.99,33,'In elaborazione',1,1),(60,'2022-06-22','1234123412341234','Alessandro Zoccola','tututatata@gmail.com',49.98,34,'In elaborazione',14,3),(61,'2022-06-24','23234354212343212','Mario Cicalese','mario@libero.it',53.37,35,'In elaborazione',2,1),(62,'2022-06-24','1234256987450213','Vincenzo Arnone','vincenzo@gmail.com',29.99,36,'In elaborazione',15,4),(64,'2022-06-24','0202020202020202','khfakfeknfknkn','zoccolaaless@gmail.com',37.78,37,'In elaborazione',23,-1),(65,'2022-06-24','0303030303030303','kznfakfak','zoccolaaless@gmail.com',29.99,38,'In elaborazione',24,NULL),(66,'2022-06-24','0303030303030303','dlknfsnfmsdlkmfsml','zoccolaaless@gmail.com',29.99,39,'In elaborazione',25,NULL),(67,'2022-06-26','23234354212343212','Mario Cicalese','mario@libero.it',29.99,40,'In elaborazione',1,1),(68,'2022-06-26','1234 1231 1231 1231','sium sium','mario@libero.it',29.99,41,'In elaborazione',1,1),(69,'2022-06-26','1234 1231 1231 1231','sium sium','mario@libero.it',18.89,42,'In elaborazione',1,1),(70,'2022-06-26','1000100020002000','Alessandro Zoccola','zoccolaaless@gmail.com',11.24,43,'In elaborazione',26,NULL),(71,'2022-06-27','1000100010001000','Alessandro Zoccola','tututatata@gmail.com',48.879999999999995,44,'In elaborazione',14,3);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
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
