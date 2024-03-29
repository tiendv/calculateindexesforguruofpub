package uit.pubguru.dbaccess;

import uit.pubguru.utility.PubGuruLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import uit.pubguru.dto.database.PubGuruDB;

/**
 * @author Huynh Ngoc Tin
 * @author Nghiep Tran
 * @author Huong Tran
 */
public class AuthorInstanceMapper extends MapperDB{
	
    public AuthorInstanceMapper() throws Exception {
        super();
    }

    public AuthorInstanceMapper(Connection con) {
        super(con);
            // TODO Auto-generated constructor stub
    }

    /**
     * insert a new instanceName for the specified author into the DB
     * @param authorDTO
     * @throws Exception
     */
    public void insertNewAuthorInstance(int authorID, String instanceName) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO " + PubGuruDB.DBNAME + "." + "authorinstance(instanceName, authorID) ");
            sql.append(" VALUES(?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, instanceName);
            stmt.setInt(2, authorID);

            stmt.execute();
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
    }
}
