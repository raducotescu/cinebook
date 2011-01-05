SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `cinebook` DEFAULT CHARACTER SET utf8 ;
USE `cinebook` ;

-- -----------------------------------------------------
-- Table `cinebook`.`User`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinebook`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `password` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `first_name` VARCHAR(45) NOT NULL ,
  `last_name` VARCHAR(45) NOT NULL ,
  `role` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinebook`.`Movie`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinebook`.`Movie` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(128) NOT NULL ,
  `genre` VARCHAR(45) NOT NULL ,
  `director` VARCHAR(45) NOT NULL ,
  `cast` VARCHAR(160) NOT NULL ,
  `description` VARCHAR(1000) NOT NULL ,
  `duration` INT NOT NULL ,
  `poster_location` VARCHAR(256) NOT NULL ,
  `rating` VARCHAR(45) NOT NULL ,
  `user_rating` FLOAT NULL ,
  `year` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinebook`.`Schedule`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinebook`.`Schedule` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `start_time` DATETIME NOT NULL ,
  `theatre` INT NOT NULL ,
  `movie` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Schedule_Movie` (`movie` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Schedule_Movie`
    FOREIGN KEY (`movie` )
    REFERENCES `cinebook`.`Movie` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinebook`.`Booking`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinebook`.`Booking` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  `schedule_entry_id` INT NOT NULL ,
  `seats` INT NOT NULL ,
  `status` INT NOT NULL ,
  `movie_user_rating` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Booking_User` (`user_id` ASC) ,
  INDEX `fk_Booking_Schedule` (`schedule_entry_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_Booking_User`
    FOREIGN KEY (`user_id` )
    REFERENCES `cinebook`.`User` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Booking_Schedule`
    FOREIGN KEY (`schedule_entry_id` )
    REFERENCES `cinebook`.`Schedule` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinebook`.`MovieComment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinebook`.`MovieComment` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  `movie_id` INT NOT NULL ,
  `comment_text` VARCHAR(1000) NOT NULL ,
  `date_posted` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_MovieComment_User` (`user_id` ASC) ,
  INDEX `fk_MovieComment_Movie` (`movie_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_MovieComment_User`
    FOREIGN KEY (`user_id` )
    REFERENCES `cinebook`.`User` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MovieComment_Movie`
    FOREIGN KEY (`movie_id` )
    REFERENCES `cinebook`.`Movie` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinebook`.`Friend`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinebook`.`Friend` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  `friend_id` INT NOT NULL ,
  `status` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Friend_User1` (`user_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_Friend_User2` (`friend_id` ASC) ,
  CONSTRAINT `fk_Friend_User1`
    FOREIGN KEY (`user_id` )
    REFERENCES `cinebook`.`User` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Friend_User2`
    FOREIGN KEY (`friend_id` )
    REFERENCES `cinebook`.`User` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinebook`.`SEQUENCER`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinebook`.`SEQUENCER` (
  `SEQUENCE_NAME` VARCHAR(30) NOT NULL ,
  `SEQUENCE_COUNTER` INT NOT NULL ,
  PRIMARY KEY (`SEQUENCE_NAME`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinebook`.`UserComment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinebook`.`UserComment` (
  `id` INT NOT NULL ,
  `postedBy` INT NOT NULL ,
  `postedTo` INT NOT NULL ,
  `rating` FLOAT NULL ,
  `comment_text` VARCHAR(1000) NOT NULL ,
  `date_posted` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_UserComments_ByUser` (`postedBy` ASC) ,
  INDEX `fk_UserComments_ToUser` (`postedTo` ASC) ,
  CONSTRAINT `fk_UserComments_ByUser`
    FOREIGN KEY (`postedBy` )
    REFERENCES `cinebook`.`User` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserComments_ToUser`
    FOREIGN KEY (`postedTo` )
    REFERENCES `cinebook`.`User` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
