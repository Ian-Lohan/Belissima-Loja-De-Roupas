-- MySQL Script generated by MySQL Workbench
-- Wed May 10 21:50:05 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema loja_de_roupas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema loja_de_roupas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `loja_de_roupas` DEFAULT CHARACTER SET utf8 ;
USE `loja_de_roupas` ;

-- -----------------------------------------------------
-- Table `loja_de_roupas`.`Funcionarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_de_roupas`.`Funcionarios` (
  `f_id` INT NOT NULL AUTO_INCREMENT,
  `f_nome` VARCHAR(45) NOT NULL,
  `f_cpf` INT(11) NOT NULL,
  `f_numero` INT(11) NOT NULL,
  `f_endereco` VARCHAR(60) NOT NULL,
  `senha` VARCHAR(15) NOT NULL,
  `f_nivelacesso` INT(2) NOT NULL,
  PRIMARY KEY (`f_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja_de_roupas`.`Produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_de_roupas`.`Produtos` (
  `p_id` INT NOT NULL AUTO_INCREMENT,
  `p_nome` VARCHAR(45) NOT NULL,
  `p_tipo` VARCHAR(45) NOT NULL,
  `p_subtipo` VARCHAR(45) NULL,
  `p_tecido` VARCHAR(45) NULL,
  `p_cor` VARCHAR(45) NOT NULL,
  `p_preco` DECIMAL(10,2) NOT NULL,
  `p_estoque` INT NOT NULL,
  PRIMARY KEY (`p_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja_de_roupas`.`Vendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_de_roupas`.`Vendas` (
  `v_id` INT NOT NULL AUTO_INCREMENT,
  `v_data` DATE NOT NULL,
  `v_formapagamento` VARCHAR(45) NOT NULL,
  `c_id` INT NOT NULL,
  `f_id` INT NOT NULL,
  PRIMARY KEY (`v_id`),
  INDEX `f_id_idx` (`f_id` ASC) VISIBLE,
  CONSTRAINT `f_id`
    FOREIGN KEY (`f_id`)
    REFERENCES `loja_de_roupas`.`Funcionarios` (`f_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja_de_roupas`.`Clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_de_roupas`.`Clientes` (
  `c_id` INT NOT NULL AUTO_INCREMENT,
  `c_nome` VARCHAR(45) NOT NULL,
  `c_cpf` INT(11) NOT NULL,
  `c_numero` INT(11) NULL,
  `c_endereco` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`c_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja_de_roupas`.`DetalhesVendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_de_roupas`.`DetalhesVendas` (
  `v_id` INT NOT NULL,
  `p_id` INT NOT NULL,
  `quantidade` INT NOT NULL,
  `precounitario` DEC(10,2) NOT NULL,
  `subtotal` DECIMAL(10,2) GENERATED ALWAYS AS (quantidade * preco_unitario) STORED,
  PRIMARY KEY (`v_id`, `p_id`),
  INDEX `p_id_idx` (`p_id` ASC) VISIBLE,
  CONSTRAINT `v_id`
    FOREIGN KEY (`v_id`)
    REFERENCES `loja_de_roupas`.`Vendas` (`v_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `p_id`
    FOREIGN KEY (`p_id`)
    REFERENCES `loja_de_roupas`.`Produtos` (`p_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
