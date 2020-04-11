-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: flashcard
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `flashcard`
--

DROP TABLE IF EXISTS `flashcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flashcard` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(500) NOT NULL,
  `answer` varchar(500) NOT NULL,
  `set_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Flashcards_id_uindex` (`id`),
  KEY `flashcards_set_set_id_fk` (`set_id`),
  CONSTRAINT `flashcards_set_set_id_fk` FOREIGN KEY (`set_id`) REFERENCES `flashcardset` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flashcard`
--

LOCK TABLES `flashcard` WRITE;
/*!40000 ALTER TABLE `flashcard` DISABLE KEYS */;
INSERT INTO `flashcard` VALUES (1,'Java is short for \"JavaScript\".','false',1),(2,'(X+2)/4 = 20 What is X?','78',3),(3,'In Java, it is possible to inherit attributes and methods from one class to another.','True',1),(4,'Who created java language?','James Gosling, Mike Sheridan, Patrick Naughton',1),(5,'What was the first name of JAVA?','Greentalk',1),(6,'What was the second name of JAVA?','Oak, Oak is a symbol of strength and chosen as a national tree of many countries like the U.S.A., France, Germany, Romania, etc.In 1995, Oak was renamed as \"Java\" because it was already a trademark by Oak Technologies.',1),(7,'4+5+6+7 = ?','22',3),(8,'what is the capital of Thailand?','Bangkok',4),(9,'What is the capital of germany?','Berlin',4),(10,'11+5+3-3-5-11 = ?','0',3),(11,'What is the capital of China?','Beijing',4),(12,'2X + 6 = 10 What is X?','2',3),(13,'What is the capital of russia?','Moscow',4),(14,'What is the capital of Chile?','Santiago',4);
/*!40000 ALTER TABLE `flashcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flashcardset`
--

DROP TABLE IF EXISTS `flashcardset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flashcardset` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flashcardset`
--

LOCK TABLES `flashcardset` WRITE;
/*!40000 ALTER TABLE `flashcardset` DISABLE KEYS */;
INSERT INTO `flashcardset` VALUES (1,'IT','Java','Basic Java question'),(2,'Chemistry','Science','High school Chemistry questions'),(3,'Algebra','Mathematics','High school Mathematics'),(4,'geography','geography','Elementary geography questions');
/*!40000 ALTER TABLE `flashcardset` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-31 21:43:47
