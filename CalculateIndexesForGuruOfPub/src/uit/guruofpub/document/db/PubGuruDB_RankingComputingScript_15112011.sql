DELIMITER $$

DROP PROCEDURE IF EXISTS `PubGuru`.`ComputingRankingData` $$
CREATE PROCEDURE `PubGuru`.`ComputingRankingData` ()
BEGIN

-- Version 4: reduce number of rows, item which is not existing have all count numbers being 0.
-- Version 5: fix null error in  idOrg, ...

-- NOTE:
-- Before running this procedure, tune MySQL server to have cache, inmemory...

-- Clean up data
delete from paper_paper where idPaper = idPaperRef;

-- Truncate all old ranking data
truncate table _Rank_Paper;
truncate table _Rank_Paper_Subdomain;
truncate table _Rank_Paper_Keyword;
truncate table _Rank_Author;
truncate table _Rank_Author_Subdomain;
truncate table _Rank_Author_Keyword;
truncate table _Rank_Org;
truncate table _Rank_Org_Subdomain;
truncate table _Rank_Org_Keyword;
truncate table _Rank_Conference;
truncate table _Rank_Conference_Subdomain;
truncate table _Rank_Conference_Keyword;
truncate table _Rank_Journal;
truncate table _Rank_Journal_Subdomain;
truncate table _Rank_Journal_Keyword;
truncate table _Rank_Magazine;
truncate table _Rank_Magazine_Subdomain;
truncate table _Rank_Magazine_Keyword;

-- Insert count data
-- 1. TABLE _rank_paper, citationCount: all paper cite to this paper
BEGIN
insert ignore
into _rank_paper(idPaper, citationCount)
select pp.idPaperRef, count(distinct pp.idPaper)
from paper_paper pp
group by pp.idPaperRef;
END;

-- 2. TABLE _Rank_Paper_Subdomain, citationCount: citing paper are in each subdomain, 
-- count for all subdomain and paper, not only which exist in sudomain_paper. 
-- paper which has no citation in subdomain will has citationCount 0.
BEGIN
insert ignore 
into _Rank_Paper_Subdomain(idPaper, idSubdomain, citationCount)
select pp.idPaperRef, sp.idSubdomain, count(distinct pp.idPaper)
from paper_paper pp join subdomain_paper sp on pp.idPaper = sp.idPaper
group by pp.idPaperRef, sp.idSubdomain;
END;

-- 3. TABLE _Rank_Paper_Keyword
BEGIN
insert ignore 
into _Rank_Paper_Keyword(idPaper, idKeyword, citationCount)
select pp.idPaperRef, pk.idKeyword, count(distinct pp.idPaper)
from paper_paper pp join paper_keyword pk on pp.idPaper = pk.idPaper
group by pp.idPaperRef, pk.idKeyword;
END;

-- 4. TABLE _Rank_Author
BEGIN
-- count publicationCount
insert
into _Rank_Author(idAuthor, publicationCount, citationCount, coAuthorCount)
select ap.idAuthor, @publicationCount := count(distinct ap.idPaper), 0, 0
from author_paper ap
group by ap.idAuthor
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Author(idAuthor, publicationCount, citationCount, coAuthorCount)
select ap.idAuthor, 0, @citationCount := count(distinct pp.idPaper), 0
from author_paper ap join paper_paper pp on ap.idPaper = pp.idPaperRef
group by ap.idAuthor
on duplicate key update citationCount = @citationCount;

-- count coAuthorCount
insert
into _Rank_Author(idAuthor, publicationCount, citationCount, coAuthorCount)
select ap.idAuthor, 0, 0, @coAuthorCount := (count(distinct ap2.idAuthor) - 1 )
from author_paper ap join author_paper ap2 on ap.idPaper = ap2.idPaper
group by ap.idAuthor
on duplicate key update coAuthorCount = @coAuthorCount;
END;

-- 5. TABLE _Rank_Author_Subdomain
BEGIN
-- count publicationCount
insert
into _Rank_Author_Subdomain(idAuthor, idSubdomain, publicationCount, citationCount, coAuthorCount)
select ap.idAuthor, sp.idSubdomain, @publicationCount := count(distinct ap.idPaper), 0, 0
from author_paper ap join subdomain_paper sp on ap.idPaper = sp.idPaper
group by ap.idAuthor, sp.idSubdomain
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Author_Subdomain(idAuthor, idSubdomain, publicationCount, citationCount, coAuthorCount)
select ap.idAuthor, sp.idSubdomain, 0, @citationCount := count(distinct pp.idPaper), 0
from author_paper ap join paper_paper pp on ap.idPaper = pp.idPaperRef join subdomain_paper sp on pp.idPaper = sp.idPaper
group by ap.idAuthor, sp.idSubdomain
on duplicate key update citationCount = @citationCount;

-- count coAuthorCount
insert
into _Rank_Author_Subdomain(idAuthor, idSubdomain, publicationCount, citationCount, coAuthorCount)
select ap.idAuthor, sp.idSubdomain, 0, 0, @coAuthorCount := (count(distinct ap2.idAuthor) - 1 )
from author_paper ap join author_paper ap2 on ap.idPaper = ap2.idPaper join subdomain_paper sp on ap.idPaper = sp.idPaper
group by ap.idAuthor, sp.idSubdomain
on duplicate key update coAuthorCount = @coAuthorCount;
END;

-- 6. TABLE _Rank_Author_Keyword
BEGIN
-- count publicationCount
insert
into _Rank_Author_Keyword(idAuthor, idKeyword, publicationCount, citationCount, coAuthorCount)
select ap.idAuthor, pk.idKeyword, @publicationCount := count(distinct ap.idPaper), 0, 0
from author_paper ap join paper_keyword pk on ap.idPaper = pk.idPaper
group by ap.idAuthor, pk.idKeyword
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Author_Keyword(idAuthor, idKeyword, publicationCount, citationCount, coAuthorCount)
select ap.idAuthor, pk.idKeyword, 0, @citationCount := count(distinct pp.idPaper), 0
from author_paper ap join paper_paper pp on ap.idPaper = pp.idPaperRef join paper_keyword pk on pp.idPaper = pk.idPaper
group by ap.idAuthor, pk.idKeyword
on duplicate key update citationCount = @citationCount;

-- count coAuthorCount
insert
into _Rank_Author_Keyword(idAuthor, idKeyword, publicationCount, citationCount, coAuthorCount)
select ap.idAuthor, pk.idKeyword, 0, 0, @coAuthorCount := (count(distinct ap2.idAuthor) - 1 )
from author_paper ap join author_paper ap2 on ap.idPaper = ap2.idPaper join paper_keyword pk on ap.idPaper = pk.idPaper
group by ap.idAuthor, pk.idKeyword
on duplicate key update coAuthorCount = @coAuthorCount;
END;

-- 7. TABLE _Rank_Org
BEGIN
-- count authorCount
insert
into _Rank_Org(idOrg, authorCount, publicationCount, citationCount)
select a.idOrg, @authorCount := count(distinct a.idAuthor), 0, 0
from author a
where a.idOrg is not null
group by a.idOrg
on duplicate key update authorCount = @authorCount;

-- count publicationCount
insert
into _Rank_Org(idOrg, authorCount, publicationCount, citationCount)
select a.idOrg, 0, @publicationCount := count(distinct ap.idPaper), 0
from author a join author_paper ap on a.idAuthor = ap.idAuthor
where a.idOrg is not null
group by a.idOrg
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Org(idOrg, authorCount, publicationCount, citationCount)
select a.idOrg, 0, 0, @citationCount := count(distinct pp.idPaper)
from author a join author_paper ap on a.idAuthor = ap.idAuthor join paper_paper pp on ap.idPaper = pp.idPaperRef
where a.idOrg is not null
group by a.idOrg
on duplicate key update citationCount = @citationCount;
END;

-- 8. TABLE _Rank_Org_Subdomain
BEGIN
-- count authorCount
insert
into _Rank_Org_Subdomain(idOrg, idSubdomain, authorCount, publicationCount, citationCount)
select a.idOrg, sp.idSubdomain, @authorCount := count(distinct a.idAuthor), 0, 0
from author a join author_paper ap on a.idAuthor = ap.idAuthor join subdomain_paper sp on ap.idPaper = sp.idPaper
where a.idOrg is not null
group by a.idOrg, sp.idSubdomain
on duplicate key update authorCount = @authorCount;

-- count publicationCount
insert
into _Rank_Org_Subdomain(idOrg, idSubdomain, authorCount, publicationCount, citationCount)
select a.idOrg, sp.idSubdomain, 0, @publicationCount := count(distinct ap.idPaper), 0
from author a join author_paper ap on a.idAuthor = ap.idAuthor join subdomain_paper sp on ap.idPaper = sp.idPaper
where a.idOrg is not null
group by a.idOrg, sp.idSubdomain
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Org_Subdomain(idOrg, idSubdomain, authorCount, publicationCount, citationCount)
select a.idOrg, sp.idSubdomain, 0, 0, @citationCount := count(distinct pp.idPaper)
from author a join author_paper ap on a.idAuthor = ap.idAuthor join paper_paper pp on ap.idPaper = pp.idPaperRef join subdomain_paper sp on pp.idPaper = sp.idPaper
where a.idOrg is not null
group by a.idOrg, sp.idSubdomain
on duplicate key update citationCount = @citationCount;
END;

-- 9. TABLE _Rank_Org_Keyword
BEGIN
-- count authorCount
insert
into _Rank_Org_Keyword(idOrg, idKeyword, authorCount, publicationCount, citationCount)
select a.idOrg, pk.idKeyword, @authorCount := count(distinct a.idAuthor), 0, 0
from author a join author_paper ap on a.idAuthor = ap.idAuthor join paper_keyword pk on ap.idPaper = pk.idPaper
where a.idOrg is not null
group by a.idOrg, pk.idKeyword
on duplicate key update authorCount = @authorCount;

-- count publicationCount
insert
into _Rank_Org_Keyword(idOrg, idKeyword, authorCount, publicationCount, citationCount)
select a.idOrg, pk.idKeyword, 0, @publicationCount := count(distinct ap.idPaper), 0
from author a join author_paper ap on a.idAuthor = ap.idAuthor join paper_keyword pk on ap.idPaper = pk.idPaper
where a.idOrg is not null
group by a.idOrg, pk.idKeyword
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Org_Keyword(idOrg, idKeyword, authorCount, publicationCount, citationCount)
select a.idOrg, pk.idKeyword, 0, 0, @citationCount := count(distinct pp.idPaper)
from author a join author_paper ap on a.idAuthor = ap.idAuthor join paper_paper pp on ap.idPaper = pp.idPaperRef join paper_keyword pk on pp.idPaper = pk.idPaper
where a.idOrg is not null
group by a.idOrg, pk.idKeyword
on duplicate key update citationCount = @citationCount;
END;

-- 10. TABLE _Rank_Conference
BEGIN
-- count publicationCount
insert
into _Rank_Conference(idConference, publicationCount, citationCount)
select p.idConference, @publicationCount := count(distinct p.idPaper), 0
from paper p
where p.idConference is not null
group by p.idConference
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Conference(idConference, publicationCount, citationCount)
select p.idConference, 0, @citationCount := count(distinct pp.idPaper)
from paper p join paper_paper pp on p.idPaper = pp.idPaperRef
where p.idConference is not null
group by p.idConference
on duplicate key update citationCount = @citationCount;
END;

-- 11. TABLE _Rank_Conference_Subdomain
BEGIN
-- count publicationCount
insert
into _Rank_Conference_Subdomain(idConference, idSubdomain, publicationCount, citationCount)
select p.idConference, sp.idSubdomain, @publicationCount := count(distinct p.idPaper), 0
from paper p join subdomain_paper sp on p.idPaper = sp.idPaper
where p.idConference is not null
group by p.idConference, sp.idSubdomain
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Conference_Subdomain(idConference, idSubdomain, publicationCount, citationCount)
select p.idConference, sp.idSubdomain, 0, @citationCount := count(distinct pp.idPaper)
from paper p join paper_paper pp on p.idPaper = pp.idPaperRef join subdomain_paper sp on pp.idPaper = sp.idPaper
where p.idConference is not null
group by p.idConference, sp.idSubdomain
on duplicate key update citationCount = @citationCount;
END;

-- 12. TABLE _Rank_Conference_Keyword
BEGIN
-- count publicationCount
insert
into _Rank_Conference_Keyword(idConference, idKeyword, publicationCount, citationCount)
select p.idConference, pk.idKeyword, @publicationCount := count(distinct p.idPaper), 0
from paper p join paper_keyword pk on p.idPaper = pk.idPaper
where p.idConference is not null
group by p.idConference, pk.idKeyword
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Conference_Keyword(idConference, idKeyword, publicationCount, citationCount)
select p.idConference, pk.idKeyword, 0, @citationCount := count(distinct pp.idPaper)
from paper p join paper_paper pp on p.idPaper = pp.idPaperRef join paper_keyword pk on pp.idPaper = pk.idPaper
where p.idConference is not null
group by p.idConference, pk.idKeyword
on duplicate key update citationCount = @citationCount;
END;

-- 13. TABLE _Rank_Journal
BEGIN
-- count publicationCount
insert
into _Rank_Journal(idJournal, publicationCount, citationCount)
select p.idJournal, @publicationCount := count(distinct p.idPaper), 0
from paper p
where p.idJournal is not null
group by p.idJournal
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Journal(idJournal, publicationCount, citationCount)
select p.idJournal, 0, @citationCount := count(distinct pp.idPaper)
from paper p join paper_paper pp on p.idPaper = pp.idPaperRef
where p.idJournal is not null
group by p.idJournal
on duplicate key update citationCount = @citationCount;
END;

-- 14. TABLE _Rank_Journal_Subdomain
BEGIN
-- count publicationCount
insert
into _Rank_Journal_Subdomain(idJournal, idSubdomain, publicationCount, citationCount)
select p.idJournal, sp.idSubdomain, @publicationCount := count(distinct p.idPaper), 0
from paper p join subdomain_paper sp on p.idPaper = sp.idPaper
where p.idJournal is not null
group by p.idJournal, sp.idSubdomain
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Journal_Subdomain(idJournal, idSubdomain, publicationCount, citationCount)
select p.idJournal, sp.idSubdomain, 0, @citationCount := count(distinct pp.idPaper)
from paper p join paper_paper pp on p.idPaper = pp.idPaperRef join subdomain_paper sp on pp.idPaper = sp.idPaper
where p.idJournal is not null
group by p.idJournal, sp.idSubdomain
on duplicate key update citationCount = @citationCount;
END;

-- 15. TABLE _Rank_Journal_Keyword
BEGIN
-- count publicationCount
insert
into _Rank_Journal_Keyword(idJournal, idKeyword, publicationCount, citationCount)
select p.idJournal, pk.idKeyword, @publicationCount := count(distinct p.idPaper), 0
from paper p join paper_keyword pk on p.idPaper = pk.idPaper
where p.idJournal is not null
group by p.idJournal, pk.idKeyword
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Journal_Keyword(idJournal, idKeyword, publicationCount, citationCount)
select p.idJournal, pk.idKeyword, 0, @citationCount := count(distinct pp.idPaper)
from paper p join paper_paper pp on p.idPaper = pp.idPaperRef join paper_keyword pk on pp.idPaper = pk.idPaper
where p.idJournal is not null
group by p.idJournal, pk.idKeyword
on duplicate key update citationCount = @citationCount;
END;

-- 16. TABLE _Rank_Magazine
BEGIN
-- count publicationCount
insert
into _Rank_Magazine(idMagazine, publicationCount, citationCount)
select p.idMagazine, @publicationCount := count(distinct p.idPaper), 0
from paper p
where p.idMagazine is not null
group by p.idMagazine
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Magazine(idMagazine, publicationCount, citationCount)
select p.idMagazine, 0, @citationCount := count(distinct pp.idPaper)
from paper p join paper_paper pp on p.idPaper = pp.idPaperRef
where p.idMagazine is not null
group by p.idMagazine
on duplicate key update citationCount = @citationCount;
END;

-- 17. TABLE _Rank_Magazine_Subdomain
BEGIN
-- count publicationCount
insert
into _Rank_Magazine_Subdomain(idMagazine, idSubdomain, publicationCount, citationCount)
select p.idMagazine, sp.idSubdomain, @publicationCount := count(distinct p.idPaper), 0
from paper p join subdomain_paper sp on p.idPaper = sp.idPaper
where p.idMagazine is not null
group by p.idMagazine, sp.idSubdomain
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Magazine_Subdomain(idMagazine, idSubdomain, publicationCount, citationCount)
select p.idMagazine, sp.idSubdomain, 0, @citationCount := count(distinct pp.idPaper)
from paper p join paper_paper pp on p.idPaper = pp.idPaperRef join subdomain_paper sp on pp.idPaper = sp.idPaper
where p.idMagazine is not null
group by p.idMagazine, sp.idSubdomain
on duplicate key update citationCount = @citationCount;
END;

-- 18. TABLE _Rank_Magazine_Keyword
BEGIN
-- count publicationCount
insert
into _Rank_Magazine_Keyword(idMagazine, idKeyword, publicationCount, citationCount)
select p.idMagazine, pk.idKeyword, @publicationCount := count(distinct p.idPaper), 0
from paper p join paper_keyword pk on p.idPaper = pk.idPaper
where p.idMagazine is not null
group by p.idMagazine, pk.idKeyword
on duplicate key update publicationCount = @publicationCount;

-- count citationCount
insert
into _Rank_Magazine_Keyword(idMagazine, idKeyword, publicationCount, citationCount)
select p.idMagazine, pk.idKeyword, 0, @citationCount := count(distinct pp.idPaper)
from paper p join paper_paper pp on p.idPaper = pp.idPaperRef join paper_keyword pk on pp.idPaper = pk.idPaper
where p.idMagazine is not null
group by p.idMagazine, pk.idKeyword
on duplicate key update citationCount = @citationCount;
END;

END $$

DELIMITER ;
