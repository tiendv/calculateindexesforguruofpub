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
import uit.pubguru.dto.AuthorDTO;
import uit.pubguru.dto.database.PubGuruDB;

/**
 *
 * @author Huynh Ngoc Tin
 * @author Loc Do
 * @author Nghiep Tran
 * @author Huong Tran
 */
public class AuthorMapper extends MapperDB {

    public AuthorMapper() throws Exception {
            super();
    }

    public AuthorMapper(Connection con) {
            super(con);
            // TODO Auto-generated constructor stub
    }

    /**
     * check If the author exist in DB
     * @param authorDTO
     * @return
     * @throws Exception
     */
    public int isExisted(AuthorDTO authorDTO) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "author author");
            sql.append(" WHERE author.url = ? ");            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, authorDTO.getUrl());
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                idObj = rs.getInt("idAuthor");
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
        return idObj;
    }
    
    /**
     * Check if the author exist in DB
     * @param url
     * @return
     * @throws Exception 
     */
    public int isExisted(String url) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "author author");
            sql.append(" WHERE author.url = ? ");            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, url);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                idObj = rs.getInt("idAuthor");
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
        return idObj;
    }

    /**
     *
     * @param authorName
     * @return: authorID or -1 if not exist
     * @throws Exception
     */
    public int getIDObj(String authorName) throws Exception {
        int idAuthor = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT author.idAuthor FROM " + PubGuruDB.DBNAME + "." + "author author");
            sql.append(" WHERE author.authorName = ? ");            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, authorName);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                idAuthor = rs.getInt("idAuthor");
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

        return idAuthor;
    }

    /**
     * 
     * @param authorDTO
     * @return
     * @throws Exception 
     */
    public boolean updateObj(AuthorDTO authorDTO) throws Exception {
        boolean isUpdated = false;
        try {
          int idAuthor = getIDObj(authorDTO.getAuthorName());
          if (idAuthor == -1) {
              insertObj(authorDTO);
              return true;
          }
          StringBuffer sql = new StringBuffer();
          sql.append("UPDATE " + PubGuruDB.DBNAME + "." + "author");
          sql.append(" SET");
          sql.append(" authorName=?,");          
          sql.append(" image=?,");
          sql.append(" emailAddress=?,");
          sql.append(" website=?,");
          sql.append(" idOrg=?,");
          sql.append(" h_index=?,");
          sql.append(" g_index=?,");
          sql.append(" url=?");
          sql.append(" WHERE idAuthor=?");
           
          PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
          stmt.setString(1, authorDTO.getAuthorName());          
          stmt.setString(2, authorDTO.getImage());
          stmt.setString(3, authorDTO.getEmailAddress());
          stmt.setString(4, authorDTO.getWebsite());
            if (authorDTO.getIdOrg() == -1)
                stmt.setNull(5, java.sql.Types.INTEGER);
            else 
              stmt.setInt(5, authorDTO.getIdOrg());
            if (authorDTO.getH_index() == -1)
                stmt.setNull(6, java.sql.Types.INTEGER);
            else
                stmt.setInt(6, authorDTO.getH_index());
            if (authorDTO.getG_index() == -1)
                stmt.setNull(7, java.sql.Types.INTEGER);
            else
                stmt.setInt(7, authorDTO.getG_index());
          stmt.setString(8, authorDTO.getUrl());
          stmt.setInt(9, authorDTO.getIdAuthor());
            
          int rowCount = stmt.executeUpdate();
          if (rowCount > 0) {
              isUpdated = true;
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
        return isUpdated;
    }
     /**
     *
     * @param authorName
     * @return: authorID or -1 if not exist
     * @throws Exception
     */
    public String getObjName(int idAuthor) throws Exception {
        String authorName = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT author.authorName FROM " + PubGuruDB.DBNAME + "." + "author author");
            sql.append(" WHERE author.idAuthor = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                authorName = rs.getString("authorName");
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

        return authorName;
    }

    /**
     * insert a new author into the DB
     * @param authorDTO
     * @throws Exception
     */
    public int insertObj(AuthorDTO authorDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO " + PubGuruDB.DBNAME + "." + "author(authorName, image, emailAddress, website, idOrg, h_index, g_index, url) ");
            sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, authorDTO.getAuthorName());            
            stmt.setString(2, authorDTO.getImage());
            stmt.setString(3, authorDTO.getEmailAddress());
            stmt.setString(4, authorDTO.getWebsite());
            if (authorDTO.getIdOrg() == -1)
                stmt.setNull(5, java.sql.Types.INTEGER);
            else 
                stmt.setInt(5, authorDTO.getIdOrg());
            if (authorDTO.getH_index() == -1)
                stmt.setNull(6, java.sql.Types.INTEGER);
            else
                stmt.setInt(6, authorDTO.getH_index());
            if (authorDTO.getG_index() == -1)
                stmt.setNull(7, java.sql.Types.INTEGER);
            else
                stmt.setInt(7, authorDTO.getG_index());
            stmt.setString(8, authorDTO.getUrl());
            
            stmt.execute();

            // get the last inserted ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            int insertAuthorID = -1;
            if (rs != null && rs.next())
                insertAuthorID = rs.getInt(1);
            stmt.close();
            return insertAuthorID;
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
     * getAuthorIDListFromnameList
     * @param authorNameList
     * @return
     * @throws Exception
     */
    public ArrayList getObjIDListFromNameList(ArrayList authorNameList) throws Exception {
        ArrayList authorIDList = new ArrayList();
        int authorID = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT author.idAuthor FROM " + PubGuruDB.DBNAME + "." + "author author");
            sql.append(" WHERE author.authorName IN (");
            for (int i=0; i<authorNameList.size(); i++) {
                if (i != (authorNameList.size()-1))
                    sql.append("?,");
                else
                    sql.append("?");

            }
            sql.append(")");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            for (int i=0; i<authorNameList.size(); i++) {
                stmt.setString(i+1, (String) authorNameList.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                authorID = rs.getInt("idAuthor");
                authorIDList.add(new Integer(authorID));
            }

            rs.close();
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
        
        return authorIDList;
    }
    
    /**
     * getAuthorDTO
     * @param idAuthor
     * @return dtoAuthor
     * @throws Exception 
     */
    public AuthorDTO getAuthorDTO(int idAuthor) throws Exception{
        AuthorDTO dtoAuthor = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "author author");
            sql.append(" WHERE author.idAuthor = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                dtoAuthor = new AuthorDTO();
                dtoAuthor.setIdAuthor(idAuthor);
                dtoAuthor.setAuthorName(rs.getString("authorName"));
                dtoAuthor.setEmailAddress(rs.getString("emailAddress"));
                dtoAuthor.setG_index(rs.getInt("g_index"));
                dtoAuthor.setH_index(rs.getInt("h_index"));
                dtoAuthor.setIdOrg(rs.getInt("idOrg"));
                dtoAuthor.setImage(rs.getString("image"));
                dtoAuthor.setUrl(rs.getString("url"));
                dtoAuthor.setWebsite(rs.getString("website"));
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

        return dtoAuthor;        
    }
    
    /**
     * 
     * @param offset
     * @param limit
     * @return
     * @throws Exception 
     */
    public ArrayList getAuthorDTOList(int offset, int limit) throws Exception{
        AuthorDTO dtoAuthor = null;
        ArrayList authorDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "author author");
            sql.append(" ORDER BY author.idAuthor ASC LIMIT ?, ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                dtoAuthor = new AuthorDTO();
                dtoAuthor.setIdAuthor(rs.getInt("idAuthor"));
                dtoAuthor.setAuthorName(rs.getString("authorName"));
                dtoAuthor.setEmailAddress(rs.getString("emailAddress"));
                dtoAuthor.setG_index(rs.getInt("g_index"));
                dtoAuthor.setH_index(rs.getInt("h_index"));
                dtoAuthor.setIdOrg(rs.getInt("idOrg"));
                dtoAuthor.setImage(rs.getString("image"));
                dtoAuthor.setUrl(rs.getString("url"));
                dtoAuthor.setWebsite(rs.getString("website"));
                authorDTOList.add(dtoAuthor);
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

    public ArrayList getAuthorIdList() throws Exception {
        ArrayList authorIdList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT author.idAuthor FROM " + PubGuruDB.DBNAME + "." + "author author ORDER BY author.idAuthor");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                authorIdList.add(rs.getInt("idAuthor"));
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

        return authorIdList;
    }

    public int getH_index(int idAuthor) throws Exception {
        int h_index = 0;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT r.h_index FROM " + PubGuruDB.DBNAME + "." + "author author");
            sql.append(" join " + PubGuruDB.DBNAME + "." + "_rank_author r on author.idAuthor = r.idAuthor");
            sql.append(" where author.idAuthor = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                h_index = rs.getInt("h_index");
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

        return h_index;
    }
    
    public int getG_index(int idAuthor) throws Exception {
        int g_index = 0;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT r.g_index FROM " + PubGuruDB.DBNAME + "." + "author author");
            sql.append(" join " + PubGuruDB.DBNAME + "." + "_rank_author r on author.idAuthor = r.idAuthor");
            sql.append(" where author.idAuthor = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                g_index = rs.getInt("g_index");
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

        return g_index;
    }
}
