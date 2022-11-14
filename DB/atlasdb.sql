-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema atlasdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `atlasdb` ;

-- -----------------------------------------------------
-- Schema atlasdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `atlasdb` DEFAULT CHARACTER SET utf8 ;
USE `atlasdb` ;

-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NULL,
  `image` VARCHAR(45) NULL,
  `latitude` DOUBLE NULL,
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
  `photo` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_visit_location_idx` (`location_id` ASC),
  CONSTRAINT `fk_visit_location`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `photos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `photos` ;

CREATE TABLE IF NOT EXISTS `photos` (
  `id` INT NOT NULL,
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
USE `atlasdb`;
INSERT INTO `location` (`id`, `country`, `city`, `image`, `latitude`, `longitude`) VALUES (1, 'Vietnam', 'Ho Chi Minh', '', 10.762622, 106.660172);
INSERT INTO `location` (`id`, `country`, `city`, `image`, `latitude`, `longitude`) VALUES (2, 'Colombia', 'Cartagena', NULL, 10.3932, 75.4832);
INSERT INTO `location` (`id`, `country`, `city`, `image`, `latitude`, `longitude`) VALUES (3, 'Italy', 'Venice', '', 45.4408, 12.3155);
INSERT INTO `location` (`id`, `country`, `city`, `image`, `latitude`, `longitude`) VALUES (4, 'Vietnam', 'Hoi An', '', 15.8801, 108.3380);
INSERT INTO `location` (`id`, `country`, `city`, `image`, `latitude`, `longitude`) VALUES (5, 'Italy', 'Rome', NULL, 41.9028, 12.4964);

COMMIT;


-- -----------------------------------------------------
-- Data for table `visit`
-- -----------------------------------------------------
START TRANSACTION;
USE `atlasdb`;
INSERT INTO `visit` (`id`, `arrival_date`, `departure_date`, `note`, `location_id`, `photo`) VALUES (1, '2017-03-10', '2017-05-29', 'Motorbike trip over Hai Van Pass', 1, 'https://tigitmotorbikes.com/wp-content/uploads/2016/05/rsz_20160428_135139.jpg');
INSERT INTO `visit` (`id`, `arrival_date`, `departure_date`, `note`, `location_id`, `photo`) VALUES (2, '2017-03-11', '2017-04-27', 'Eating food in Hoi An', 1, 'https://res.klook.com/image/upload/q_80/blogen/2018/03/viet-street-food1.png');
INSERT INTO `visit` (`id`, `arrival_date`, `departure_date`, `note`, `location_id`, `photo`) VALUES (3, '2022-06-10', '2022-07-15', 'Visiting the Doge Palace', 3, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1c/3b/33/2b/20201018-122621-largejpg.jpg?w=1200&h=-1&s=1');
INSERT INTO `visit` (`id`, `arrival_date`, `departure_date`, `note`, `location_id`, `photo`) VALUES (4, NULL, NULL, 'Swimming with manta rays', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `photos`
-- -----------------------------------------------------
START TRANSACTION;
USE `atlasdb`;
INSERT INTO `photos` (`id`, `photo`, `description`, `visit_id`) VALUES (1, 'img', 'picture of motorbike', 1);
INSERT INTO `photos` (`id`, `photo`, `description`, `visit_id`) VALUES (2, 'img2', 'picture at the beach', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `visit_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `atlasdb`;
INSERT INTO `visit_comment` (`id`, `comment`, `date`, `visit_id`) VALUES (1, 'bought a 150cc Honda', '2017-03-01', 1);
INSERT INTO `visit_comment` (`id`, `comment`, `date`, `visit_id`) VALUES (2, 'learned to ride the bike', '2017-03-02', 1);
INSERT INTO `visit_comment` (`id`, `comment`, `date`, `visit_id`) VALUES (3, 'went fishing with a new friend', '2017-03-29', 2);
INSERT INTO `visit_comment` (`id`, `comment`, `date`, `visit_id`) VALUES (4, 'played pool with the locals', '2017-03-30', 2);

COMMIT;

