/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.pubguru.dbaccess;

import uit.pubguru.utility.PubGuruLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import uit.pubguru.dto.DomainDTO;
import uit.pubguru.dto.database.PubGuruDB;
/**
 *
 * @author Loc Do
 * @author Nghiep Tran
 * @author Huong Tran
 */
public class DomainMapper extends MapperDB {
    public DomainMapper() throws Exception {
        super();
    }

    public DomainMapper(Connection con) {
        super(con);
    }
    
    /**
     * insert a new domain object into the DB    
     * @param domainDTO
     * @return the last inserted domain id
     * @throws Exception
     */
    public int insertObj(DomainDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO " + PubGuruDB.DBNAME + "." + "domain(domainName)");                                          
            sql.append(" VALUES(?)");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getDomainName());
                        
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
     * check if the specified conference exist in the DB
     * @param paperDTO
     * @return -1 if not found
     * @return  n if found existed
     * @throws Exception
     */
    public int isExisted(DomainDTO objDTO) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "domain d");
            sql.append(" WHERE d.domainName=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, objDTO.getDomainName());                
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = rs.getInt("idDomain");                    
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
     * @param domainDTO
     * @return 1 if update successfully
     * @return 0 if update unsuccessfully
     * @throws Exception
     */
    public int updateObj(DomainDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE " + PubGuruDB.DBNAME + "." + "domain d");                                          
            sql.append(" SET d.domainName=?");
            sql.append(" WHERE d.idDomain=?");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getDomainName());
            stmt.setInt(2, objDTO.getIdDomain());
            
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
    
    /**
     * Get list objects in database
     * @return Hash
     * @throw Exception
     */
    public HashMap getListObj() throws Exception {
        HashMap map = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM cspublication.domain");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            
            ResultSet rs = stmt.executeQuery();
            map = new HashMap();
             while (rs != null && rs.next()) {
                int idDomain = rs.getInt("idDomain");
                String domainName = rs.getString("domainName");
                map.put(idDomain, domainName);
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
        return map;
    }

    /**
     * getDomainDTO
     * @param idDomain
     * @return
     * @throws Exception 
     */
    public DomainDTO getDomainDTO(int idDomain) throws Exception{
        DomainDTO domainDTO = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "domain d");
            sql.append(" WHERE d.idDomain = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idDomain);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                domainDTO = new DomainDTO();
                domainDTO.setIdDomain(idDomain);
                domainDTO.setDomainName(rs.getString("domainName"));
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

        return domainDTO;        
    }
    
    /**
     * getDomainDTOList
     * @param offset
     * @param limit
     * @return
     * @throws Exception 
     */
    public ArrayList getDomainDTOList(int offset, int limit) throws Exception{
        DomainDTO domainDTO = null;
        ArrayList domainDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "domain d");
            sql.append(" ORDER BY d.idDomain ASC LIMIT ?, ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                domainDTO = new DomainDTO();
                domainDTO.setIdDomain(rs.getInt("idDomain"));
                domainDTO.setDomainName(rs.getString("domainName"));
                domainDTOList.add(domainDTO);
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

        return domainDTOList;        
    }    
}
