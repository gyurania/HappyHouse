-- -----------------------------------------------------
-- Table `happyhouse`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`notice` (
  `notice_no` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `userid` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`notice_no`),
  INDEX `fk_notice_user1_idx` (`userid` ASC) VISIBLE,
  CONSTRAINT `fk_notice_user1`
    FOREIGN KEY (`userid`)
    REFERENCES `happyhouse`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;