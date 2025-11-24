-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: inventory
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Electrónicos','Dispositivos electrónicos y gadgets','2025-11-24 05:12:06','2025-11-24 05:12:06'),(2,'Accesorios','Accesorios para varios dispositivos','2025-11-24 05:12:06','2025-11-24 05:12:06'),(3,'Hogar','Productos para el hogar','2025-11-24 05:12:06','2025-11-24 05:12:06'),(4,'Computadoras','Laptops, desktops y periféricos','2025-11-24 05:12:06','2025-11-24 05:12:06'),(5,'Ropa','Vestimenta de todo tipo','2025-11-24 05:12:06','2025-11-24 05:12:06');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  `description` text,
  `category_id` int NOT NULL,
  `supplier_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `category_id` (`category_id`),
  KEY `supplier_id` (`supplier_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Laptop Lenovo IdeaPad 3',1899.90,12,'Laptop Core i5 de 12va generación',4,1,'2025-11-24 05:12:35','2025-11-24 05:12:35',1),(2,'Mouse Inalámbrico Logitech M170',39.90,50,'Mouse inalámbrico de bajo consumo',2,2,'2025-11-24 05:12:35','2025-11-24 05:12:35',1),(3,'Audífonos Sony WH-CH510',159.00,25,'Audífonos inalámbricos Bluetooth',1,4,'2025-11-24 05:12:35','2025-11-24 05:12:35',1),(4,'Set de vasos Luminarc 6 unidades',29.90,40,'Juego de vasos de vidrio templado',3,3,'2025-11-24 05:12:35','2025-11-24 05:12:35',1),(5,'Cargador Samsung 25W',79.00,30,'Cargador rápido USB-C original',2,1,'2025-11-24 05:12:35','2025-11-24 05:12:35',1),(6,'Camisa Casual Hombre Azul',69.90,20,'Camisa de algodón manga larga',5,5,'2025-11-24 05:12:35','2025-11-24 05:12:35',1),(7,'Teclado Mecánico Redragon Kumara',189.00,18,'Teclado mecánico RGB switches Redragon',4,4,'2025-11-24 05:12:35','2025-11-24 05:12:35',1),(8,'Ventilador de Mesa 16\"',129.00,15,'Ventilador oscilante de alta potencia',3,3,'2025-11-24 05:12:35','2025-11-24 05:12:35',1),(9,'Smartwatch Xiaomi Redmi Watch 3',299.00,22,'Smartwatch con GPS y pantalla AMOLED',1,1,'2025-11-24 05:12:35','2025-11-24 05:12:35',1),(10,'Memoria USB 64GB Kingston',35.00,60,'Memoria flash USB 3.2',2,2,'2025-11-24 05:12:35','2025-11-24 05:12:35',1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (1,'Tech Imports SAC','contacto@techimports.com','987654321','2025-11-24 05:12:26','2025-11-24 05:12:26'),(2,'Global Parts Peru','info@globalparts.pe','912345678','2025-11-24 05:12:26','2025-11-24 05:12:26'),(3,'Home Solutions','ventas@homesolutions.com','945678123','2025-11-24 05:12:26','2025-11-24 05:12:26'),(4,'Digital World','support@digitalworld.com','934567812','2025-11-24 05:12:26','2025-11-24 05:12:26'),(5,'Moda Express','contacto@modaexpress.com','901234567','2025-11-24 05:12:26','2025-11-24 05:12:26');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'Diego','diego@gmail.com','diego','$2a$10$uGcLD1VDWf0unYZIyKgY4.x2mgWhr8QUVGs1A47NeY6E2QfOYBtvW',1,1,'2025-11-24 04:30:07','2025-11-24 04:30:07'),(5,'Administrador','admin@correo.com','admin','$2a$10$eB7/TIxoyzyME9KWjx8EdOywYyrlcoW4KKfRDur9w1QwyUh5wqmPW',1,1,'2025-11-24 04:38:46','2025-11-24 04:38:46'),(6,'Usuario Normal','user@correo.com','usuario','$2a$10$WtigOXn/SdGpQEI8.lV0oeBREAaSzq3Ai/MAjsX23l3q7vDj5XYHK',2,1,'2025-11-24 04:39:07','2025-11-24 04:39:07');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-24  0:16:39
