package uit.pubguru.bo;

import java.util.ArrayList;
import uit.pubguru.dbaccess.*;
import uit.pubguru.dto.*;
import uit.pubguru.utility.PubGuruLogger;

/**
 * @author Tin Huynh
 * @author Loc Do
 * @author Huong Tran
 */
public class PaperBO {

    /**
     * check if the specified paper exist in the DB
     * @param paperDTO
     * @return
     * @throws Exception
     */
    public int isPaperExisted(PaperDTO paperDTO) throws Exception {
        PaperMapper mapper = null;
        try {
            mapper = new PaperMapper();
            return mapper.isExisted(paperDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            mapper.closeConnection();
        }
    }

    /**
     * insert a new paper into the DB
     * @param paperDTO
     * @return
     * @throws Exception
     */
    public int insertNewPaper(PaperDTO paperDTO) throws Exception {
        PaperMapper mapper = null;
        try {
            mapper = new PaperMapper();
            return mapper.insertObj(paperDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            mapper.closeConnection();
        }
    }

    public ArrayList searchPaper(String keyword) throws Exception {
        ArrayList paperDTOList = new ArrayList();
        PaperMapper paperMapper = new PaperMapper();
        String[] keywords = null;

        try {
            keywords = keyword.split(" ");
            paperDTOList = paperMapper.searchPaper(keywords, 100);

            return paperDTOList;
        } catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            paperMapper.closeConnection();
        }
    }
}
