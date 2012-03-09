package uit.pubguru.dbaccess;

/**
 * 
 * @author Huynh Ngoc Tin
 * @author Loc Do
 * @author Huong Tran
 */

import uit.pubguru.utility.PubGuruLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import uit.pubguru.dto.AuthorDTO;
import uit.pubguru.dto.AuthorPaperDTO;
import uit.pubguru.dto.PaperDTO;
import uit.pubguru.dto.database.PubGuruDB;

public class AuthorPaperMapper extends MapperDB {

    public AuthorPaperMapper() throws Exception {
            super();
    }

    public AuthorPaperMapper(Connection con) {
            super(con);
            // TODO Auto-generated constructor stub
    }
    /**
     * check if relation is duplicate in the database.
     * @param idAuthor
     * @param idPaper
     * @return
     * @throws Exception
     */
    public int isExisted(int idAuthor, int idPaper) throws Exception {
        int isExist = -1;
        try {
            StringBuffer sql = new StringBuffer(); 
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "author_paper authorpaper");
            sql.append(" WHERE authorpaper.idAuthor = ? AND authorpaper.idPaper = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            stmt.setInt(2, idPaper);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                isExist = 1;
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

        return isExist;
    }

    public int isExisted(AuthorPaperDTO dtoAuthorPaper) throws Exception {
        int isExist = -1;
        try {
            StringBuffer sql = new StringBuffer(); 
            sql.append(" SELECT * FROM " + PubGuruDB.DBNAME + "." + "author_paper authorpaper");
            sql.append(" WHERE authorpaper.idAuthor = ? AND authorpaper.idPaper = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, dtoAuthorPaper.getIdAuthor());
            stmt.setInt(2, dtoAuthorPaper.getIdPaper());
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                isExist = 1;
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

        return isExist;
    }
        
    /**
     * insert a new relation between the author and the paper
     * @param idAuthor
     * @param idPaper
     * @return
     * @throws Exception
     */
    public int insertObj(int idAuthor, int idPaper) throws Exception {
    	int insertPaperID = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + PubGuruDB.DBNAME + "." + "author_paper(idAuthor, idPaper) ");
            sql.append(" VALUES(?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            stmt.setInt(2, idPaper);            

            stmt.execute();
            
            // get the last inserted ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");            
            if (rs != null && rs.next())
                insertPaperID = rs.getInt(1);
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
        return insertPaperID;
    }

    /**
     * Insert a new author
     * @param dtoAuthorPaper
     * @return
     * @throws Exception 
     */
    public int insertObj(AuthorPaperDTO dtoAuthorPaper) throws Exception {
        int result = -1;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + PubGuruDB.DBNAME + "." + "author_paper(idAuthor, idPaper) ");
            sql.append(" VALUES(?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, dtoAuthorPaper.getIdAuthor());
            stmt.setInt(2, dtoAuthorPaper.getIdPaper());

            stmt.execute();
            
            // get the last inserted ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");            
            if (rs != null && rs.next())
                result = rs.getInt(1);
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
        return result;
    }
        
    /**
     * update a relation between author and paper
     * @param AuthorPaperDTO
     * @return
     * @throws Exception
     */
    public int updateObj(AuthorPaperDTO authorpaper) throws Exception {
    	try {
    		StringBuffer sql = new StringBuffer();
    		sql.append(" UPDATE author_paper");
    		sql.append(" SET idPaper=?");
    		sql.append(" WHERE idAuthor=?");
    		PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, authorpaper.getIdPaper());
            stmt.setInt(2, authorpaper.getIdAuthor());
            
            int rowCount = stmt.executeUpdate();
            stmt.close();
            
            if (rowCount > 0) return 1;
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
     * get all papers of specified author
     * @param idAuthor
     * @return idPaperList
     * @throws Exception
     */
    public ArrayList getIDPaperListOfAnAuthor(int idAuthor) throws Exception {
        ArrayList idPaperList = new ArrayList();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT idPaper FROM " + PubGuruDB.DBNAME + "." + "author_paper a");
            sql.append(" WHERE a.idAuthor = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            ResultSet rs = stmt.executeQuery();
            String idPaper;
            while (rs != null && rs.next()) {
                idPaper = String.valueOf(rs.getInt("idPaper"));
                if ((idPaper != null) && (!idPaper.equals("")))
                    idPaperList.add(idPaper);
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

        return idPaperList;
    }

    /**
     * get CoAuthorList base on Papers
     * @param idPaperList
     * @param idAuthor
     * @return coAuthorList
     */
    public ArrayList getCoAuthorDTOList(ArrayList idPaperList, int idAuthor) throws Exception {
        ArrayList coAuthorList = new ArrayList();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT DISTINCT a.idAuthor, a.authorName ");
            sql.append(" FROM " + PubGuruDB.DBNAME + "." + "author a, " + PubGuruDB.DBNAME + "." + "author_paper ap");
            sql.append(" WHERE a.idAuthor = ap.idAuthor AND ap.idPaper IN (");
            if ((idPaperList != null) && (idPaperList.size() > 0)) {
                for (int i=0; i< idPaperList.size(); i++){
                    if (i == idPaperList.size()-1)
                        sql.append(idPaperList.get(i));
                    else {
                        sql.append(idPaperList.get(i));
                        sql.append(", ");
                    }
                }
            }
            
            sql.append(")");
            sql.append(" AND a.idAuthor != ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            ResultSet rs = stmt.executeQuery();
            AuthorDTO authorDTO;
            while (rs != null && rs.next()) {
                String coAuthorName = rs.getString("authorName");
                if ((coAuthorName != null) && (!coAuthorName.equals(""))) {
                    authorDTO = new AuthorDTO();
                    authorDTO.setIdAuthor(rs.getInt("idAuthor"));
                    authorDTO.setAuthorName(coAuthorName);
                    coAuthorList.add(authorDTO);
                }
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

        return coAuthorList;
    }

    /**
     * getCoAuthorList
     * @param idAuthor
     * @return coidAuthorList
     * @throws Exception
     */
    public ArrayList getCoIDAuthorList(int idAuthor) throws Exception {
        ArrayList coidAuthorList = new ArrayList();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT DISTINCT a.idAuthor, a.authorName");
            sql.append(" FROM " + PubGuruDB.DBNAME + "." + "author_paper ap, " + PubGuruDB.DBNAME + "." + "author a");
            sql.append(" WHERE a.idAuthor = ap.idAuthor AND ap.idAuthor != ?");
            sql.append(" AND ap.idPaper in (SELECT ap.idPaper FROM " + PubGuruDB.DBNAME + "." + "author_paper ap WHERE ap.idAuthor = ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            stmt.setInt(2, idAuthor);
            ResultSet rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                int coidAuthor = rs.getInt("idAuthor");
                coidAuthorList.add(new Integer(coidAuthor));
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

        return coidAuthorList;
    }

    /**
     * getAuthorPaperList
     * @param idAuthor
     * @return
     * @throws Exception
     */
    public ArrayList getAuthorPaperDTOList(int idAuthor) throws Exception {
        ArrayList authorPaperList = new ArrayList();
        try {
            Connection conn = this.getConnection();
            // get the list of publication relate to this author and add to the vertex
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT ap.idPaper, p.idConference, p.idJournal");
            sql.append(" FROM " + PubGuruDB.DBNAME + "." + "author_paper ap, " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" WHERE ap.idPaper =  p.idPaper");
            sql.append(" AND ap.idAuthor = ?");
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            ResultSet rs = stmt.executeQuery();
            int idPaper = -1;
            while (rs != null && rs.next()) {
                idPaper = rs.getInt("idPaper");                
                //int idConference = rs.getInt("idConference");
                //int idJournal = rs.getInt("idJournal");
                AuthorPaperDTO authorPaperDTO = new AuthorPaperDTO();
                authorPaperDTO.setIdAuthor(idAuthor);
                authorPaperDTO.setIdPaper(idPaper);                
                //authorPaperDTO.setidConference(idConference);
                //authorPaperDTO.setidJournal(idJournal);
                authorPaperList.add(authorPaperDTO);
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
        return authorPaperList;
    }

    /**
     * getTotalPaperInEachConfOfAnAuthor
     * @param idAuthor
     * @return
     * @throws Exception
     */
    public HashMap getTotalPaperInEachConfOfAnAuthor(int idAuthor) throws Exception {
         /* Tim cac conference ma tac gia da goi bai, so bai moi confernece */
        HashMap confPaperCountMap = new HashMap();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT c.idConference, c.conferenceName, count(c.conferenceName) as ct");
            sql.append(" FROM " + PubGuruDB.DBNAME + "." + "conference c, " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" WHERE p.idConference = c.idConference AND p.idPaper in ");
            sql.append(" (SELECT ap.idPaper FROM " + PubGuruDB.DBNAME + "." + "author_paper ap WHERE ap.idAuthor = ?)");
            sql.append(" GROUP BY c.conferenceName ORDER BY ct");
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            ResultSet rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                String conferenceName = rs.getString("conferenceName");
                int count = rs.getInt("ct");
                confPaperCountMap.put(conferenceName, new Integer(count));
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
        return confPaperCountMap;
    }

    /**
     * getTotalPaperInEachJourOfAnAuthor
     * @param idAuthor
     * @return
     * @throws Exception
     */
    public HashMap getTotalPaperInEachJourOfAnAuthor(int idAuthor) throws Exception {
        HashMap jourPaperCountMap = new HashMap();
        try {
            /* Tim cac journal ma tac gia da goi bai, so bai moi journal */
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT j.idJournal, j.journalName, count(j.journalName) as ct");
            sql.append(" FROM " + PubGuruDB.DBNAME + "." + "journal j, " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" WHERE p.idJournal = j.idJournal AND p.idPaper in ");
            sql.append(" (SELECT ap.idPaper FROM " + PubGuruDB.DBNAME + "." + "author_paper ap WHERE ap.idAuthor = ?)");
            sql.append(" GROUP BY j.journalName ORDER BY ct");
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            stmt.setInt(1, idAuthor);
            ResultSet rs = stmt.executeQuery();

            while (rs != null && rs.next()) {
                String journalName = rs.getString("journalName");
                int count = rs.getInt("ct");
                jourPaperCountMap.put(journalName, new Integer(count));
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

        return jourPaperCountMap;
    }

    /**
     * getTotalPaperInEachConfOfAuthorList
     * @param idAuthorList
     * @return confPaperCountHashMap
     * @throws Exception
     */
    public HashMap getTotalPaperInEachConfOfAuthorList(ArrayList idAuthorList) throws Exception {
        HashMap confPaperCountHashMap = new HashMap();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT c.idConference, c.conferenceName, count(c.conferenceName) as ct");
            sql.append(" FROM " + PubGuruDB.DBNAME + "." + "conference c, " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" WHERE p.idConference = c.idConference AND p.idPaper IN ");
            sql.append(" (SELECT ap.idPaper FROM " + PubGuruDB.DBNAME + "." + "author_paper ap WHERE ap.idAuthor IN (");
            for (int i=0; i<idAuthorList.size(); i++) {
                String curidAuthor = (String) idAuthorList.get(i);
                if (i == idAuthorList.size()-1)
                    sql.append(curidAuthor);
                else
                    sql.append(curidAuthor + ",");
            }
            sql.append(" ))");
            sql.append(" GROUP BY c.conferenceName ORDER BY ct");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs != null && rs.next()) {
                String confName = rs.getString("conferenceName");
                int count = rs.getInt("ct");
                confPaperCountHashMap.put(confName, new Double(count));
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

        return confPaperCountHashMap;
    }

    /**
     * getTotalPaperInEachConfOfAuthorList
     * @param idAuthorList
     * @param confNameList
     * @return
     * @throws Exception
     */
    public HashMap getTotalPaperInEachConfOfAuthorList(ArrayList idAuthorList, ArrayList confNameList) throws Exception {
        HashMap confPaperCountHashMap = new HashMap();
        try {
            if ((idAuthorList == null) || (idAuthorList.size() == 0)) {
                if (confNameList != null && confNameList.size() > 0) {
                    for (int i=0; i<confNameList.size(); i++) {
                        confPaperCountHashMap.put(confNameList.get(i), new Double(0));
                    }
                }
                return confPaperCountHashMap;
            }

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT c.idConference, c.conferenceName, count(c.conferenceName) as ct");
            sql.append(" FROM " + PubGuruDB.DBNAME + "." + "conference c, " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" WHERE p.idConference = c.idConference AND p.idPaper IN ");
            sql.append(" (SELECT ap.idPaper FROM " + PubGuruDB.DBNAME + "." + "author_paper ap WHERE ap.idAuthor IN (");
            for (int i=0; i<idAuthorList.size(); i++) {
                String curidAuthor = (String) idAuthorList.get(i);
                if (i == idAuthorList.size()-1)
                    sql.append(curidAuthor);
                else
                    sql.append(curidAuthor + ",");
            }
            sql.append(" ))");
            sql.append(" AND c.conferenceName IN (");
            for (int i=0; i<confNameList.size(); i++) {
                if (i == confNameList.size()-1)
                    sql.append("?");
                else
                    sql.append("?,");
            }
            sql.append(" )");

            sql.append(" GROUP BY c.conferenceName ORDER BY ct");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            for (int i=0; i<confNameList.size(); i++) {
                stmt.setString(i+1, (String) confNameList.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                String confName = rs.getString("conferenceName");
                double count = rs.getDouble("ct");
                if (confName.equalsIgnoreCase("International Conference on Hardware Software Codesign - CODES")
                        || confName.equalsIgnoreCase("International Symposium on Systems Synthesis - ISSS")
                        || confName.equalsIgnoreCase("International Conference on Hardware Software Codesign - CODES+ISSS")) {

                    Double curValue = null;
                    curValue = (Double) confPaperCountHashMap.get("International Conference on Hardware Software Codesign - CODES");
                    if (curValue == null) {
                        curValue = (Double) confPaperCountHashMap.get("International Symposium on Systems Synthesis - ISSS");
                        if (curValue == null)
                            curValue = (Double) confPaperCountHashMap.get("International Conference on Hardware Software Codesign - CODES+ISSS");
                    }
                    if (curValue != null) {
                        count += curValue.doubleValue();
                    }
                    
                    confPaperCountHashMap.put(confName, new Double(count));
                }
                else {
                    confPaperCountHashMap.put(confName, new Double(count));
                }
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

        return confPaperCountHashMap;
    }

    /**
     * getTotalPaperInEachJourOfAuthorList
     * @param idAuthorList
     * @return
     * @throws Exception
     */
    public HashMap getTotalPaperInEachJourOfAuthorList(ArrayList idAuthorList) throws Exception {
        HashMap jourPaperCountHashMap = new HashMap();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT j.idJournal, j.journalName, count(j.journalName) as ct");
            sql.append(" FROM " + PubGuruDB.DBNAME + "." + "journal j, " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" WHERE p.idJournal = j.idJournal AND p.idPaper IN ");
            sql.append(" (SELECT ap.idPaper FROM " + PubGuruDB.DBNAME + "." + "author_paper ap WHERE ap.idAuthor IN (");
            for (int i=0; i<idAuthorList.size(); i++) {
                String curidAuthor = (String) idAuthorList.get(i);
                if (i == idAuthorList.size()-1)
                    sql.append(curidAuthor);
                else
                    sql.append(curidAuthor + ",");
            }
            sql.append(" ))");
            sql.append(" GROUP BY j.journalName ORDER BY ct");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs != null && rs.next()) {
                String jourName = rs.getString("journalName");
                int count = rs.getInt("ct");
                jourPaperCountHashMap.put(jourName, new Double(count));
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

        return jourPaperCountHashMap;
    }

    /**
     * getTotalPaperInEachJourOfAuthorList
     * @param idAuthorList
     * @param jourNameList
     * @return jourPaperCountHashMap
     * @throws Exception
     */
    public HashMap getTotalPaperInEachJourOfAuthorList(ArrayList idAuthorList, ArrayList jourNameList) throws Exception {
        HashMap jourPaperCountHashMap = new HashMap();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT j.idJournal, j.journalName, count(j.journalName) as ct");
            sql.append(" FROM " + PubGuruDB.DBNAME + "." + "journal j, " + PubGuruDB.DBNAME + "." + "paper p");
            sql.append(" WHERE p.idJournal = j.idJournal AND p.idPaper IN ");
            sql.append(" (SELECT ap.idPaper FROM " + PubGuruDB.DBNAME + "." + "author_paper ap WHERE ap.idAuthor IN (");
            for (int i=0; i<idAuthorList.size(); i++) {
                String curidAuthor = (String) idAuthorList.get(i);
                if (i == idAuthorList.size()-1)
                    sql.append(curidAuthor);
                else
                    sql.append(curidAuthor + ",");
            }
            sql.append(" ))");
            sql.append(" j.journalName IN (");
            for (int i=0; i<jourNameList.size(); i++) {
                if (i == jourNameList.size()-1)
                    sql.append("?");
                else
                    sql.append("?,");
            }
            sql.append(" )");

            sql.append(" GROUP BY j.journalName ORDER BY ct");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            for (int i=0; i<jourNameList.size(); i++) {
                stmt.setString(i+1, (String) jourNameList.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                String jourName = rs.getString("journalName");
                double count = rs.getDouble("ct");
                if (jourName.equalsIgnoreCase("International Conference on Hardware Software Codesign - CODES") ||
                        jourName.equalsIgnoreCase("International Symposium on Systems Synthesis - ISSS")) {

                    Double curValue = null;
                    curValue = (Double) jourPaperCountHashMap.get("International Conference on Hardware Software Codesign - CODES");
                    if (curValue == null) {
                        curValue = (Double) jourPaperCountHashMap.get("International Symposium on Systems Synthesis - ISSS");
                    }
                    if (curValue != null) {
                        count += curValue.doubleValue();
                    }

                    jourPaperCountHashMap.put(jourName, new Double(count));
                }
                else {
                    jourPaperCountHashMap.put(jourName, new Double(count));
                }
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

        return jourPaperCountHashMap;
    }

    /**
     * Count the the total of number paper of an author in a conference
     * @param idAuthor
     * @param confName
     * @return
     * @throws Exception
     */
    public double countTotalPaperInConfOfAnAuthor(int idAuthor, String confName) throws Exception {
        double totalPaperOfAuthorInConf = 0;
        try {
            // Count the the total of number paper of an author in a conference
            StringBuffer sqlStr = new StringBuffer();

            if (confName.equalsIgnoreCase("International Conference on Hardware Software Codesign - CODES")
                        || confName.equalsIgnoreCase("International Symposium on Systems Synthesis - ISSS")
                        || confName.equalsIgnoreCase("International Conference on Hardware Software Codesign - CODES+ISSS")) {
                
                sqlStr.append(" SELECT count(*) as totalPaperOfAuthorInConf");
                sqlStr.append(" FROM " + PubGuruDB.DBNAME + "." + "conference c, " + PubGuruDB.DBNAME + "." + "paper p, " + PubGuruDB.DBNAME + "." + "author_paper ap");
                sqlStr.append(" WHERE p.idConference = c.idConference AND ap.idPaper = p.idPaper");
                sqlStr.append(" AND ap.idAuthor = ? AND (c.conferenceName = ? OR c.conferenceName = ? OR c.conferenceName = ?)");
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlStr.toString());
                stmt.setInt(1, idAuthor);
                stmt.setString(2, "International Conference on Hardware Software Codesign - CODES");
                stmt.setString(3, "International Symposium on Systems Synthesis - ISSS");
                stmt.setString(4, "International Conference on Hardware Software Codesign - CODES+ISSS");
                ResultSet rs = stmt.executeQuery();
                if (rs != null && rs.next()) {
                    totalPaperOfAuthorInConf = rs.getInt("totalPaperOfAuthorInConf");
                    rs.close();
                }
                stmt.close();

            }
            else {
                sqlStr.append(" SELECT count(*) as totalPaperOfAuthorInConf");
                sqlStr.append(" FROM " + PubGuruDB.DBNAME + "." + "conference c, " + PubGuruDB.DBNAME + "." + "paper p, " + PubGuruDB.DBNAME + "." + "author_paper ap");
                sqlStr.append(" WHERE p.idConference = c.idConference AND ap.idPaper = p.idPaper");
                sqlStr.append(" AND c.conferenceName = ? AND ap.idAuthor = ?");
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlStr.toString());
                stmt.setString(1, confName);
                stmt.setInt(2, idAuthor);
                ResultSet rs = stmt.executeQuery();
                if (rs != null && rs.next()) {
                    totalPaperOfAuthorInConf = rs.getInt("totalPaperOfAuthorInConf");
                    rs.close();
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
        return totalPaperOfAuthorInConf;
    }

    /**
     * Count the the total of number paper of an author in a journal
     * @param idAuthor
     * @param jourName
     * @return
     * @throws Exception
     */
    public double countTotalPaperInJourOfAnAuthor(int idAuthor, String jourName) throws Exception {
        double totalPaperOfAuthorInJour = 0;
        try {
            // Count the the total of number paper of an author in a journal
            StringBuffer sqlStr = new StringBuffer();
            sqlStr.append(" SELECT count(*) as totalPaperOfAuthorInJour");
            sqlStr.append(" FROM " + PubGuruDB.DBNAME + "." + "journal j, " + PubGuruDB.DBNAME + "." + "paper p, " + PubGuruDB.DBNAME + "." + "author_paper ap");
            sqlStr.append(" WHERE p.idJournal = j.idJournal AND ap.idPaper = p.idPaper");
            sqlStr.append(" AND j.journalName = ? AND ap.idAuthor = ?");
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlStr.toString());
            stmt.setString(1, jourName);
            stmt.setInt(2, idAuthor);
            ResultSet rs = stmt.executeQuery();

            if (rs != null && rs.next()) {
                totalPaperOfAuthorInJour = rs.getInt("totalPaperOfAuthorInJour");
                rs.close();
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

        return totalPaperOfAuthorInJour;
    }

    /**
     * Count the the total of number paper of an author
     * @param idAuthor
     * @return
     * @throws Exception
     */
    public double countTotalPaperOfAnAuthor(int idAuthor) throws Exception {
        double totalPaperOfAuthor = 0;
        try {
            // Count the the total of number paper of an author
            StringBuffer sqlStr = new StringBuffer();
            sqlStr.append(" SELECT COUNT(*) as totalPaperOfAuthor");
            sqlStr.append(" FROM " + PubGuruDB.DBNAME + "." + "author_paper ap");
            sqlStr.append(" WHERE ap.idAuthor = ?");
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlStr.toString());
            stmt.setInt(1, idAuthor);
            ResultSet rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                totalPaperOfAuthor = rs.getInt("totalPaperOfAuthor");
                rs.close();
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

        return totalPaperOfAuthor;
    }

    /**
     * countTotalPaperOfTwoAuthorIsCoAuthor
     * @param author1
     * @param author2
     * @return
     * @throws Exception
     */
    public double countTotalPaperOfTwoAuthorIsCoAuthor (int author1, int author2)  throws Exception {
        double numberOfPapersCoAuthor = 0;
        try {
            // count the number of papers which author1 & author2 is the co-author
            StringBuffer sqlStr = new StringBuffer();
            sqlStr.append(" SELECT COUNT(*) as ct FROM " + PubGuruDB.DBNAME + "." + "author_paper ap");
            sqlStr.append(" WHERE ap.idAuthor = ? AND ap.idPaper IN ");
            sqlStr.append(" (SELECT ap.idPaper FROM " + PubGuruDB.DBNAME + "." + "author_paper ap WHERE ap.idAuthor = ?)");
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlStr.toString());
            stmt.setInt(1, author1);
            stmt.setInt(2, author2);
            ResultSet rs = stmt.executeQuery();
            if (rs != null & rs.next()) {
                numberOfPapersCoAuthor = rs.getDouble("ct");
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

        return numberOfPapersCoAuthor;
    }
}
