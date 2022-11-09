-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema traveldb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `traveldb` ;

-- -----------------------------------------------------
-- Schema traveldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `traveldb` DEFAULT CHARACTER SET utf8 ;
USE `traveldb` ;

-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NULL,
  `image` VARCHAR(45) NULL,
  `lattitude` DOUBLE NULL,
  `longitude` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `visit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `visit` ;

CREATE TABLE IF NOT EXISTS `visit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `arrival_date` DATE NULL,
  `departure_date` DATE NULL,
  `note` VARCHAR(45) NULL,
  `location_id` INT NOT NULL,
  `photo` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_visit_location_idx` (`location_id` ASC),
  CONSTRAINT `fk_visit_location`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `photo` ;

CREATE TABLE IF NOT EXISTS `photo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `photo` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `visit_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_photos_visit1_idx` (`visit_id` ASC),
  CONSTRAINT `fk_photos_visit1`
    FOREIGN KEY (`visit_id`)
    REFERENCES `visit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `visit_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `visit_comment` ;

CREATE TABLE IF NOT EXISTS `visit_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(45) NOT NULL,
  `date` DATE NULL,
  `visit_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_visit_comment_visit1_idx` (`visit_id` ASC),
  CONSTRAINT `fk_visit_comment_visit1`
    FOREIGN KEY (`visit_id`)
    REFERENCES `visit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS traveler@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'traveler'@'localhost' IDENTIFIED BY 'traveler';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'traveler'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `location` (`id`, `country`, `city`, `image`, `lattitude`, `longitude`) VALUES (1, 'Vietnam', 'Ho Chi Minh', NULL, 10.762622, 106.660172);

COMMIT;


-- -----------------------------------------------------
-- Data for table `visit`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `visit` (`id`, `arrival_date`, `departure_date`, `note`, `location_id`, `photo`) VALUES (1, '2017-03-10', '2017-05-29', 'motorbike trip', 1, NULL);
INSERT INTO `visit` (`id`, `arrival_date`, `departure_date`, `note`, `location_id`, `photo`) VALUES (2, '2017-03-11', '2017-04-27', 'eating all the food', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `photo`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `photo` (`id`, `photo`, `description`, `visit_id`) VALUES (1, 'img', 'picture of motorbike', 1);
INSERT INTO `photo` (`id`, `photo`, `description`, `visit_id`) VALUES (2, 'img2', 'picture at the beach', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `visit_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `traveldb`;
INSERT INTO `visit_comment` (`id`, `comment`, `date`, `visit_id`) VALUES (1, 'bought a 150cc Honda', '2017-03-01', 1);
INSERT INTO `visit_comment` (`id`, `comment`, `date`, `visit_id`) VALUES (2, 'learned to ride the bike', '2017-03-02', 1);
INSERT INTO `visit_comment` (`id`, `comment`, `date`, `visit_id`) VALUES (3, 'went fishing with a new friend', '2017-03-29', 2);
INSERT INTO `visit_comment` (`id`, `comment`, `date`, `visit_id`) VALUES (4, 'played pool with the locals', '2017-03-30', 2);

COMMIT;

