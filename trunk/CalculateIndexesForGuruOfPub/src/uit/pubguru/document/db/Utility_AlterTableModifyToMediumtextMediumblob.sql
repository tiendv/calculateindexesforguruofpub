alter table Paper modify abstract MEDIUMBLOB NULL DEFAULT NULL;
alter table Paper modify viewPublication MEDIUMTEXT NULL DEFAULT NULL;
alter table Comment modify content MEDIUMTEXT NULL DEFAULT NULL;
alter table Paper_Reviewer modify content MEDIUMTEXT NULL DEFAULT NULL;
alter table Paper_Paper modify citationContext MEDIUMTEXT NULL DEFAULT NULL;