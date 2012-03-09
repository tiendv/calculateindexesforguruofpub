package uit.pubguru.dbaccess;

/**
 * @author Huynh Ngoc Tin
 * @author Loc Do
 * @author Nghiep Tran
 */
import uit.pubguru.utility.PubGuruLogger;
import uit.pubguru.dbaccess.MapperDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import uit.pubguru.dto.AuthorDTO;
import uit.pubguru.dto.PaperDTO;
import uit.pubguru.dto.database.PubGuruDB;

public class PaperMapper extends MapperDB {
    public PaperMapper() throws Exception {
        super();
    }

    public PaperMapper(Connection con) {
        super(con);
    }

    /**
     * checkExistingStatusOfPublication
     * @param strPub
     * @return 0 --> not exist, 1 --> only URL existed, 2 --> all meta-data existed
     * @throws Exception 
     */
    public int[] checkExistingStatusOfPublication(String strPub) throws Exception {        
        int idPaper[] = {0, -1};
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" WHERE p.url = ?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, strPub);
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    String title = rs.getString("title");
                    if ((title == null) || (title.compareTo("") == 0))
                        idPaper[0] = 1; //URL existed, but title and other metadata are not
                    else
                        idPaper[0] = 2; //URL and other metadata are existed
                    
                    idPaper[1] = rs.getInt("idPaper");
                }
                stmt.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return idPaper;
    }

    /**
     * 
     * @param strPub
     * @return
     * @throws Exception 
     */
    public int isExisted(String strPub) throws Exception {        
        int idPaper = -1;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" WHERE p.url = ?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, strPub);
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    //có rồi nói chung
                    idPaper = rs.getInt("idPaper");
                }
                stmt.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return idPaper;
    }
        
    /**
     * check if the specified paper exist in the DB
     * @param paperDTO
     * @return isExist
     * @throws Exception
     */
    public int isExisted(PaperDTO paperDTO) throws Exception {        
        int idPaper = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" WHERE p.title = ? AND p.year = ?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, paperDTO.getTitle());
                stmt.setInt(2, paperDTO.getYear());
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {                    
                    idPaper = rs.getInt("idPaper");
                }
                stmt.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return idPaper;
    }

    /**
     * insert a new paper into the DB
     * @param authorDTO
     * @throws Exception
     */
    public int insertObj(PaperDTO paperDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO " + PubGuruDB.DBNAME + "." + "paper(doi, isbn, url, title, abstract, volume, pages, year, viewPublication, bibTex, endNote, idJournal, idConference, idMagazine, version, paperFile) ");
            sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, paperDTO.getDoi());
            stmt.setString(2, paperDTO.getIsbn());
            stmt.setString(3, paperDTO.getUrl());
            stmt.setString(4, paperDTO.getTitle());
            stmt.setString(5, paperDTO.getAbstractContent());
            stmt.setString(6, paperDTO.getVolume());
            stmt.setString(7, paperDTO.getPages());            
            if (paperDTO.getYear() == -1)
                stmt.setNull(8, java.sql.Types.INTEGER);
            else 
                stmt.setInt(8, paperDTO.getYear());
            stmt.setString(9, paperDTO.getViewPublication());
            stmt.setString(10, paperDTO.getBibTex());
            stmt.setString(11, paperDTO.getEndNote());
            if (paperDTO.getIdJournal() == -1)
                stmt.setNull(12, java.sql.Types.INTEGER);
            else 
                stmt.setInt(12, paperDTO.getIdJournal());
            if (paperDTO.getIdConference() == -1)
                stmt.setNull(13, java.sql.Types.INTEGER);
            else 
                stmt.setInt(13, paperDTO.getIdConference());
            if (paperDTO.getIdMagazine() == -1)
                stmt.setNull(14, java.sql.Types.INTEGER);
            else 
                stmt.setInt(14, paperDTO.getIdMagazine());
            if (paperDTO.getVersion() == -1)
                stmt.setNull(15, java.sql.Types.INTEGER);
            else 
                stmt.setInt(15, paperDTO.getVersion());
            stmt.setString(16, paperDTO.getPaperFile());
            stmt.execute();

            // get the last inserted ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            int insertPaperID = -1;
            if (rs != null && rs.next()) {
                insertPaperID = rs.getInt(1);
            }
                
            stmt.close();
            return insertPaperID;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
    }
    
    /**
     * updateObj
     * @param paperDTO
     * @return
     * @throws Exception 
     */
    public int updateObj(PaperDTO paperDTO) throws Exception {
    	 try {
    	     StringBuffer sql = new StringBuffer();
             sql.append("UPDATE " + PubGuruDB.DBNAME + "." + "paper");
             sql.append(" SET doi=?, isbn=?, url=?, title=?, abstract=?, volume=?," +
    		 		    " pages=?, year=?, viewPublication=?, bibTex=?, endNote=?," +
    		 		    " idJournal=?, idConference=?, idMagazine=?, version=?, paperFile=? ");
             sql.append(" WHERE idPaper=?");
    		 
             PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
             stmt.setString(1, paperDTO.getDoi());
             stmt.setString(2, paperDTO.getIsbn());
             stmt.setString(3, paperDTO.getUrl());
             stmt.setString(4, paperDTO.getTitle());
             stmt.setString(5, paperDTO.getAbstractContent());
             stmt.setString(6, paperDTO.getVolume());
             stmt.setString(7, paperDTO.getPages());            
            if (paperDTO.getYear() == -1)
                stmt.setNull(8, java.sql.Types.INTEGER);
            else 
                stmt.setInt(8, paperDTO.getYear());
            stmt.setString(9, paperDTO.getViewPublication());
            stmt.setString(10, paperDTO.getBibTex());
            stmt.setString(11, paperDTO.getEndNote());
            if (paperDTO.getIdJournal() == -1)
                stmt.setNull(12, java.sql.Types.INTEGER);
            else 
                stmt.setInt(12, paperDTO.getIdJournal());
            if (paperDTO.getIdConference() == -1)
                stmt.setNull(13, java.sql.Types.INTEGER);
            else 
                stmt.setInt(13, paperDTO.getIdConference());
            if (paperDTO.getIdMagazine() == -1)
                stmt.setNull(14, java.sql.Types.INTEGER);
            else 
                stmt.setInt(14, paperDTO.getIdMagazine());
            if (paperDTO.getVersion() == -1)
                stmt.setNull(15, java.sql.Types.INTEGER);
            else 
                stmt.setInt(15, paperDTO.getVersion());
             stmt.setString(16, paperDTO.getPaperFile());
             stmt.setInt(17, paperDTO.getIdPaper());
             
             Boolean isOk = stmt.execute();
             stmt.close();
             
             if (isOk) return 1; 
             else return 0;
    	 } catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
    		 throw ex;
    	 }    	 
    }
    
    /**
     * getObjWithID
     * @param idPaper
     * @return
     * @throws Exception 
     */
    public PaperDTO getObjWithID(int idPaper) throws Exception {
    	PaperDTO paperDTO = null;
    	try {
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT * FROM " + PubGuruDB.DBNAME + "." + "paper p");
    		sql.append(" WHERE p.idPaper=?");
    		
    		PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
    		stmt.setInt(1, idPaper);
    		
    		ResultSet rs = stmt.executeQuery();
    		if ((rs != null) && (rs.next())) {
    			paperDTO = new PaperDTO();
    			paperDTO.setIdPaper(idPaper);
    			paperDTO.setDoi(rs.getString("doi"));
    			paperDTO.setIsbn(rs.getString("isbn"));
    			paperDTO.setUrl(rs.getString("url"));
    			paperDTO.setTitle(rs.getString("title"));
    			paperDTO.setAbstractContent(rs.getString("abstract"));
    			paperDTO.setVolume(rs.getString("volume"));
    			paperDTO.setPages(rs.getString("pages"));
    			paperDTO.setYear(rs.getInt("year"));
    			paperDTO.setViewPublication(rs.getString("viewPublication"));
    			paperDTO.setBibTex(rs.getString("bibTex"));
    			paperDTO.setEndNote(rs.getString("endNote"));
    			paperDTO.setIdJournal(rs.getInt("idJournal"));
    			paperDTO.setIdConference(rs.getInt("idConference"));
    			paperDTO.setIdMagazine(rs.getInt("idMagazine"));
    			paperDTO.setVersion(rs.getInt("version"));
                        paperDTO.setPaperFile(rs.getString("paperFile"));
    		}
                stmt.close();
    	} catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
    		throw ex;
    	}
    	return paperDTO;
    }

    /**
     * getPaperDTOList
     * @param offset
     * @param limit
     * @return
     * @throws Exception 
     */
    public ArrayList getPaperDTOList(int offset, int limit) throws Exception{
        PaperDTO paperDTO = null;
        ArrayList paperDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" ORDER BY p.idPaper ASC LIMIT ?, ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                paperDTO = new PaperDTO();
                paperDTO.setIdPaper(rs.getInt("idPaper"));
                paperDTO.setDoi(rs.getString("doi"));
                paperDTO.setIsbn(rs.getString("isbn"));
                paperDTO.setUrl(rs.getString("url"));
                paperDTO.setTitle(rs.getString("title"));
                paperDTO.setAbstractContent(rs.getString("abstract"));
                paperDTO.setVolume(rs.getString("volume"));
                paperDTO.setPages(rs.getString("pages"));
                paperDTO.setYear(rs.getInt("year"));
                paperDTO.setViewPublication(rs.getString("viewPublication"));
                paperDTO.setBibTex(rs.getString("bibTex"));
                paperDTO.setEndNote(rs.getString("endNote"));
                paperDTO.setIdJournal(rs.getInt("idJournal"));
                paperDTO.setIdConference(rs.getInt("idConference"));
                paperDTO.setIdMagazine(rs.getInt("idMagazine"));
                paperDTO.setVersion(rs.getInt("version"));
                paperDTO.setPaperFile(rs.getString("paperFile"));
                paperDTOList.add(paperDTO);
            }

            stmt.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return paperDTOList;
    }

    /**
     * searchPaper
     * @param keyword
     * @return
     * @throws Exception 
     */
    public ArrayList searchPaper(String[] keywords, int limit) throws Exception {
        PaperDTO paperDTO = null;
        ArrayList paperDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" join " + PubGuruDB.DBNAME + "." + "author_paper ap on p.idPaper = ap.idPaper");
            sql.append(" join " + PubGuruDB.DBNAME + "." + "author a on ap.idAuthor = a.idAuthor");
            sql.append(" join " + PubGuruDB.DBNAME + "." + "conference c on p.idConference = c.idConference");
            sql.append(" join " + PubGuruDB.DBNAME + "." + "journal j on p.idJournal = j.idJournal");            
            sql.append(" where p.title like '" + keywords[0] + "'");
            for (int i = 1; i < keywords.length; i++) {
                sql.append(" or p.title like '" + keywords[i] + "'");
            }
            for (int i = 0; i < keywords.length; i++) {
                sql.append(" or a.authorName like '" + keywords[i] + "'");
            }
            for (int i = 0; i < keywords.length; i++) {
                sql.append(" or c.conferenceName like '" + keywords[i] + "'");
            }
            for (int i = 0; i < keywords.length; i++) {
                sql.append(" or j.journalName like '" + keywords[i] + "'");
            }
            sql.append(" limit ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                paperDTO = new PaperDTO();
                paperDTO.setIdPaper(rs.getInt("idPaper"));
                paperDTO.setDoi(rs.getString("doi"));
                paperDTO.setIsbn(rs.getString("isbn"));
                paperDTO.setUrl(rs.getString("url"));
                paperDTO.setTitle(rs.getString("title"));
                paperDTO.setAbstractContent(rs.getString("abstract"));
                paperDTO.setVolume(rs.getString("volume"));
                paperDTO.setPages(rs.getString("pages"));
                paperDTO.setYear(rs.getInt("year"));
                paperDTO.setViewPublication(rs.getString("viewPublication"));
                paperDTO.setBibTex(rs.getString("bibTex"));
                paperDTO.setEndNote(rs.getString("endNote"));
                paperDTO.setIdJournal(rs.getInt("idJournal"));
                paperDTO.setIdConference(rs.getInt("idConference"));
                paperDTO.setIdMagazine(rs.getInt("idMagazine"));
                paperDTO.setVersion(rs.getInt("version"));
                paperDTO.setPaperFile(rs.getString("paperFile"));
                paperDTOList.add(paperDTO);
            }

            stmt.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return paperDTOList;
    }

    /**
     * getAuthorDTOList
     * @param idPaper
     * @return
     * @throws Exception 
     */
    public ArrayList getAuthorDTOList(int idPaper) throws Exception {
        AuthorDTO authorDTO = null;
        ArrayList authorDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT distinct a.* FROM " + PubGuruDB.DBNAME + "." + "paper p join " + PubGuruDB.DBNAME + "." + "author_paper ap on p.idPaper = ap.idPaper");
            sql.append(" join " + PubGuruDB.DBNAME + "." + "author a on ap.idAuthor = a.idAuthor");
            sql.append(" where p.idPaper = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idPaper);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                authorDTO = new AuthorDTO();
                authorDTO.setIdAuthor(rs.getInt("idAuthor"));
                authorDTO.setAuthorName(rs.getString("authorName"));
                authorDTO.setImage(rs.getString("image"));
                authorDTO.setEmailAddress(rs.getString("emailAddress"));
                authorDTO.setWebsite(rs.getString("website"));
                authorDTO.setIdOrg(rs.getInt("idOrg"));
                authorDTO.setH_index(rs.getInt("h_index"));
                authorDTO.setG_index(rs.getInt("g_index"));
                authorDTO.setUrl(rs.getString("url"));
                authorDTOList.add(authorDTO);
            }

            stmt.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return authorDTOList;
    }

    /**
     * getCitationCount
     * @param idPaper
     * @return
     * @throws Exception 
     */
    public int getCitationCount(int idPaper) throws Exception {
        int citationCount = 0;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT rp.citationCount FROM " + PubGuruDB.DBNAME + "." + "paper p join " + PubGuruDB.DBNAME + "." + "_rank_paper rp on p.idPaper = rp.idPaper");
            sql.append(" where p.idPaper = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idPaper);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                citationCount = rs.getInt("citationCount");
            }

            stmt.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return citationCount;
    }
}
