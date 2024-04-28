-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema_db
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `castt`
--

DROP TABLE IF EXISTS `castt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `castt` (
  `MOVIE_ID` varchar(20) DEFAULT NULL,
  `PRODUCER` varchar(20) DEFAULT NULL,
  `MALE_ACTOR` varchar(20) DEFAULT NULL,
  `FEMALE_ACTOR` varchar(20) DEFAULT NULL,
  `MALE_COACTOR` varchar(20) DEFAULT NULL,
  `FEMALE_COACTOR` varchar(20) DEFAULT NULL,
  `DIRECTOR` varchar(20) DEFAULT NULL,
  KEY `CASTT_FK_MOVIE_ID` (`MOVIE_ID`),
  CONSTRAINT `CASTT_FK_MOVIE_ID` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `castt`
--

LOCK TABLES `castt` WRITE;
/*!40000 ALTER TABLE `castt` DISABLE KEYS */;
/*!40000 ALTER TABLE `castt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CUSTOMER_ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `PHONE_NO` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`CUSTOMER_ID`),
  UNIQUE KEY `customer_unique_email` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (2,'Muneeb khowaja','muneeb@gmail.com','Taha@111','03353036800'),(4,'taha siddiqui','taha@gmail.com','Muneeb@1','03353036802'),(5,'laiba fawwad','laiba12@gmail.com','Laiba12@','03156849279');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hall` (
  `HALL_ID` int NOT NULL AUTO_INCREMENT,
  `MOVIE_ID` varchar(20) DEFAULT NULL,
  `NO_OF_SEATS` int DEFAULT NULL,
  PRIMARY KEY (`HALL_ID`),
  KEY `HALL_FK_MOVIE_ID` (`MOVIE_ID`),
  CONSTRAINT `HALL_FK_MOVIE_ID` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'M1',50),(3,'M2',50),(4,'M3',50),(5,'M4',50),(6,'M5',50);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `MOVIE_ID` varchar(20) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `GENERE` varchar(20) DEFAULT NULL,
  `RELEASE_DATE` date DEFAULT NULL,
  `LANGUAGE` varchar(20) DEFAULT NULL,
  `PRODUCTION_COMPANY` varchar(50) DEFAULT NULL,
  `DURATION` varchar(20) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MOVIE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES ('M1','Inception','Sci-Fi','2010-07-16','English','Warner Bros.','148 minutes','Active'),('M13','John Wick','Action','2014-10-24','English','Thunder Road Pictures','101 min','Active'),('M2','The Shawshank Redemption','Drama','1994-09-23','English','Castle Rock Entertainment','142 minutes','Active'),('M3','The Dark Knight','Action','2008-07-18','English','Warner Bros.','152 minutes','Active'),('M4','Forrest Gump','Drama','1994-07-06','English','Paramount Pictures','142 minutes','Active'),('M5','The Godfather','Crime','1972-03-24','English','Paramount Pictures','175 minutes','Active'),('M7','The Matrix','Sci-Fi','1999-03-31','English','Warner Bros.','136 minutes','Active'),('M8','Titanic','Romance','1997-12-19','English','20th Century Fox','195 minutes',NULL);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_reviews`
--

DROP TABLE IF EXISTS `movie_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_reviews` (
  `REVIEW_ID` int NOT NULL AUTO_INCREMENT,
  `MOVIE_ID` varchar(20) DEFAULT NULL,
  `REVIEWS` text,
  `RATING` int DEFAULT NULL,
  `CUSTOMER_ID` int DEFAULT NULL,
  PRIMARY KEY (`REVIEW_ID`),
  KEY `MOVIE_REVIEWS_FK_MOVIE_ID` (`MOVIE_ID`),
  KEY `MOVIE_REVIEWS_FK_CUSTOMER_ID` (`CUSTOMER_ID`),
  CONSTRAINT `MOVIE_REVIEWS_FK_CUSTOMER_ID` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`),
  CONSTRAINT `MOVIE_REVIEWS_FK_MOVIE_ID` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`),
  CONSTRAINT `movie_reviews_chk_1` CHECK (((`RATING` >= 1) and (`RATING` <= 5)))
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_reviews`
--

LOCK TABLES `movie_reviews` WRITE;
/*!40000 ALTER TABLE `movie_reviews` DISABLE KEYS */;
INSERT INTO `movie_reviews` VALUES (23,'M1','heello',4,2),(24,'M2','best movie',4,2),(26,'M1','great movie',4,5);
/*!40000 ALTER TABLE `movie_reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pk`
--

DROP TABLE IF EXISTS `pk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pk` (
  `MOVIE_ID` varchar(20) DEFAULT NULL,
  `HALL_ID` int DEFAULT NULL,
  `SHOWTIME_ID` varchar(20) DEFAULT NULL,
  KEY `PK_FK_MOVIE_ID` (`MOVIE_ID`),
  KEY `PK_FK_HALL_ID` (`HALL_ID`),
  KEY `PK_FK_SHOWTIME_ID` (`SHOWTIME_ID`),
  CONSTRAINT `PK_FK_HALL_ID` FOREIGN KEY (`HALL_ID`) REFERENCES `hall` (`HALL_ID`),
  CONSTRAINT `PK_FK_MOVIE_ID` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`),
  CONSTRAINT `PK_FK_SHOWTIME_ID` FOREIGN KEY (`SHOWTIME_ID`) REFERENCES `showtime` (`SHOWTIME_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pk`
--

LOCK TABLES `pk` WRITE;
/*!40000 ALTER TABLE `pk` DISABLE KEYS */;
/*!40000 ALTER TABLE `pk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `RESERVATION_ID` int NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int DEFAULT NULL,
  `MOVIE_ID` varchar(20) DEFAULT NULL,
  `HALL_ID` int DEFAULT NULL,
  `SHOWTIME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`RESERVATION_ID`),
  KEY `RESERVATION_FK_CUSTOMER_ID` (`CUSTOMER_ID`),
  KEY `RESERVATION_FK_MOVIE_ID` (`MOVIE_ID`),
  KEY `RESERVATION_FK_HALL_ID` (`HALL_ID`),
  CONSTRAINT `RESERVATION_FK_CUSTOMER_ID` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `RESERVATION_FK_HALL_ID` FOREIGN KEY (`HALL_ID`) REFERENCES `hall` (`HALL_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `RESERVATION_FK_MOVIE_ID` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revenue`
--

DROP TABLE IF EXISTS `revenue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revenue` (
  `REVENUE_ID` int NOT NULL AUTO_INCREMENT,
  `MOVIE_ID` varchar(20) DEFAULT NULL,
  `EARNING` float DEFAULT '0',
  PRIMARY KEY (`REVENUE_ID`),
  KEY `REVENUE_FK_MOVIE_ID` (`MOVIE_ID`),
  CONSTRAINT `REVENUE_FK_MOVIE_ID` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revenue`
--

LOCK TABLES `revenue` WRITE;
/*!40000 ALTER TABLE `revenue` DISABLE KEYS */;
INSERT INTO `revenue` VALUES (1,'M1',4000),(2,'M2',3000),(3,'M3',7000),(4,'M4',0);
/*!40000 ALTER TABLE `revenue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showtime`
--

DROP TABLE IF EXISTS `showtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showtime` (
  `SHOWTIME_ID` varchar(20) NOT NULL,
  `MOVIE_ID` varchar(20) DEFAULT NULL,
  `HALL_ID` int DEFAULT NULL,
  `RESERVED_SEATS` int DEFAULT NULL,
  `AVAILABLE_SEATS` int DEFAULT NULL,
  `STARTTIME` time DEFAULT NULL,
  `ENDTIME` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`SHOWTIME_ID`),
  KEY `SHOWTIME_FK_MOVIE_ID` (`MOVIE_ID`),
  KEY `SHOWTIME_FK_HALL_ID` (`HALL_ID`),
  CONSTRAINT `SHOWTIME_FK_HALL_ID` FOREIGN KEY (`HALL_ID`) REFERENCES `hall` (`HALL_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `SHOWTIME_FK_MOVIE_ID` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showtime`
--

LOCK TABLES `showtime` WRITE;
/*!40000 ALTER TABLE `showtime` DISABLE KEYS */;
INSERT INTO `showtime` VALUES ('S1','M1',5,19,31,'18:00:00','21:00:00','2023-11-29'),('S2','M2',3,28,22,'15:30:00','18:30:00','2023-11-25'),('S3','M3',1,17,33,'20:00:00','23:00:00','2023-11-26'),('S4','M4',3,7,43,'14:00:00','17:00:00','2023-11-27'),('S5','M5',1,30,20,'19:30:00','22:30:00','2023-11-24'),('S6','M3',1,NULL,NULL,'18:00:00','21:00:00','2023-12-05');
/*!40000 ALTER TABLE `showtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `STAFF_ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `CNIC` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`STAFF_ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  UNIQUE KEY `CNIC` (`CNIC`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'saqib shaikh','saqib@gmail.com','Muneeb@1','4210161358373'),(2,'muhammad kazim','kazim@gmail.com','Kazim@11','4210161358374');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `TICKET_ID` int NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int DEFAULT NULL,
  `MOVIE_ID` varchar(20) DEFAULT NULL,
  `PRICE` float DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `SEAT_NO` int DEFAULT NULL,
  `SHOWTIME_ID` varchar(20) DEFAULT NULL,
  `TICKET_TYPE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`TICKET_ID`),
  KEY `TICKET_FK_CUSTOMER_ID` (`CUSTOMER_ID`),
  KEY `TICKET_FK_MOVIE_ID` (`MOVIE_ID`),
  KEY `TICKET_FK_SHOWTIME_ID` (`SHOWTIME_ID`),
  CONSTRAINT `TICKET_FK_CUSTOMER_ID` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TICKET_FK_MOVIE_ID` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TICKET_FK_SHOWTIME_ID` FOREIGN KEY (`SHOWTIME_ID`) REFERENCES `showtime` (`SHOWTIME_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (2,2,'M1',2000,'BOOKED',2,NULL,NULL),(3,2,'M2',2000,'BOOKED',5,NULL,NULL),(4,2,'M3',2000,'BOOKED',8,NULL,NULL),(5,2,'M1',2000,'BOOKED',5,NULL,NULL),(6,2,'M1',2000,'BOOKED',5,NULL,NULL),(7,2,'M2',2000,'BOOKED',7,NULL,NULL),(8,2,'M3',1000,'BOOKED',4,NULL,NULL),(9,2,'M2',1000,'BOOKED',4,NULL,NULL),(11,4,'M1',2000,'booked',69,'s1',NULL),(12,2,'m3',2000,'booked',8,'s1',NULL),(15,2,'M3',2000,'booked',6,'S3',NULL),(16,5,'m1',2000,'booked',12,'s1',NULL);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `INVOICE_ID` int NOT NULL,
  `TICKET_ID` int DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `CUSTOMER_ID` int DEFAULT NULL,
  `QUANTITY` int DEFAULT NULL,
  PRIMARY KEY (`INVOICE_ID`),
  KEY `TRANSACTION_FK_TICKET_ID` (`TICKET_ID`),
  KEY `TRANSACTION_FK_CUSTOMER_ID` (`CUSTOMER_ID`),
  CONSTRAINT `TRANSACTION_FK_CUSTOMER_ID` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TRANSACTION_FK_TICKET_ID` FOREIGN KEY (`TICKET_ID`) REFERENCES `ticket` (`TICKET_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-05  6:48:26
