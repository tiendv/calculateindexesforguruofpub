/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uit.guruofpub.dbaccess;

import uit.guruofpub.utility.GuruOfPubLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import uit.guruofpub.dto.JournalDTO;

/**
 *
 * @author Huynh Ngoc Tin
 * @author Loc Do
 * @author Nghiep Tran
 * @author Huong Tran
 */
public class JournalMapper extends MapperDB {
    public JournalMapper() throws Exception {
        super();
    }

    public JournalMapper(Connection con) {
        super(con);
    }

    /**
     * check if the specified conference exist in the DB
     * @param paperDTO
     * @return
     * @throws Exception
     */
    public int isExisted(JournalDTO journalDTO) throws Exception {
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.journal journal");
            sql.append(" WHERE journal.url = ?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, journalDTO.getUrl());                
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = rs.getInt("idJournal");
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
            sql.append(" SELECT * FROM guruofpub.journal journal");
            sql.append(" WHERE journal.url = ?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, url);
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = rs.getInt("idJournal");
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
     * insert a new conference into the DB
     * @param authorDTO
     * @throws Exception
     */
    public int insertObj(JournalDTO journalDTO) throws Exception {                
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO guruofpub.journal(journalName, website, yearStart, yearEnd, organization, url) ");
            sql.append(" VALUES(?, ?, ?, ?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, journalDTO.getJournalName());
            stmt.setString(2, journalDTO.getWebsite());
            if (journalDTO.getYearStart() == -1)
                stmt.setNull(3, java.sql.Types.INTEGER);
            else
                stmt.setInt(3, journalDTO.getYearStart());
            if (journalDTO.getYearEnd() == -1)
                stmt.setNull(4, java.sql.Types.INTEGER);
            else
                stmt.setInt(4, journalDTO.getYearEnd());
            stmt.setString(5, journalDTO.getOrganization());
            stmt.setString(6, journalDTO.getUrl());
            stmt.execute();

            // get the last inserted ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            int insertidJournal = -1;
            if (rs != null && rs.next())
                insertidJournal = rs.getInt(1);

            stmt.close();
            return insertidJournal;
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
     * update existing Journal record 
     * @param journalDTO
     * @return number of affected row
     * @throw
     */
    public int updateObj(JournalDTO journalDTO) throws Exception {
    	 try {
    	     StringBuffer sql = new StringBuffer();
             sql.append("UPDATE guruofpub.journal");
             sql.append(" SET journalName=?, website=?, yearStart=?, yearEnd=?, organization=?, url=?");    		
             sql.append(" WHERE idJournal=?");
    		 
             PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
             stmt.setString(1, journalDTO.getJournalName());
             stmt.setString(2, journalDTO.getWebsite());
            if (journalDTO.getYearStart() == -1)
                stmt.setNull(3, java.sql.Types.INTEGER);
            else
                stmt.setInt(3, journalDTO.getYearStart());
            if (journalDTO.getYearEnd() == -1)
                stmt.setNull(4, java.sql.Types.INTEGER);
            else
                stmt.setInt(4, journalDTO.getYearEnd());
             stmt.setString(5, journalDTO.getOrganization());
             stmt.setString(6, journalDTO.getUrl());
             stmt.setInt(7, journalDTO.getIdJournal());
             
             Boolean isOk = stmt.execute();
             stmt.close();
             
             if (isOk) return 1; 
             else return 0;
         } catch (Exception ex) {
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
     * getJournalDTO
     * @param idJournal
     * @return
     * @throws Exception 
     */
    public JournalDTO getJournalDTO(int idJournal) throws Exception{
        JournalDTO journalDTO = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.journal j");
            sql.append(" WHERE j.idJournal = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idJournal);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                journalDTO = new JournalDTO();
                journalDTO.setIdJournal(idJournal);
                journalDTO.setJournalName(rs.getString("journalName"));
                journalDTO.setOrganization(rs.getString("organization"));
                journalDTO.setUrl(rs.getString("url"));
                journalDTO.setWebsite(rs.getString("website"));
                journalDTO.setYearStart(rs.getInt("yearStart"));
                journalDTO.setYearEnd(rs.getInt("yearEnd"));
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

        return journalDTO;        
    }
    
    /**
     * getJournalDTOList
     * @param offset
     * @param limit
     * @return
     * @throws Exception 
     */
    public ArrayList getJournalDTOList(int offset, int limit) throws Exception{
        JournalDTO journalDTO = null;
        ArrayList journalDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.journal j");
            sql.append(" ORDER BY j.idJournal ASC LIMIT ?, ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                journalDTO = new JournalDTO();
                journalDTO.setIdJournal(rs.getInt("idJournal"));
                journalDTO.setJournalName(rs.getString("journalName"));
                journalDTO.setOrganization(rs.getString("organization"));
                journalDTO.setUrl(rs.getString("url"));
                journalDTO.setWebsite(rs.getString("website"));
                journalDTO.setYearStart(rs.getInt("yearStart"));
                journalDTO.setYearEnd(rs.getInt("yearEnd"));
                journalDTOList.add(journalDTO);
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

        return journalDTOList;        
    }

    
    public String getJournalName(int idJournal) throws Exception{
        String journalName = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.journal j");
            sql.append(" where j.idJournal = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idJournal);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                journalName = rs.getString("journalName");
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

        return journalName;
    }
}
