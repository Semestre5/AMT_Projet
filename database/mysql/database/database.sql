-- MySQL Script generated by MySQL Workbench
-- Mon Nov  8 14:27:04 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DECIMAL(8,2) NULL,
  `description` TEXT NULL,
  `name` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL DEFAULT 0,
  `link` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`article_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`article_category` (
  `idArticle` INT NOT NULL,
  `idCategory` INT NOT NULL,
  PRIMARY KEY (`idArticle`, `idCategory`),
  INDEX `fk_article_has_category_category1_idx` (`idCategory` ASC) VISIBLE,
  INDEX `fk_article_has_category_article_idx` (`idArticle` ASC) VISIBLE,
  CONSTRAINT `fk_article_has_category_article`
    FOREIGN KEY (`idArticle`)
    REFERENCES `mydb`.`article` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_article_has_category_category1`
    FOREIGN KEY (`idCategory`)
    REFERENCES `mydb`.`category` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cart` (
  `id` INT NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC) VISIBLE,
  CONSTRAINT `idUser_User`
    FOREIGN KEY (`idUser`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`article_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`article_cart` (
  `idActicle` INT NOT NULL,
  `idCart` INT NOT NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`idActicle`, `idCart`),
  INDEX `fk_article_has_cart_cart1_idx` (`idCart` ASC) VISIBLE,
  INDEX `fk_article_has_cart_article1_idx` (`idActicle` ASC) VISIBLE,
  CONSTRAINT `fk_article_has_cart_article1`
    FOREIGN KEY (`idActicle`)
    REFERENCES `mydb`.`article` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_article_has_cart_cart1`
    FOREIGN KEY (`idCart`)
    REFERENCES `mydb`.`cart` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
