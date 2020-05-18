DROP DATABASE IF EXISTS `spring_food`;

CREATE DATABASE IF NOT EXISTS `spring_food`;
USE `spring_food`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- Default passwords here are: admin
--

INSERT INTO `user` (username, password)
VALUES 
('admin','$2a$10$OP8JQKCyP1.3PkV.SN3wX.fdKG10qjdCYe.Q.gfZRLN8ScpXgQShm');

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES 
('USER'), ('ADMIN');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles`
(
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(1, 2);

--
-- Table structure for table `recipe_search`
--

DROP TABLE IF EXISTS `recipe_search`;

CREATE TABLE `recipe_search`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `search_query` varchar(200) NOT NULL,
  `update_time_stamp` bigint NOT NULL,
  `successful_iteration` boolean NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `recipe_base`
--

DROP TABLE IF EXISTS `recipe_base`;

CREATE TABLE `recipe_base`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `spoonacular_id` bigint UNIQUE NOT NULL,
  `title` varchar(200) NOT NULL,
  `ready_in_minutes` bigint DEFAULT NULL,
  `servings` bigint DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `recipe_detail`
--

DROP TABLE IF EXISTS `recipe_detail`;

CREATE TABLE `recipe_detail`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `recipe_base_id` bigint UNIQUE NOT NULL,
  `vegetarian` boolean DEFAULT FALSE,
  `vegan` boolean DEFAULT FALSE,
  `gluten_free` boolean DEFAULT FALSE,
  `dairy_free` boolean DEFAULT FALSE,
  `very_healthy` boolean DEFAULT FALSE,
  `cheap` boolean DEFAULT FALSE,
  `very_popular` boolean DEFAULT FALSE,
  `sustainable` boolean DEFAULT FALSE,
  `low_fodmap` boolean DEFAULT FALSE,
  `weight_watcher_smart_points` bigint DEFAULT NULL,
  `gaps` varchar(200) DEFAULT NULL,
  `preparation_in_minutes` bigint DEFAULT NULL,
  `cooking_minutes` bigint DEFAULT NULL,
  `source_url` varchar(200) DEFAULT NULL,
  `spoonacular_source_url` varchar(200) DEFAULT NULL,
  `aggregate_likes` bigint DEFAULT NULL,
  `spoonacular_score` double DEFAULT NULL,
  `health_score` double DEFAULT NULL,
  `credits_text` varchar(200) DEFAULT NULL,
  `source_name` varchar(200) DEFAULT NULL,
  `price_per_serving` double DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `ready_in_minutes` bigint DEFAULT NULL,
  `servings` bigint DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `image_type` varchar(20) DEFAULT NULL,
  `summary` text DEFAULT NULL,
  `instructions` text DEFAULT NULL,
  `original_id` bigint DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  INDEX `FK_RECIPE_BASE_idx` (`recipe_base_id`),
  
  CONSTRAINT `FK_RECIPE_BASE` FOREIGN KEY (`recipe_base_id`) 
  REFERENCES `recipe_base` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `cuisine`
--

DROP TABLE IF EXISTS `cuisine`;

CREATE TABLE `cuisine`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `recipe_detail_cuisines`
--

DROP TABLE IF EXISTS `recipe_detail_cuisines`;

CREATE TABLE `recipe_detail_cuisines`
(
  `recipe_detail_id` bigint NOT NULL,
  `cuisine_id` bigint NOT NULL,
  
  PRIMARY KEY (`recipe_detail_id`,`cuisine_id`),
  
  KEY `FK_CUISINE_idx` (`cuisine_id`),
  
  CONSTRAINT `FK_RECIPE_DETAIL_CUISINE` FOREIGN KEY (`recipe_detail_id`) 
  REFERENCES `recipe_detail` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_CUISINE` FOREIGN KEY (`cuisine_id`) 
  REFERENCES `cuisine` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `dish_type`
--

DROP TABLE IF EXISTS `dish_type`;

CREATE TABLE `dish_type`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `recipe_detail_dish_types`
--

DROP TABLE IF EXISTS `recipe_detail_dish_types`;

CREATE TABLE `recipe_detail_dish_types`
(
  `recipe_detail_id` bigint NOT NULL,
  `dish_type_id` bigint NOT NULL,
  
  PRIMARY KEY (`recipe_detail_id`,`dish_type_id`),
  
  KEY `FK_DISH_TYPE_idx` (`dish_type_id`),
  
  CONSTRAINT `FK_RECIPE_DETAIL_DISH_TYPE` FOREIGN KEY (`recipe_detail_id`) 
  REFERENCES `recipe_detail` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_DISH_TYPE` FOREIGN KEY (`dish_type_id`) 
  REFERENCES `dish_type` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;

CREATE TABLE `ingredient`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `spoonacular_id` bigint DEFAULT NULL,
  `aisle` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `consistency` varchar(200) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `original` varchar(200) DEFAULT NULL,
  `original_string` varchar(200) DEFAULT NULL,
  `original_name` varchar(200) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `recipe_detail_ingredients`
--

DROP TABLE IF EXISTS `recipe_detail_ingredients`;

CREATE TABLE `recipe_detail_ingredients`
(
  `recipe_detail_id` bigint NOT NULL,
  `ingredient_id` bigint NOT NULL,
  
  PRIMARY KEY (`recipe_detail_id`,`ingredient_id`),
  
  KEY `FK_INGREDIENT_idx` (`ingredient_id`),
  
  CONSTRAINT `FK_RECIPE_DETAIL_INGREDIENT` FOREIGN KEY (`recipe_detail_id`) 
  REFERENCES `recipe_detail` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_INGREDIENT_RDI` FOREIGN KEY (`ingredient_id`) 
  REFERENCES `ingredient` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `ingredient_metas`
--

DROP TABLE IF EXISTS `ingredient_metas`;

CREATE TABLE `ingredient_metas`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ingredient_id` bigint NOT NULL,
  `meta` varchar(200) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  INDEX `FK_INGREDIENT_idx` (`ingredient_id`),
  
  CONSTRAINT `FK_INGREDIENT_IM` FOREIGN KEY (`ingredient_id`) 
  REFERENCES `ingredient` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `ingredient_meta_informations`
--

DROP TABLE IF EXISTS `ingredient_meta_informations`;

CREATE TABLE `ingredient_meta_informations`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ingredient_id` bigint NOT NULL,
  `meta_information` varchar(200) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  INDEX `FK_INGREDIENT_idx` (`ingredient_id`),
  
  CONSTRAINT `FK_INGREDIENT_IMI` FOREIGN KEY (`ingredient_id`) 
  REFERENCES `ingredient` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `measure`
--

DROP TABLE IF EXISTS `measure`;

CREATE TABLE `measure`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ingredient_id` bigint NOT NULL,
  `unit_short` varchar(50) DEFAULT NULL,
  `unit_long` varchar(50) DEFAULT NULL,
  `imp_unit_short` varchar(50) DEFAULT NULL,
  `imp_unit_long` varchar(50) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  INDEX `FK_INGREDIENT_idx` (`ingredient_id`),
  
  CONSTRAINT `FK_INGREDIENT_MEASURE` FOREIGN KEY (`ingredient_id`) 
  REFERENCES `ingredient` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `step`
--

DROP TABLE IF EXISTS `step`;

CREATE TABLE `step`
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `number` bigint NOT NULL,
  `step` text NOT NULL,
  `length_number` bigint DEFAULT NULL,
  `length_unit` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `recipe_detail_steps`
--

DROP TABLE IF EXISTS `recipe_detail_steps`;

CREATE TABLE `recipe_detail_steps`
(
  `recipe_detail_id` bigint NOT NULL,
  `step_id` bigint NOT NULL,
  `recipe_option_id` bigint NOT NULL,
  
  PRIMARY KEY (`recipe_detail_id`,`step_id`),
  
  KEY `FK_STEP_idx` (`step_id`),
  
  CONSTRAINT `FK_RECIPE_DETAIL_STEP` FOREIGN KEY (`recipe_detail_id`) 
  REFERENCES `recipe_detail` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_STEPS` FOREIGN KEY (`step_id`) 
  REFERENCES `step` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

SET FOREIGN_KEY_CHECKS = 1;