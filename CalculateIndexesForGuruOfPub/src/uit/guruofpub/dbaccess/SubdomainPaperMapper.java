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
import uit.guruofpub.dto.SubdomainPaperDTO;
/**
 * NOTE: don't set the index value automatic increasing
 * @author Loc Do
 * @author Nghiep Tran
 */
public class SubdomainPaperMapper extends MapperDB {
    public SubdomainPaperMapper() throws Exception {
        super();
    }

    public SubdomainPaperMapper(Connection con) {
        super(con);
    }
    
    /**
     * insert a new SubdomainPaper object into the DB
     * @param SubdomainPaperDTO
     * @return 1 --> success, -1 --> fail
     * @throws Exception
     */
    public int insertObj(SubdomainPaperDTO objDTO) throws Exception {
        int result = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO guruofpub.Subdomain_Paper(idPaper, idSubdomain)");                                          
            sql.append(" VALUES(?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, objDTO.getIdPaper());
            stmt.setInt(2, objDTO.getIdSubdomain());            
            stmt.execute();
            stmt.close();
            result = 1;
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
        return result;
    }
    
    /**
     * check if the specified SubdomainPaper exist in the DB
     * @param SubdomainPaperDTO
     * @return -1 if not found
     * @return  1 if found existed
     * @throws Exception
     */
    public int isExisted(SubdomainPaperDTO objDTO) throws Exception {        
        int found = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.Subdomain_Paper");
            sql.append(" WHERE idPaper=? AND idSubdomain=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setInt(1, objDTO.getIdPaper());                
                stmt.setInt(2, objDTO.getIdSubdomain());
                        
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    found = 1;
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

        return found;
    }
    
}
