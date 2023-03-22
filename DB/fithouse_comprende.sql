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
-- Table structure for table `comprende`
--

DROP TABLE IF EXISTS `comprende`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comprende` (
  `ID_prod` varchar(30) NOT NULL,
  `Num_ordine` int NOT NULL,
  `Mole` varchar(30) NOT NULL,
  `Aroma` varchar(40) NOT NULL,
  `Prezzo` double NOT NULL,
  `Url_Foto` varchar(100) NOT NULL,
  `Quantita` int NOT NULL,
  KEY `id_prod_idx` (`ID_prod`),
  KEY `id_ordid_idx` (`Num_ordine`),
  CONSTRAINT `id_ordid` FOREIGN KEY (`Num_ordine`) REFERENCES `ordine` (`Num_Ordine`) ON UPDATE CASCADE,
  CONSTRAINT `id_prod` FOREIGN KEY (`ID_prod`) REFERENCES `prodotto` (`ID_prod`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprende`
--

LOCK TABLES `comprende` WRITE;
/*!40000 ALTER TABLE `comprende` DISABLE KEYS */;
INSERT INTO `comprende` VALUES ('A2',31,'500g','Caffè nero',17.24,'images/prodotti/caffènero',2),('A3',31,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('A3',32,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('A3',33,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('A3',34,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('A3',35,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',2),('B1',36,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('B1',37,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',37,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B1',38,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',38,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B1',39,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',39,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B1',40,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',40,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B1',41,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',41,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B1',42,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('B1',43,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',43,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B2',44,'270pz','N/A',13.19,'images/prodotti/vitamineccompresse',1),('B1',44,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',45,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B1',46,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A2',47,'500g','Caffè nero',17.24,'images/prodotti/caffènero',1),('B1',47,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',2),('B1',48,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',2),('A3',48,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B1',49,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',2),('A3',49,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B1',50,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',2),('A3',50,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B1',51,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',51,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',2),('A2',52,'500g','Caffè nero',17.24,'images/prodotti/caffènero',1),('B1',53,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',2),('A2',54,'500g','Caffè nero',17.24,'images/prodotti/caffènero',1),('C1',55,'1kg','frutti di bosco',37.99,'images/prodotti/creatinamonoidrato',1),('A2',56,'500g','Caffè nero',17.24,'images/prodotti/caffènero',1),('A2',57,'500g','Caffè nero',17.24,'images/prodotti/caffènero',1),('B1',57,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',58,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',2),('B1',59,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('C1',60,'500g','frutti di bosco',24.99,'images/prodotti/creatinamonoidrato',2),('A2',61,'500g','Caffè nero',17.24,'images/prodotti/caffènero',2),('A3',61,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('B1',62,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',64,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',2),('B1',65,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('B1',66,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('D3',67,'1kg','N/A',29.99,'images/prodotti/spirulinainpolvere',1),('D3',68,'1kg','N/A',29.99,'images/prodotti/spirulinainpolvere',1),('A3',69,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1),('C4',70,'2.5kg','caramello',11.24,'images/prodotti/avenaistantanea',1),('B1',71,'500g','N/A',29.99,'images/prodotti/vitamineb5inpolvere',1),('A3',71,'1kg','N/A',18.89,'images/prodotti/proteinedialbumeinpolvere',1);
/*!40000 ALTER TABLE `comprende` ENABLE KEYS */;
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
