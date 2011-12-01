SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `GuruOfPub` ;
CREATE SCHEMA IF NOT EXISTS `GuruOfPub` DEFAULT CHARACTER SET utf8 ;
USE `GuruOfPub` ;

-- -----------------------------------------------------
-- Table `GuruOfPub`.`Org`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Org` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Org` (
  `idOrg` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `orgName` VARCHAR(1000) NULL ,
  `website` VARCHAR(1000) NULL DEFAULT NULL ,
  `continent` VARCHAR(1000) NULL DEFAULT NULL ,
  `idOrgParent` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `h_index` INT(3) NULL DEFAULT NULL ,
  `url` VARCHAR(1000) NULL ,
  PRIMARY KEY (`idOrg`) ,
  INDEX `fk_Org_Org1` (`idOrgParent` ASC) ,
  INDEX `index_Org_url` (`url` ASC) ,
  CONSTRAINT `fk_Org_Org1`
    FOREIGN KEY (`idOrgParent` )
    REFERENCES `GuruOfPub`.`Org` (`idOrg` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Author` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Author` (
  `idAuthor` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `authorName` VARCHAR(1000) NULL ,
  `image` VARCHAR(1000) NULL DEFAULT NULL ,
  `emailAddress` VARCHAR(1000) NULL DEFAULT NULL ,
  `website` VARCHAR(1000) NULL DEFAULT NULL ,
  `idOrg` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `h_index` INT(3) NULL DEFAULT NULL ,
  `g_index` INT(3) NULL DEFAULT NULL ,
  `url` VARCHAR(1000) NULL ,
  PRIMARY KEY (`idAuthor`) ,
  INDEX `fk_Author_Org` (`idOrg` ASC) ,
  INDEX `index_Author_url` (`url` ASC) ,
  CONSTRAINT `fk_Author_Org`
    FOREIGN KEY (`idOrg` )
    REFERENCES `GuruOfPub`.`Org` (`idOrg` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Conference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Conference` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Conference` (
  `idConference` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `conferenceName` VARCHAR(1000) NULL ,
  `website` VARCHAR(1000) NULL DEFAULT NULL ,
  `organization` VARCHAR(1000) NULL DEFAULT NULL ,
  `organizedLocation` VARCHAR(1000) NULL DEFAULT NULL ,
  `duration` VARCHAR(1000) NULL DEFAULT NULL ,
  `yearStart` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `yearEnd` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `url` VARCHAR(1000) NULL ,
  PRIMARY KEY (`idConference`) ,
  INDEX `index_Conference_url` (`url` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Journal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Journal` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Journal` (
  `idJournal` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `journalName` VARCHAR(1000) NULL ,
  `website` VARCHAR(1000) NULL DEFAULT NULL ,
  `yearStart` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `yearEnd` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `organization` VARCHAR(1000) NULL DEFAULT NULL ,
  `url` VARCHAR(1000) NULL ,
  PRIMARY KEY (`idJournal`) ,
  INDEX `index_Journal_url` (`url` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Keyword` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Keyword` (
  `idKeyword` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `keyword` VARCHAR(1000) NULL ,
  `stemmingVariations` VARCHAR(10000) NULL DEFAULT NULL ,
  `url` VARCHAR(1000) NULL ,
  PRIMARY KEY (`idKeyword`) ,
  INDEX `index_Keyword_url` (`url` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Magazine`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Magazine` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Magazine` (
  `idMagazine` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `magazineName` VARCHAR(1000) NULL ,
  `website` VARCHAR(1000) NULL DEFAULT NULL ,
  `yearStart` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `yearEnd` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `organization` VARCHAR(1000) NULL DEFAULT NULL ,
  `url` VARCHAR(1000) NULL ,
  PRIMARY KEY (`idMagazine`) ,
  INDEX `index_Magazine_url` (`url` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Paper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Paper` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Paper` (
  `idPaper` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `doi` VARCHAR(100) NULL DEFAULT NULL ,
  `isbn` VARCHAR(100) NULL DEFAULT NULL ,
  `url` VARCHAR(1000) NULL DEFAULT NULL ,
  `title` VARCHAR(1000) NULL ,
  `abstract` MEDIUMBLOB NULL DEFAULT NULL ,
  `volume` VARCHAR(100) NULL DEFAULT NULL ,
  `pages` VARCHAR(100) NULL DEFAULT NULL ,
  `year` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `viewPublication` MEDIUMTEXT NULL DEFAULT NULL ,
  `bibTex` VARCHAR(1000) NULL DEFAULT NULL ,
  `endNote` VARCHAR(1000) NULL DEFAULT NULL ,
  `idJournal` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `idConference` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `idMagazine` INT(10) UNSIGNED NULL DEFAULT NULL ,
  `version` INT(3) NULL DEFAULT NULL ,
  `paperFile` VARCHAR(1000) NULL DEFAULT NULL ,
  PRIMARY KEY (`idPaper`) ,
  INDEX `fk_Paper_Journal1` (`idJournal` ASC) ,
  INDEX `fk_Paper_Conference1` (`idConference` ASC) ,
  INDEX `fk_Paper_Magazine1` (`idMagazine` ASC) ,
  INDEX `index_Paper_url` (`url` ASC) ,
  CONSTRAINT `fk_Paper_Journal1`
    FOREIGN KEY (`idJournal` )
    REFERENCES `GuruOfPub`.`Journal` (`idJournal` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paper_Conference1`
    FOREIGN KEY (`idConference` )
    REFERENCES `GuruOfPub`.`Conference` (`idConference` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paper_Magazine1`
    FOREIGN KEY (`idMagazine` )
    REFERENCES `GuruOfPub`.`Magazine` (`idMagazine` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Domain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Domain` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Domain` (
  `idDomain` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `domainName` VARCHAR(1000) NULL ,
  PRIMARY KEY (`idDomain`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Subdomain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Subdomain` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Subdomain` (
  `idSubdomain` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `subdomainName` VARCHAR(1000) NULL ,
  `idDomain` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idSubdomain`) ,
  INDEX `fk_Subdomain_Domain1` (`idDomain` ASC) ,
  CONSTRAINT `fk_Subdomain_Domain1`
    FOREIGN KEY (`idDomain` )
    REFERENCES `GuruOfPub`.`Domain` (`idDomain` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Paper_Keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Paper_Keyword` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Paper_Keyword` (
  `idPaper` INT(10) UNSIGNED NOT NULL ,
  `idKeyword` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idPaper`, `idKeyword`) ,
  INDEX `fk_Paper_has_Keyword_Keyword1` (`idKeyword` ASC) ,
  INDEX `fk_Paper_has_Keyword_Paper1` (`idPaper` ASC) ,
  CONSTRAINT `fk_Paper_has_Keyword_Paper1`
    FOREIGN KEY (`idPaper` )
    REFERENCES `GuruOfPub`.`Paper` (`idPaper` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paper_has_Keyword_Keyword1`
    FOREIGN KEY (`idKeyword` )
    REFERENCES `GuruOfPub`.`Keyword` (`idKeyword` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Paper_Paper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Paper_Paper` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Paper_Paper` (
  `idPaper` INT(10) UNSIGNED NOT NULL ,
  `idPaperRef` INT(10) UNSIGNED NOT NULL ,
  `citationContext` MEDIUMTEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`idPaper`, `idPaperRef`) ,
  INDEX `fk_Paper_has_Paper_Paper2` (`idPaperRef` ASC) ,
  INDEX `fk_Paper_has_Paper_Paper1` (`idPaper` ASC) ,
  CONSTRAINT `fk_Paper_has_Paper_Paper1`
    FOREIGN KEY (`idPaper` )
    REFERENCES `GuruOfPub`.`Paper` (`idPaper` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paper_has_Paper_Paper2`
    FOREIGN KEY (`idPaperRef` )
    REFERENCES `GuruOfPub`.`Paper` (`idPaper` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Author_Paper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Author_Paper` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Author_Paper` (
  `idAuthor` INT(10) UNSIGNED NOT NULL ,
  `idPaper` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idAuthor`, `idPaper`) ,
  INDEX `fk_Author_has_Paper_Paper1` (`idPaper` ASC) ,
  INDEX `fk_Author_has_Paper_Author1` (`idAuthor` ASC) ,
  CONSTRAINT `fk_Author_has_Paper_Author1`
    FOREIGN KEY (`idAuthor` )
    REFERENCES `GuruOfPub`.`Author` (`idAuthor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Author_has_Paper_Paper1`
    FOREIGN KEY (`idPaper` )
    REFERENCES `GuruOfPub`.`Paper` (`idPaper` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`PCMember`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`PCMember` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`PCMember` (
  `idPCMember` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `pcMemberName` VARCHAR(1000) NULL ,
  `image` VARCHAR(1000) NULL DEFAULT NULL ,
  `emailAddress` VARCHAR(1000) NULL DEFAULT NULL ,
  `website` VARCHAR(1000) NULL DEFAULT NULL ,
  `organization` VARCHAR(1000) NULL DEFAULT NULL ,
  PRIMARY KEY (`idPCMember`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Conference_PCMember`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Conference_PCMember` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Conference_PCMember` (
  `idConference` INT(10) UNSIGNED NOT NULL ,
  `idPCMember` INT(10) UNSIGNED NOT NULL ,
  `year` INT(10) UNSIGNED NULL DEFAULT NULL ,
  PRIMARY KEY (`idConference`, `idPCMember`) ,
  INDEX `fk_Conference_has_pcMember_pcMember1` (`idPCMember` ASC) ,
  INDEX `fk_Conference_has_pcMember_Conference1` (`idConference` ASC) ,
  CONSTRAINT `fk_Conference_has_pcMember_Conference1`
    FOREIGN KEY (`idConference` )
    REFERENCES `GuruOfPub`.`Conference` (`idConference` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Conference_has_pcMember_pcMember1`
    FOREIGN KEY (`idPCMember` )
    REFERENCES `GuruOfPub`.`PCMember` (`idPCMember` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`AuthorInstance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`AuthorInstance` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`AuthorInstance` (
  `autoID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `instanceName` VARCHAR(1000) NULL ,
  `idAuthor` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`autoID`) ,
  INDEX `fk_authorInstance_Author1` (`idAuthor` ASC) ,
  CONSTRAINT `fk_authorInstance_Author1`
    FOREIGN KEY (`idAuthor` )
    REFERENCES `GuruOfPub`.`Author` (`idAuthor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Comment` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Comment` (
  `idComment` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `rating` INT(1) NULL DEFAULT NULL ,
  `content` MEDIUMTEXT NULL DEFAULT NULL ,
  `idPaper` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idComment`) ,
  INDEX `fk_Comment_Paper1` (`idPaper` ASC) ,
  CONSTRAINT `fk_Comment_Paper1`
    FOREIGN KEY (`idPaper` )
    REFERENCES `GuruOfPub`.`Paper` (`idPaper` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Reviewer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Reviewer` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Reviewer` (
  `idReviewer` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `reviewerName` VARCHAR(1000) NULL ,
  `image` VARCHAR(1000) NULL DEFAULT NULL ,
  `emailAddress` VARCHAR(1000) NULL DEFAULT NULL ,
  `website` VARCHAR(1000) NULL DEFAULT NULL ,
  `organization` VARCHAR(1000) NULL DEFAULT NULL ,
  PRIMARY KEY (`idReviewer`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Paper_Reviewer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Paper_Reviewer` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Paper_Reviewer` (
  `idPaper` INT(10) UNSIGNED NOT NULL ,
  `idReviewer` INT(10) UNSIGNED NOT NULL ,
  `rating` INT(1) NULL ,
  `content` MEDIUMTEXT NULL ,
  PRIMARY KEY (`idPaper`, `idReviewer`) ,
  INDEX `fk_Paper_has_Reviewer_Reviewer1` (`idReviewer` ASC) ,
  INDEX `fk_Paper_has_Reviewer_Paper1` (`idPaper` ASC) ,
  CONSTRAINT `fk_Paper_has_Reviewer_Paper1`
    FOREIGN KEY (`idPaper` )
    REFERENCES `GuruOfPub`.`Paper` (`idPaper` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paper_has_Reviewer_Reviewer1`
    FOREIGN KEY (`idReviewer` )
    REFERENCES `GuruOfPub`.`Reviewer` (`idReviewer` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


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


-- -----------------------------------------------------
-- Table `GuruOfPub`.`Subdomain_Paper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuruOfPub`.`Subdomain_Paper` ;

CREATE  TABLE IF NOT EXISTS `GuruOfPub`.`Subdomain_Paper` (
  `idPaper` INT(10) UNSIGNED NOT NULL ,
  `idSubdomain` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idPaper`, `idSubdomain`) ,
  INDEX `fk_Subdomain_has_Paper_Paper1` (`idPaper` ASC) ,
  INDEX `fk_Subdomain_has_Paper_Subdomain1` (`idSubdomain` ASC) ,
  CONSTRAINT `fk_Subdomain_has_Paper_Subdomain1`
    FOREIGN KEY (`idSubdomain` )
    REFERENCES `GuruOfPub`.`Subdomain` (`idSubdomain` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subdomain_has_Paper_Paper1`
    FOREIGN KEY (`idPaper` )
    REFERENCES `GuruOfPub`.`Paper` (`idPaper` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
