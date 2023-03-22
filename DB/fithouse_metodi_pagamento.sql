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
-- Table structure for table `metodi_pagamento`
--

DROP TABLE IF EXISTS `metodi_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodi_pagamento` (
  `NumCarta` varchar(30) NOT NULL,
  `Intestatario` varchar(60) NOT NULL,
  `Data_Scadenza` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CVV` int NOT NULL,
  PRIMARY KEY (`NumCarta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodi_pagamento`
--

LOCK TABLES `metodi_pagamento` WRITE;
/*!40000 ALTER TABLE `metodi_pagamento` DISABLE KEYS */;
INSERT INTO `metodi_pagamento` VALUES ('1000100010001000','Alessandro Zoccola','11-22',123),('1111222233334444','bg','1-12',1),('1234 1231 1231 1231','sium sium','10/12',222),('1234123412341234','Alessandro Zoccola','12/22',123),('1234256987450213','Vincenzo Arnone','01/22',123),('1234567890123456','Alessandro Zoccola','12-22',123),('141425251414','dfv','1-12',111),('23234354212343212','Mario Cicalese','12/32',222),('45648161','Alessandro Zoccola','20/21',0),('8908890889088908','Alessandro Zoccola','12/21',123),('9874987498749874','Alessandro Zoccola','12-22',123),('9875987598759875','Alessandro Zoccola','51-6',123);
/*!40000 ALTER TABLE `metodi_pagamento` ENABLE KEYS */;
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
