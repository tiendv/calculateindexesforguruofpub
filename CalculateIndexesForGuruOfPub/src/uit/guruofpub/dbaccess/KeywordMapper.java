/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uit.guruofpub.dbaccess;

import uit.guruofpub.utility.GuruOfPubLogger;
import uit.guruofpub.dbaccess.MapperDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import uit.guruofpub.dto.KeywordDTO;

/**
 *
 * @author Loc Do
 * @author Nghiep Tran
 */
public class KeywordMapper extends MapperDB {
    public KeywordMapper() throws Exception {
        super();
    }

    public KeywordMapper(Connection con) {
        super(con);
    }
    
    /**
     * insert a new Keyword object into the DB
     * @param KeywordDTO
     * @return the last inserted Keyword id
     * @throws Exception
     */
    public int insertObj(KeywordDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO guruofpub.Keyword(keyword,"
                    + " stemmingVariations, url)");                                          
            sql.append(" VALUES(?,?,?)");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getKeyword());
            stmt.setString(2, objDTO.getStemmingVariations());
            stmt.setString(3, objDTO.getUrl());
            stmt.execute();

            // get the last inserted ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            int insertedID = -1;
            if (rs != null && rs.next())
                insertedID = rs.getInt(1);

            stmt.close();
            return insertedID;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
    }
    
    /**
     * check if the specified Keyword exist in the DB
     * @param KeywordDTO
     * @return -1 if not found
     * @return  n if found existed
     * @throws Exception
     */
    public int isExisted(KeywordDTO objDTO) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.Keyword");
            sql.append(" WHERE url=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, objDTO.getUrl());                
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = rs.getInt("idKeyword");                    
                }
                stmt.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return idObj;
    }
    
    /**
     * Check if existed
     * @param url
     * @return
     * @throws Exception 
     */
    public int isExisted(String url) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.Keyword");
            sql.append(" WHERE url=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, url);                
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = rs.getInt("idKeyword");                    
                }
                stmt.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return idObj;
    }
    /**
     * update the existing record in DB
     * @param KeywordDTO
     * @return 1 if update successfully
     * @return 0 if update unsuccessfully
     * @throws Exception
     */
    public int updateObj(KeywordDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE guruofpub.Keyword");                                          
            sql.append(" SET keyword=?, stemmingVariations=?, url=?");                    
            sql.append(" WHERE idKeyword=?");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getKeyword());
            stmt.setString(2, objDTO.getStemmingVariations());
            stmt.setString(3, objDTO.getUrl());
            stmt.setInt(4,objDTO.getIdKeyword());
            
            Boolean isOk = stmt.execute();

            stmt.close();            
            
            if (isOk) return 1; 
                else return 0;                        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
    }
    
    /**
     * getKeywordDTO
     * @param idKeyword
     * @return
     * @throws Exception 
     */
    public KeywordDTO getKeywordDTO(int idKeyword) throws Exception{
        KeywordDTO keywordDTO = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.Keyword k");
            sql.append(" WHERE k.idKeyword = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idKeyword);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                keywordDTO = new KeywordDTO();
                keywordDTO.setIdKeyword(idKeyword);
                keywordDTO.setKeyword(rs.getString("keyword"));
                keywordDTO.setStemmingVariations(rs.getString("stemmingVariations"));
                keywordDTO.setUrl(rs.getString("url"));
            }

            stmt.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return keywordDTO;        
    }
    
    /**
     * getKeywordDTOList
     * @param offset
     * @param limit
     * @return
     * @throws Exception 
     */
    public ArrayList getKeywordDTOList(int offset, int limit) throws Exception{
        KeywordDTO keywordDTO = null;
        ArrayList keywordDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.Keyword k");
            sql.append(" ORDER BY k.idKeyword ASC LIMIT ?, ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                keywordDTO = new KeywordDTO();
                keywordDTO.setIdKeyword(rs.getInt("idKeyword"));
                keywordDTO.setKeyword(rs.getString("keyword"));
                keywordDTO.setStemmingVariations(rs.getString("stemmingVariations"));
                keywordDTO.setUrl(rs.getString("url"));
                keywordDTOList.add(keywordDTO);
            }

            stmt.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return keywordDTOList;
    }

    public ArrayList getKeywordIdList() throws Exception{
        ArrayList keywordIdList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT k.idKeyword FROM guruofpub.Keyword k ORDER BY k.idKeyword");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                keywordIdList.add(rs.getInt("idKeyword"));
            }

            stmt.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }

        return keywordIdList;
    }
}
