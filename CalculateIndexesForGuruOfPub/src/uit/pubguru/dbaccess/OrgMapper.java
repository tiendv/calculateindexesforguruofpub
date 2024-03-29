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
import uit.pubguru.dto.AuthorDTO;
import uit.pubguru.dto.OrgDTO;
import uit.pubguru.dto.PaperDTO;
import uit.pubguru.dto.database.PubGuruDB;

/**
 *
 * @author Loc Do
 * @author Nghiep Tran
 */
public class OrgMapper extends MapperDB {
    public OrgMapper() throws Exception {
        super();
    }

    public OrgMapper(Connection con) {
        super(con);
    }
    
    /**
     * insert a new Org object into the DB
     * @param orgDTO
     * @return the last inserted Org id
     * @throws Exception
     */
    public int insertObj(OrgDTO objDTO) throws Exception {
        int insertedID = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO " + PubGuruDB.DBNAME + "." + "Org(orgName, website,"
                    + " continent, idOrgParent, h_index, url)");                                          
            sql.append(" VALUES(?,?,?,?,?,?)");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getOrgName());
            stmt.setString(2, objDTO.getWebsite());
            stmt.setString(3, objDTO.getContinent());
            if (objDTO.getIdOrgParent() == -1)
                stmt.setNull(4, java.sql.Types.INTEGER);
            else 
                stmt.setInt(4, objDTO.getIdOrgParent());
            if (objDTO.getH_index() == -1)
                stmt.setNull(5, java.sql.Types.INTEGER);
            else
                stmt.setInt(5, objDTO.getH_index());
            stmt.setString(6, objDTO.getUrl());
            
            stmt.execute();

            // get the last inserted ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
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
     * check if the specified org exist in the DB
     * @param orgDTO
     * @return -1 if not found
     * @return  n if found existed
     * @throws Exception
     */
    public int isExisted(OrgDTO objDTO) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "Org");
            sql.append(" WHERE url=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, objDTO.getUrl());                
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = rs.getInt("idOrg");
                    return idObj;
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
    
    public int isExisted(String url) throws Exception {        
        int idObj = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "Org");
            sql.append(" WHERE url=?");
            Connection con = this.getConnection();
            if (con != null) {
                PreparedStatement stmt = con.prepareStatement(sql.toString());
                stmt.setString(1, url);                
                ResultSet rs = stmt.executeQuery();
                if ((rs != null) && (rs.next())) {
                    idObj = rs.getInt("idOrg");
                    return idObj;
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
     * @param OrgDTO
     * @return 1 if update successfully
     * @return 0 if update unsuccessfully
     * @throws Exception
     */
    public int updateObj(OrgDTO objDTO) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE " + PubGuruDB.DBNAME + "." + "Org");                                          
            sql.append(" SET orgName=?, website=?, continent=?,"
                     + " idOrgParent=?, h_index=?, url=?");
            sql.append(" WHERE idOrg=?");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, objDTO.getOrgName());
            stmt.setString(2, objDTO.getWebsite());
            stmt.setString(3, objDTO.getContinent());
            if (objDTO.getIdOrgParent() == -1)
                stmt.setNull(4, java.sql.Types.INTEGER);
            else 
                stmt.setInt(4, objDTO.getIdOrgParent());
            if (objDTO.getH_index() == -1)
                stmt.setNull(5, java.sql.Types.INTEGER);
            else
                stmt.setInt(5, objDTO.getH_index());
            stmt.setString(6, objDTO.getUrl());
            stmt.setInt(7,objDTO.getIdOrg());
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
     * getOrgDTO
     * @param idOrg
     * @return
     * @throws Exception 
     */
    public OrgDTO getOrgDTO(int idOrg) throws Exception{
        OrgDTO orgDTO = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "Org o");
            sql.append(" WHERE o.idOrg = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idOrg);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                orgDTO = new OrgDTO();
                orgDTO.setIdOrg(idOrg);
                orgDTO.setOrgName(rs.getString("orgName"));
                orgDTO.setContinent(rs.getString("continent"));
                orgDTO.setH_index(rs.getInt("h_index"));
                orgDTO.setIdOrgParent(rs.getInt("idOrgParent"));
                orgDTO.setUrl(rs.getString("url"));
                orgDTO.setWebsite(rs.getString("website"));
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

        return orgDTO;        
    }
    
    /**
     * getOrgDTOList
     * @param offset
     * @param limit
     * @return
     * @throws Exception 
     */
    public ArrayList getOrgDTOList(int offset, int limit) throws Exception{
        OrgDTO orgDTO = null;
        ArrayList orgDTOList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "Org o");
            sql.append(" ORDER BY o.idOrg ASC LIMIT ?, ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                orgDTO = new OrgDTO();
                orgDTO.setIdOrg(rs.getInt("idOrg"));
                orgDTO.setOrgName(rs.getString("orgName"));
                orgDTO.setContinent(rs.getString("continent"));
                orgDTO.setH_index(rs.getInt("h_index"));
                orgDTO.setIdOrgParent(rs.getInt("idOrgParent"));
                orgDTO.setUrl(rs.getString("url"));
                orgDTO.setWebsite(rs.getString("website"));
                orgDTOList.add(orgDTO);
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

        return orgDTOList;        
    }

    public ArrayList getOrgIdList() throws Exception {
        ArrayList orgIdList = new ArrayList();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT org.idOrg FROM " + PubGuruDB.DBNAME + "." + "Org org order by org.idOrg");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && (rs.next())) {
                orgIdList.add(rs.getInt("idOrg"));
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

        return orgIdList;
    }

    public ArrayList getAuthorDTOListByOrgId(int idOrg) throws Exception {
        ArrayList authorDTOList = new ArrayList();
        AuthorDTO dtoAuthor = new AuthorDTO();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT distinct author.* FROM " + PubGuruDB.DBNAME + "." + "Author author");
            sql.append(" where author.idOrg = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idOrg);
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

    public int getH_index(int idOrg) throws Exception {
        int h_index = 0;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT r.h_index FROM " + PubGuruDB.DBNAME + "." + "org org");
            sql.append(" join " + PubGuruDB.DBNAME + "." + "_rank_org r on org.idOrg = r.idOrg");
            sql.append(" where org.idOrg = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idOrg);
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
    
    public int getG_index(int idOrg) throws Exception {
        int g_index = 0;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT r.g_index FROM " + PubGuruDB.DBNAME + "." + "org org");
            sql.append(" join " + PubGuruDB.DBNAME + "." + "_rank_org r on org.idOrg = r.idOrg");
            sql.append(" where org.idOrg = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idOrg);
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

    public ArrayList getAuthorDTOListByOrgIdSubdomainId(int idOrg, int idSubdomain) throws Exception {
        ArrayList authorDTOList = new ArrayList();
        AuthorDTO dtoAuthor = new AuthorDTO();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT distinct a.* FROM " + PubGuruDB.DBNAME + "." + "Author a");
            sql.append(" JOIN " + PubGuruDB.DBNAME + "." + "author_paper ap ON a.idAuthor = ap.idAuthor");
            sql.append(" JOIN " + PubGuruDB.DBNAME + "." + "paper p ON ap.idPaper = p.idPaper");
            sql.append(" JOIN " + PubGuruDB.DBNAME + "." + "subdomain_paper sp ON p.idPaper = sp.idPaper");
            sql.append(" where a.idOrg = ?");
            sql.append(" and sp.idSubdomain = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idOrg);
            stmt.setInt(2, idSubdomain);
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

    public ArrayList getAuthorDTOListByOrgIdKeywordId(int idOrg, int idKeyword) throws Exception {
        ArrayList authorDTOList = new ArrayList();
        AuthorDTO dtoAuthor = new AuthorDTO();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT distinct a.* FROM " + PubGuruDB.DBNAME + "." + "Author a");
            sql.append(" JOIN " + PubGuruDB.DBNAME + "." + "author_paper ap ON a.idAuthor = ap.idAuthor");
            sql.append(" JOIN " + PubGuruDB.DBNAME + "." + "paper p ON ap.idPaper = p.idPaper");
            sql.append(" JOIN " + PubGuruDB.DBNAME + "." + "paper_keyword pk ON p.idPaper = pk.idPaper");
            sql.append(" where a.idOrg = ?");
            sql.append(" and pk.idKeyword = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idOrg);
            stmt.setInt(2, idKeyword);
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
}
