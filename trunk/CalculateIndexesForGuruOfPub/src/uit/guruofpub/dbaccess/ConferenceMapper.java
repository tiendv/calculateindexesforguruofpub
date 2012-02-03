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
import uit.guruofpub.dto.ConferenceDTO;

/**
 *
 * @author Huynh Ngoc Tin
 * @author Loc Do
 * @author Nghiep Tran
 * @author Huong Tran
 */
public class ConferenceMapper extends MapperDB {
    
    public ConferenceMapper() throws Exception {
        super();
    }

    public ConferenceMapper(Connection con) {
        super(con);
    }

    /**
     * check if the specified conference exist in the DB
     * @param paperDTO
     * @return -1 if not exists
     *          n if existed
     * @throws Exception
     */
    public int isExisted(ConferenceDTO confDTO) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.conference conf");
            sql.append(" WHERE conf.url = ?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, confDTO.getUrl());                                
                ResultSet rs = stmt.executeQuery();
                
                if ((rs != null) && (rs.next())) {                    
                    idObj = rs.getInt("idConference");                                        
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
            sql.append(" SELECT * FROM guruofpub.conference conf");
            sql.append(" WHERE conf.url = ?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, url);                                
                ResultSet rs = stmt.executeQuery();
                
                if ((rs != null) && (rs.next())) {                    
                    idObj = rs.getInt("idConference");                                        
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
     * @comment fix the 'organizedLocation' error in database
     * @param authorDTO
     * @throws Exception
     */
    public int insertObj(ConferenceDTO confDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO guruofpub.conference("
                     + "conferenceName, website, organization, organizedLocation, "
                     + "duration, yearStart, yearEnd, url)");
            sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, confDTO.getConferenceName());
            stmt.setString(2, confDTO.getWebsite());
            stmt.setString(3, confDTO.getOrganization());
            stmt.setString(4, confDTO.getOrganizedLocation());
            stmt.setString(5, confDTO.getDuration());
            if (confDTO.getYearStart() == -1)
                stmt.setNull(6, java.sql.Types.INTEGER);
            else
                stmt.setInt(6, confDTO.getYearStart());
            if (confDTO.getYearEnd() == -1)
                stmt.setNull(7, java.sql.Types.INTEGER);
            else
                stmt.setInt(7, confDTO.getYearEnd());
            stmt.setString(8 , confDTO.getUrl());
            stmt.execute();

            // get the last inserted ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            int insertConfID = -1;
            if (rs != null && rs.next())
                insertConfID = rs.getInt(1);

            stmt.close();
            return insertConfID;
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
     * update existing Conference record     
     * @param confDTO
     * @return number of affected row
     * @throw
     */
    public int updateObj(ConferenceDTO confDTO) throws Exception {
    	 try {
    	     StringBuffer sql = new StringBuffer();
             sql.append("UPDATE guruofpub.conference");
             sql.append(" SET conferenceName=?, website=?, organization=?,"
                      + " organizedLocation=?, duration=?,"
                      + " yearStart=?, yearEnd=?, url=?");    		
             sql.append(" WHERE idConference=?");
    		 
             PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
             stmt.setString(1, confDTO.getConferenceName());
             stmt.setString(2, confDTO.getWebsite());
             stmt.setString(3, confDTO.getOrganization());
             stmt.setString(4, confDTO.getOrganizedLocation());
             stmt.setString(5, confDTO.getDuration());
            if (confDTO.getYearStart() == -1)
                stmt.setNull(6, java.sql.Types.INTEGER);
            else
                stmt.setInt(6, confDTO.getYearStart());
            if (confDTO.getYearEnd() == -1)
                stmt.setNull(7, java.sql.Types.INTEGER);
            else
                stmt.setInt(7, confDTO.getYearEnd());
             stmt.setString(8, confDTO.getUrl());
             stmt.setInt(9, confDTO.getIdConference());
             
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
     * getConferenceNameListForAuthorList     
     * @param authorIDList
     * @return confNameList
     * @throws Exception
     */
    public ArrayList getConferenceNameListForAuthorList(ArrayList authorIDList) throws Exception {
        ArrayList confNameList = new ArrayList();
        StringBuffer sqlStr1 = new StringBuffer();
        try {
            sqlStr1.append(" SELECT c.idConference, c.conferenceName");
            sqlStr1.append(" FROM guruofpub.conferencec, guruofpub.paper p");
            sqlStr1.append(" WHERE p.idConference = c.idConference AND p.idPaper IN ");
            sqlStr1.append(" (SELECT ap.idPaper FROM guruofpub.author_paper ap WHERE ap.idAuthor IN (");
            for (int i=0; i<authorIDList.size(); i++) {
                String curAuthorID = (String) authorIDList.get(i);
                if (i == authorIDList.size()-1)
                    sqlStr1.append(curAuthorID);
                else
                    sqlStr1.append(curAuthorID + ",");
            }
            sqlStr1.append(" ))");
            sqlStr1.append(" GROUP BY c.conferenceName");
            Connection conn = getConnection();
            PreparedStatement stmt1 = conn.prepareStatement(sqlStr1.toString());
            ResultSet rs1 = stmt1.executeQuery();

            while (rs1 != null && rs1.next()) {
                String confName = rs1.getString("conferenceName");
                confNameList.add(confName);
            }

            rs1.close();
            stmt1.close();
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
            return confNameList;
    }
    
    /**
     * getConferenceDTO
     * @param idConference
     * @return
     * @throws Exception 
     */
    public ConferenceDTO getConferenceDTO(int idConference) throws Exception{
        ConferenceDTO conferenceDTO = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.conference c");
            sql.append(" WHERE c.idConference = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idConference);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                conferenceDTO = new ConferenceDTO();
                conferenceDTO.setIdConference(idConference);
                conferenceDTO.setConferenceName(rs.getString("conferenceName"));
                conferenceDTO.setDuration(rs.getString("duration"));
                conferenceDTO.setOrganization(rs.getString("organization"));
                conferenceDTO.setOrganizedLocation(rs.getString("organizedLocation"));
                conferenceDTO.setUrl(rs.getString("url"));
                conferenceDTO.setWebsite(rs.getString("website"));
                conferenceDTO.setYearStart(rs.getInt("yearStart"));
                conferenceDTO.setYearEnd(rs.getInt("yearEnd"));
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

        return conferenceDTO;        
    }
    
    /**
     * getConferenceDTOList
     * @param offset
     * @param limit
     * @return
     * @throws Exception 
     */
    public ArrayList getConferenceDTOList(int offset, int limit) throws Exception{
        ConferenceDTO conferenceDTO = null;
        ArrayList conferenceDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.conference c");
            sql.append(" ORDER BY c.idConference ASC LIMIT ?, ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                conferenceDTO = new ConferenceDTO();
                conferenceDTO.setIdConference(rs.getInt("idConference"));
                conferenceDTO.setConferenceName(rs.getString("conferenceName"));
                conferenceDTO.setDuration(rs.getString("duration"));
                conferenceDTO.setOrganization(rs.getString("organization"));
                conferenceDTO.setOrganizedLocation(rs.getString("organizedLocation"));
                conferenceDTO.setUrl(rs.getString("url"));
                conferenceDTO.setWebsite(rs.getString("website"));
                conferenceDTO.setYearStart(rs.getInt("yearStart"));
                conferenceDTO.setYearEnd(rs.getInt("yearEnd"));
                conferenceDTOList.add(conferenceDTO);
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

        return conferenceDTOList;        
    }

    /**
     * getConferenceName
     * @param idConference
     * @return
     * @throws Exception 
     */
    public String getConferenceName(int idConference) throws Exception {
        String conferenceName = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM guruofpub.conference c");
            sql.append(" where c.idConference = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idConference);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                conferenceName = rs.getString("conferenceName");
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

        return conferenceName;        
    }

    public ArrayList getConferenceIdList() throws Exception{
        ArrayList conferenceIdList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT c.idConference FROM guruofpub.conference c ORDER BY c.idConference");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                conferenceIdList.add(rs.getInt("idConference"));
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

        return conferenceIdList;
    }

    public int saveIndexes(int idConference, int g_index) throws Exception {
        int result = -1;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" insert into guruofpub._rank_conference(idConference, g_index)");
            sql.append(" value (?, ?)");
            sql.append(" on duplicate key update");
            sql.append(" g_index = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idConference);
            stmt.setInt(2, g_index);
            stmt.setInt(3, g_index);
            result = stmt.executeUpdate();
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
        return result;
    }

    public int saveIndexesConferenceSubdomain(int idConference, int idSubdomain, int g_index) throws Exception {
        int result = -1;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" insert into guruofpub._rank_conference_subdomain(idConference, idSubdomain, g_index)");
            sql.append(" value (?, ?, ?)");
            sql.append(" on duplicate key update");
            sql.append(" g_index = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idConference);
            stmt.setInt(2, idSubdomain);
            stmt.setInt(3, g_index);
            stmt.setInt(4, g_index);
            result = stmt.executeUpdate();
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
        return result;
    }

    public int saveIndexesConferenceKeyword(int idConference, int idKeyword, int g_index) throws Exception {
        int result = -1;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" insert into guruofpub._rank_conference_keyword(idConference, idKeyword, g_index)");
            sql.append(" value (?, ?, ?)");
            sql.append(" on duplicate key update");
            sql.append(" g_index = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idConference);
            stmt.setInt(2, idKeyword);
            stmt.setInt(3, g_index);
            stmt.setInt(4, g_index);
            result = stmt.executeUpdate();
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
        return result;
    }
}