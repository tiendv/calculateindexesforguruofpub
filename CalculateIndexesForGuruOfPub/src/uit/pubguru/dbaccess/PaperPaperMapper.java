/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.pubguru.dbaccess;

import uit.pubguru.utility.PubGuruLogger;
import uit.pubguru.dbaccess.MapperDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import uit.pubguru.dto.PaperPaperDTO;
import uit.pubguru.dto.database.PubGuruDB;
/**
 * NOTE: don't set the index value automatic increasing
 * @author Loc Do
 * @author Nghiep Tran
 */
public class PaperPaperMapper extends MapperDB {
    public PaperPaperMapper() throws Exception {
        super();
    }

    public PaperPaperMapper(Connection con) {
        super(con);
    }
    
    /**
     * insert a new PaperPaper object into the DB
     * @param PaperPaperDTO
     * @return the last inserted PaperPaper id
     * @throws Exception
     */
    public int insertObj(PaperPaperDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO " + PubGuruDB.DBNAME + "." + "Paper_Paper(idPaper,"
                    + " idPaperRef, citationContext)");                                          
            sql.append(" VALUES(?, ?, ?)");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, objDTO.getIdPaper());
            stmt.setInt(2, objDTO.getIdPaperRef());
            stmt.setString(3, objDTO.getCitationContext());
            
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
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
            throw ex;
        }
    }
    
    /**
     * check if the specified PaperPaper exist in the DB
     * @param PaperPaperDTO
     * @return -1 if not found
     * @return  n if found existed
     * @throws Exception
     */
    public int isExisted(PaperPaperDTO objDTO) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "Paper_Paper");
            sql.append(" WHERE idPaper=? AND idPaperRef=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setInt(1, objDTO.getIdPaper());                
                stmt.setInt(2, objDTO.getIdPaperRef());
                        
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = 1;
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

        return idObj;
    }
    
    /**
     * update the existing record in DB
     * @param PaperPaperDTO
     * @return 1 if update successfully
     * @return 0 if update unsuccessfully
     * @throws Exception
     */
    public int updateObj(PaperPaperDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE " + PubGuruDB.DBNAME + "." + "Paper_Paper");                                          
            sql.append(" SET citationContext=?");                    
            sql.append(" WHERE idPaper=? AND idPaperRef=?");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getCitationContext());
            stmt.setInt(2, objDTO.getIdPaper());
            stmt.setInt(3, objDTO.getIdPaperRef());
                        
            Boolean isOk = stmt.execute();

            stmt.close();            
            
            if (isOk) return 1; 
                else return 0;                        
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
}

