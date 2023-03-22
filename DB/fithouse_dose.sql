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
-- Table structure for table `dose`
--

DROP TABLE IF EXISTS `dose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dose` (
  `ID_prodotto` varchar(30) NOT NULL,
  `ID_mole` varchar(30) NOT NULL,
  `ID_gusto` varchar(40) NOT NULL,
  `quantita_disp` int NOT NULL,
  `prezzo` double NOT NULL,
  `Prezzo_Scontato` double DEFAULT NULL,
  `url_foto` varchar(100) NOT NULL DEFAULT 'images/prodotti/fithouse1.png',
  PRIMARY KEY (`ID_prodotto`,`ID_mole`,`ID_gusto`),
  KEY `ID_mole_idx` (`ID_mole`),
  KEY `ID_gusto_idx` (`ID_gusto`),
  CONSTRAINT `ID_mole` FOREIGN KEY (`ID_mole`) REFERENCES `mole` (`Quantita`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ID_prodotto` FOREIGN KEY (`ID_prodotto`) REFERENCES `prodotto` (`ID_prod`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dose`
--

LOCK TABLES `dose` WRITE;
/*!40000 ALTER TABLE `dose` DISABLE KEYS */;
INSERT INTO `dose` VALUES ('A1','2.5kg','mela e lime',20,15,7.5,''),('A1','2kg','banana',10,62.99,40.94,'images/prodotti/beef-protein-isolate-97'),('A1','2kg','cioccolato',10,62.99,40.94,'images/prodotti/beef-protein-isolate-97'),('A1','2kg','Senza aroma',10,62.99,40.94,'images/prodotti/beef-protein-isolate-97'),('A1','500g','banana',10,17.49,11.36,'images/prodotti/beef-protein-isolate-97'),('A1','500g','cioccolato',10,17.49,11.36,'images/prodotti/beef-protein-isolate-97'),('A1','500g','Senza aroma',10,17.49,11.36,'images/prodotti/beef-protein-isolate-97'),('A2','500g','Caffè nero',4,22.99,17.24,'images/prodotti/caffènero'),('A2','500g','Mocha',3,22.99,17.24,'images/prodotti/mocha'),('A3','1kg','N/A',5,26.99,18.89,'images/prodotti/proteinedialbumeinpolvere'),('A3','500g','N/A',5,14.99,10.49,'images/prodotti/proteinedialbumeinpolvere'),('B1','500g','N/A',7,29.99,NULL,'images/prodotti/vitamineb5inpolvere'),('B2','270pz','N/A',6,21.99,13.19,'images/prodotti/vitamineccompresse'),('B2','90pz','N/A',6,8.99,5.39,'images/prodotti/vitamineccompresse'),('B3','270pz','N/A',7,55.99,41.99,'images/prodotti/capsulecurcuminaevitaminad3'),('B3','90pz','N/A',6,28.99,18.84,'images/prodotti/capsulecurcuminaevitaminad3'),('B4','270pz','N/A',5,24.99,13.74,'images/prodotti/vitaminaecapsulemolli'),('B4','90pz','N/A',5,10.99,6.04,'images/prodotti/vitaminaecapsulemolli'),('C1','1kg','frutti di bosco',20,37.99,NULL,'images/prodotti/creatinamonoidrato'),('C1','1kg','mela e lime',20,37.99,NULL,'images/prodotti/creatinamonoidrato'),('C1','1kg','Senza aroma',10,37.99,NULL,'images/prodotti/creatinamonoidrato'),('C1','500g','frutti di bosco',20,24.99,NULL,'images/prodotti/creatinamonoidrato'),('C1','500g','mela e lime',20,24.99,NULL,'images/prodotti/creatinamonoidrato'),('C1','500g','Senza aroma',20,24.99,NULL,'images/prodotti/creatinamonoidrato'),('C2','1kg','Cocco',8,43.99,30.79,'images/prodotti/ketoshakeintegratori'),('C2','1kg','Vaniglia',7,43.99,30.79,'images/prodotti/ketoshakeintegratori'),('C2','2.5kg','Cocco',7,99.99,69.99,'images/prodotti/ketoshakeintegratori'),('C2','2.5kg','Vaniglia',7,99.99,69.99,'images/prodotti/ketoshakeintegratori'),('C3','1kg','Anguria',9,84.99,55.24,'images/prodotti/amminoacidiessenziali'),('C3','1kg','Cola',9,84.99,55.24,'images/prodotti/amminoacidiessenziali'),('C3','1kg','Senza aroma',9,84.99,55.24,'images/prodotti/amminoacidiessenziali'),('C3','500g','Anguria',8,49.99,32.49,'images/prodotti/amminoacidiessenziali'),('C3','500g','Cola',8,49.99,32.49,'images/prodotti/amminoacidiessenziali'),('C3','500g','Senza aroma',8,49.99,32.49,'images/prodotti/amminoacidiessenziali'),('C4','2.5kg','caramello',22,14.99,11.24,'images/prodotti/avenaistantanea'),('C4','2.5kg','cioccolato',22,14.99,11.24,'images/prodotti/avenaistantanea'),('C4','2.5kg','Senza aroma',22,14.99,11.24,'images/prodotti/avenaistantanea'),('C4','500g','caramello',13,6.99,4.89,'images/prodotti/avenaistantanea'),('C4','500g','cioccolato',13,6.99,4.89,'images/prodotti/avenaistantanea'),('C4','500g','Senza aroma',13,6.99,4.89,'images/prodotti/avenaistantanea'),('D1','90pz','N/A',5,29.99,NULL,'images/prodotti/prom3ga'),('D2','500g','N/A',8,8.99,6.29,'images/prodotti/mirtillirossisecchi'),('D3','1kg','N/A',11,49.99,29.99,'images/prodotti/spirulinainpolvere'),('D3','500g','N/A',11,29.99,17.99,'images/prodotti/spirulinainpolvere'),('E1','1kg','fragola e banana',18,34.99,22.74,'images/prodotti/frullatoperlaprimacolazionecompleto'),('E1','1kg','mirtillo e lampone',18,34.99,22.74,'images/prodotti/frullatoperlaprimacolazionecompleto'),('E1','2.5kg','fragola e banana',10,79.99,51.99,'images/prodotti/frullatoperlaprimacolazionecompleto'),('E1','2.5kg','mirtillo e lampone',10,79.99,51.99,'images/prodotti/frullatoperlaprimacolazionecompleto'),('E2','100g','N/A',5,8.99,2.69,'images/prodotti/inositolo'),('E2','500g','N/A',5,28.99,11.59,'images/prodotti/inositolo'),('F1','1pz','burro darachidi',40,2.49,1.86,'images/prodotti/brownieproteicivegani_burrodiarichidi'),('F1','1pz','doppio cioccolato',40,2.49,1.86,'images/prodotti/brownieproteicivegani'),('F2','100g','N/A',10,5.64,2.49,'images/prodotti/mixfruttaseccasemienoci'),('F23','1.5kg','burro d',20,12.5,6.9,'');
/*!40000 ALTER TABLE `dose` ENABLE KEYS */;
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
