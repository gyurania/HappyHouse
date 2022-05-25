-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema happyhouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema happyhouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `happyhouse` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `happyhouse` ;

-- -----------------------------------------------------
-- Table `happyhouse`.`interest_apt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`interest_apt` (
  `userid` VARCHAR(50) NOT NULL,
  `code` VARCHAR(45) NULL,
  `aptName` VARCHAR(45) NULL,
  `aptCode` VARCHAR(45) NOT NULL,
  `dongName` VARCHAR(45) NULL,
  `jibun` VARCHAR(45) NULL,
  `year` VARCHAR(4) NOT NULL,
  `month` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userid`, `aptCode`),
  CONSTRAINT `fk_interest_apt_user1`
    FOREIGN KEY (`userid`)
    REFERENCES `happyhouse`.`user` (`id`)
    ON DELETE cascade
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `happyhouse`.`interest_apt_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`interest_apt_list` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(50) NOT NULL,
  `code` VARCHAR(45) NOT NULL,
  `aptName` VARCHAR(45) NULL,
  `aptCode` VARCHAR(45) NULL,
  `price` VARCHAR(45) NULL,
  `area` VARCHAR(45) NULL,
  `dongName` VARCHAR(45) NULL,
  `jibun` VARCHAR(45) NULL,
  `year` VARCHAR(4) NOT NULL,
  `month` VARCHAR(2) NOT NULL,
  `day` VARCHAR(2) NULL,
  PRIMARY KEY (`idx`),
  INDEX `fk_interest_apt_list_interest_apt1_idx` (`userid` ASC, `aptCode` ASC) VISIBLE,
  CONSTRAINT `fk_interest_apt_list_interest_apt1`
    FOREIGN KEY (`userid` , `aptCode`)
    REFERENCES `happyhouse`.`interest_apt` (`userid` , `aptCode`)
    ON DELETE cascade
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
