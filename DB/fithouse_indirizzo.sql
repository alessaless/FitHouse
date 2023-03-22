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
-- Table structure for table `indirizzo`
--

DROP TABLE IF EXISTS `indirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzo` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Telefono` varchar(15) NOT NULL,
  `Citta` varchar(45) NOT NULL,
  `Provincia` varchar(45) NOT NULL,
  `Via` varchar(45) NOT NULL,
  `CAP` int NOT NULL,
  `Nazione` varchar(45) NOT NULL,
  `Note` varchar(100) DEFAULT NULL,
  `ID_utente` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_utente_idx` (`ID_utente`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzo`
--

LOCK TABLES `indirizzo` WRITE;
/*!40000 ALTER TABLE `indirizzo` DISABLE KEYS */;
INSERT INTO `indirizzo` VALUES (1,'Mario','Cicalese','3271584756','Nocera Inferiore','Salerno','Via Garibaldi 24',84014,'Italia','Seconda Casa a destra',1),(2,'Paolo','Cicalese','3365481245','Salerno','Salerno','Via Roma 23',84128,'Italia',NULL,1),(7,'Mario','Cicalese','3277510722','CC','Nocera Inferiore','Via Dei Pozzi 9',84014,'Italia','',-1),(8,'Mario','Cicalese','3277510722','pagani colera','Nocera Inferiore','Via Dei Pozzi 9',84014,'Italia','',-1),(9,'Mario','Cicalese','3277510722','ascaccacaas','Nocera Inferiore','Via Dei Pozzi 9',84014,'Italia','',-1),(10,'Mario','Cicalese','3277510722','kkkkkkkk','Nocera Inferiore','Via Dei Pozzi 9',84014,'Italia','',-1),(11,'Alessandro','Zoccola','+393272349622','capua','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(12,'Alessandro','Zoccola','+393272349622','San Cipriano Pic.','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','Nessuna nota in particolare',1),(13,'Alessandro','Zoccola','+393272349622','Modena','San Cipriano Picentino','Via Tora',84099,'Italia','Nessuna nota in particolare',1),(14,'Alessandro','Zoccola','+393272349622','capua','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',3),(15,'Vincenzo','Arnone','1234567890','Salerno','Salerno','Via Roma 31',84090,'Italia','',4),(16,'Alessandro','Zoccola','+393272349622','khuuiui','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(17,'Alessandro','Zoccola','+393272349622','khuuiui','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(18,'Alessandro','Zoccola','+393272349622','Ciaociao','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(19,'Alessandro','Zoccola','+393272349622','hegskgfwekw','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(20,'Alessandro','Zoccola','+393272349622','vksgkbjsgsk','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(21,'Alessandro','Zoccola','+393272349622','xdgsgs','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(22,'Alessandro','Zoccola','+393272349622','lsjoglweogosl','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(23,'Alessandro','Zoccola','+393272349622','skeufbskefs','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(24,'Alessandro','Zoccola','+393272349622','jkgke','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(25,'Alessandro','Zoccola','+393272349622','ksdlgssssssssssssssssssss','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1),(26,'Alessandro','Zoccola','+393272349622','San Cipriano Pic.','San Cipriano Picentino','Via Tora di Filetta',84099,'Italia','',-1);
/*!40000 ALTER TABLE `indirizzo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-28 22:39:01
