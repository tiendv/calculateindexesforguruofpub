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
import uit.guruofpub.dto.PaperKeywordDTO;
/**
 * NOTE: don't set the index value automatic increasing
 * @author Loc Do
 * @author Nghiep Tran
 */
public class PaperKeywordMapper extends MapperDB {
    public PaperKeywordMapper() throws Exception {
        super();
    }

    public PaperKeywordMapper(Connection con) {
        super(con);
    }
    
    /**
     * insert a new PaperKeyword object into the DB
     * @param PaperKeywordDTO
     * @return the last inserted PaperKeyword id
     * @throws Exception
     */
    public int insertObj(PaperKeywordDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO guruofpub.Paper_Keyword(idPaper,"
                    + " idKeyword)");                                          
            sql.append(" VALUES(?, ?)");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, objDTO.getIdPaper());
            stmt.setInt(2, objDTO.getIdKeyword());            
            
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
     * check if the specified PaperKeyword exist in the DB
     * @param PaperKeywordDTO
     * @return -1 if not found
     * @return  n if found existed
     * @throws Exception
     */
    public int isExisted(PaperKeywordDTO objDTO) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.Paper_Keyword");
            sql.append(" WHERE idPaper=? AND idKeyword=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setInt(1, objDTO.getIdPaper());                
                stmt.setInt(2, objDTO.getIdKeyword());
                        
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = 1;
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
    
}

