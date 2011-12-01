-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Author_Subdomain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Author_Subdomain` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Author_Subdomain` (
  `idAuthor` INT(10) NOT NULL ,
  `idSubdomain` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  `coAuthorCount` INT(10) NULL ,
  PRIMARY KEY (`idAuthor`, `idSubdomain`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Author` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Author` (
  `idAuthor` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  `coAuthorCount` INT(10) NULL ,
  `h_index` INT(10) NULL ,
  `g_index` INT(10) NULL ,
  PRIMARY KEY (`idAuthor`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Paper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Paper` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Paper` (
  `idPaper` INT(10) NOT NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idPaper`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Paper_Subdomain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Paper_Subdomain` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Paper_Subdomain` (
  `idPaper` INT(10) NOT NULL ,
  `idSubdomain` INT(10) NOT NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idPaper`, `idSubdomain`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Paper_Keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Paper_Keyword` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Paper_Keyword` (
  `idPaper` INT(10) NOT NULL ,
  `idKeyword` INT(10) NOT NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idPaper`, `idKeyword`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Author_Keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Author_Keyword` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Author_Keyword` (
  `idAuthor` INT(10) NOT NULL ,
  `idKeyword` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  `coAuthorCount` INT(10) NULL ,
  PRIMARY KEY (`idAuthor`, `idKeyword`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Org_Keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Org_Keyword` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Org_Keyword` (
  `idOrg` INT(10) NOT NULL ,
  `idKeyword` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  `authorCount` INT(10) NULL ,
  PRIMARY KEY (`idOrg`, `idKeyword`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Org_Subdomain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Org_Subdomain` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Org_Subdomain` (
  `idOrg` INT(10) NOT NULL ,
  `idSubdomain` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  `authorCount` INT(10) NULL ,
  PRIMARY KEY (`idOrg`, `idSubdomain`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Org`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Org` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Org` (
  `idOrg` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  `authorCount` INT(10) NULL ,
  `h_index` INT(10) NULL ,
  `g_index` INT(10) NULL ,
  PRIMARY KEY (`idOrg`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Conference_Keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Conference_Keyword` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Conference_Keyword` (
  `idConference` INT(10) NOT NULL ,
  `idKeyword` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idConference`, `idKeyword`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Conference_Subdomain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Conference_Subdomain` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Conference_Subdomain` (
  `idConference` INT(10) NOT NULL ,
  `idSubdomain` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idConference`, `idSubdomain`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Conference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Conference` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Conference` (
  `idConference` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idConference`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Journal_Keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Journal_Keyword` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Journal_Keyword` (
  `idJournal` INT(10) NOT NULL ,
  `idKeyword` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idJournal`, `idKeyword`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Journal_Subdomain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Journal_Subdomain` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Journal_Subdomain` (
  `idJournal` INT(10) NOT NULL ,
  `idSubdomain` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idJournal`, `idSubdomain`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Journal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Journal` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Journal` (
  `idJournal` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idJournal`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Magazine_Keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Magazine_Keyword` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Magazine_Keyword` (
  `idMagazine` INT(10) NOT NULL ,
  `idKeyword` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idMagazine`, `idKeyword`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Magazine_Subdomain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Magazine_Subdomain` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Magazine_Subdomain` (
  `idMagazine` INT(10) NOT NULL ,
  `idSubdomain` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idMagazine`, `idSubdomain`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`_Rank_Magazine`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`_Rank_Magazine` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`_Rank_Magazine` (
  `idMagazine` INT(10) NOT NULL ,
  `publicationCount` INT(10) NULL ,
  `citationCount` INT(10) NULL ,
  `rank` INT(10) NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idMagazine`) ,
  INDEX `index2` (`rank` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;