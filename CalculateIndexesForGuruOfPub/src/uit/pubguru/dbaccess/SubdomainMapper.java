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
import java.util.HashMap;
import uit.pubguru.dto.SubdomainDTO;
import uit.pubguru.dto.database.PubGuruDB;

/**
 *
 * @author Loc Do
 * @author Nghiep Tran
 */

public class SubdomainMapper extends MapperDB {
    public SubdomainMapper() throws Exception {
        super();
    }

    public SubdomainMapper(Connection con) {
        super(con);
    }
    
    /**
     * get idSubdomain by subdomain name
     * @param objDTO
     * @return
     * @throws Exception 
     */
    public int isExisted(String subdomain) throws Exception {        
        int idObj = -1;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "Subdomain sd");
            sql.append(" WHERE sd.subdomainName=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, subdomain);                
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = rs.getInt("idSubdomain");                    
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
     * insert a new Subdomain object into the DB    
     * @param SubdomainDTO
     * @return the last inserted Subdomain id
     * @throws Exception
     */
    public int insertObj(SubdomainDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO " + PubGuruDB.DBNAME + "." + "Subdomain(subdomainName, idDomain)");                                          
            sql.append(" VALUES(?, ?)");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getSubdomainName());
            stmt.setInt(2, objDTO.getIdDomain());
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
    public int isExisted(SubdomainDTO objDTO) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "Subdomain sd");
            sql.append(" WHERE sd.subdomainName=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, objDTO.getSubdomainName());                
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = rs.getInt("idSubdomain");                    
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
     * @param SubdomainDTO
     * @return 1 if update successfully
     * @return 0 if update unsuccessfully
     * @throws Exception
     */
    public int updateObj(SubdomainDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE " + PubGuruDB.DBNAME + "." + "Subdomain sd");                                          
            sql.append(" SET sd.subdomainName=?, sd.idDomain=?");
            sql.append(" WHERE sd.idSubdomain=?");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getSubdomainName());
            stmt.setInt(2, objDTO.getIdDomain());
            stmt.setInt(3, objDTO.getIdSubdomain());
            
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
     * getSubdomainDTO
     * @param idSubdomain
     * @return
     * @throws Exception 
     */
    public SubdomainDTO getSubdomainDTO(int idSubdomain) throws Exception{
        SubdomainDTO subdomainDTO = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "Subdomain s");
            sql.append(" WHERE s.idSubdomain = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idSubdomain);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                subdomainDTO = new SubdomainDTO();
                subdomainDTO.setIdSubdomain(idSubdomain);
                subdomainDTO.setSubdomainName(rs.getString("subdomainName"));
                subdomainDTO.setIdDomain(rs.getInt("idDomain"));
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

        return subdomainDTO;        
    }
    
    /**
     * getSubdomainDTOList
     * @param offset
     * @param limit
     * @return
     * @throws Exception 
     */
    public ArrayList getSubdomainDTOList(int offset, int limit) throws Exception{
        SubdomainDTO subdomainDTO = null;
        ArrayList subdomainDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "Subdomain s");
            sql.append(" ORDER BY s.idSubdomain ASC LIMIT ?, ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                subdomainDTO = new SubdomainDTO();
                subdomainDTO.setIdSubdomain(rs.getInt("idSubdomain"));
                subdomainDTO.setSubdomainName(rs.getString("subdomainName"));
                subdomainDTO.setIdDomain(rs.getInt("idDomain"));
                subdomainDTOList.add(subdomainDTO);
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

        return subdomainDTOList;        
    }

    public ArrayList getSubdomainIdList() throws Exception{
        ArrayList subdomainIdList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT s.idSubdomain FROM " + PubGuruDB.DBNAME + "." + "Subdomain s ORDER BY s.idSubdomain");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                subdomainIdList.add(rs.getInt("idSubdomain"));
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

        return subdomainIdList;
    }
}
