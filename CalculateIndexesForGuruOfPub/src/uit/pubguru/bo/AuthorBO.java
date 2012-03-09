/**
 * @author Tin Huynh
 * @author Nghiep H. Tran
 * @author Loc Do
 * @author Huong Tran
 */
package uit.pubguru.bo;

import uit.pubguru.dbaccess.*;
import uit.pubguru.dto.*;
import uit.pubguru.utility.PubGuruLogger;
    
public class AuthorBO {
    
    /**
     * insert a new author into the DB ...
     * @param authorDTO
     * @throws Exception
     */
    public int insertNewAuthor(AuthorDTO authorDTO) throws Exception {
        int newAuthorID = -1;
        AuthorMapper mapper = null;
        AuthorInstanceMapper authorInstanceMapper = new AuthorInstanceMapper();
        try {
            mapper = new AuthorMapper();
            if (mapper.isExisted(authorDTO)>0) {
                newAuthorID = mapper.insertObj(authorDTO);
                authorInstanceMapper.insertNewAuthorInstance(newAuthorID, authorDTO.getAuthorName());
            }
            return newAuthorID;
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
        finally {
            mapper.closeConnection();
            authorInstanceMapper.closeConnection();
        }
    }
	
    /**
     * get authorID from authorName
     * @param authorName
     * @return
     * @throws Exception
     */
    public int getAuthorID(String authorName) throws Exception {
        AuthorMapper mapper = null;
        try {
            mapper = new AuthorMapper();
            return mapper.getIDObj(authorName);
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
        finally {
            mapper.closeConnection();
        }
    }
}
