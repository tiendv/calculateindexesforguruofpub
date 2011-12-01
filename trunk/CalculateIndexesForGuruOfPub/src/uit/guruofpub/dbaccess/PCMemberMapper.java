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
import uit.guruofpub.dto.PCMemberDTO;

/**
 *
 * @author Loc Do
 * @author Nghiep Tran
 */
public class PCMemberMapper extends MapperDB {
    public PCMemberMapper() throws Exception {
        super();
    }

    public PCMemberMapper(Connection con) {
        super(con);
    }
    
    /**
     * insert a new PCMember object into the DB
     * @param PCMemberDTO
     * @return the last inserted PCMember id
     * @throws Exception
     */
    public int insertObj(PCMemberDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO guruofpub.PCMember(pcMemberName, "
                    + " image, emailAddress, website, organization)");                                          
            sql.append(" VALUES(?,?,?,?,?,?)");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getPcMemberName());            
            stmt.setString(2, objDTO.getImage());
            stmt.setString(3, objDTO.getEmailAddress());
            stmt.setString(4, objDTO.getWebsite());
            stmt.setString(5, objDTO.getOrganization());
            
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
     * check if the specified PCMember exist in the DB
     * @param PCMemberDTO
     * @return -1 if not found
     * @return  n if found existed
     * @throws Exception
     */
    public int isExisted(PCMemberDTO objDTO) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.PCMember");
            sql.append(" WHERE PCMemberName=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, objDTO.getPcMemberName());                
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = rs.getInt("idPCMember");                    
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
     * @param PCMemberDTO
     * @return 1 if update successfully
     * @return 0 if update unsuccessfully
     * @throws Exception
     */
    public int updateObj(PCMemberDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE guruofpub.PCMember");                                          
            sql.append(" SET PCMemberName=?, image=?,"
                    +  " emailAddress=?, website=?, organization=?,");                     
            sql.append(" WHERE idPCMember=?");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getPcMemberName());            
            stmt.setString(2, objDTO.getImage());
            stmt.setString(3, objDTO.getEmailAddress());
            stmt.setString(4, objDTO.getWebsite());
            stmt.setString(5, objDTO.getOrganization());            
            stmt.setInt(6,objDTO.getIdPCMember());
            
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
     * getPCMemberDTO
     * @param idPCMember
     * @return
     * @throws Exception 
     */
    public PCMemberDTO getPCMemberDTO(int idPCMember) throws Exception{
        PCMemberDTO pcMemberDTO = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.PCMember p");
            sql.append(" WHERE p.idPCMember = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idPCMember);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                pcMemberDTO = new PCMemberDTO();
                pcMemberDTO.setIdPCMember(idPCMember);
                pcMemberDTO.setPcMemberName(rs.getString("pcMemberName"));
                pcMemberDTO.setEmailAddress(rs.getString("emailAddress"));
                pcMemberDTO.setImage(rs.getString("image"));
                pcMemberDTO.setOrganization(rs.getString("organization"));
                pcMemberDTO.setWebsite(rs.getString("website"));
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

        return pcMemberDTO;        
    }
    
    /**
     * getPCMemberDTOList
     * @param offset
     * @param limit
     * @return
     * @throws Exception 
     */
    public ArrayList getPCMemberDTOList(int offset, int limit) throws Exception{
        PCMemberDTO pcMemberDTO = null;
        ArrayList pcMemberDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.PCMember p");
            sql.append(" ORDER BY p.idPCMember ASC LIMIT ?, ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                pcMemberDTO = new PCMemberDTO();
                pcMemberDTO.setIdPCMember(rs.getInt("idPCMember"));
                pcMemberDTO.setPcMemberName(rs.getString("pcMemberName"));
                pcMemberDTO.setEmailAddress(rs.getString("emailAddress"));
                pcMemberDTO.setImage(rs.getString("image"));
                pcMemberDTO.setOrganization(rs.getString("organization"));
                pcMemberDTO.setWebsite(rs.getString("website"));
                pcMemberDTOList.add(pcMemberDTO);
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

        return pcMemberDTOList;        
    }    
}