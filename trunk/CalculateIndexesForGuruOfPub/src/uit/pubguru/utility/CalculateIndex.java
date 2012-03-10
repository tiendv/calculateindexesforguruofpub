/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.pubguru.utility;

import java.util.ArrayList;
import uit.pubguru.constant.PubGuruConst;
import uit.pubguru.dbaccess.RankMapper;
import uit.pubguru.dto.PaperDTO;

/**
 *
 * @author THNghiep
 */
public class CalculateIndex {

    /**
     * calculateIndexAuthor
     * @throws Exception 
     */
    public void calculateIndexAuthor() throws Exception {
        ArrayList authorIdList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            authorIdList = rankMapper.getAuthorIdList();
            // For each authorId.
            for (Object o : authorIdList) {
                int idAuthor = Integer.parseInt(o.toString());
                publicationList = rankMapper.getPublicationDTOListByAuthorId(idAuthor);
                // Calculate h-index for each author.
                h_index = 0;
                while (h_index < publicationList.size()) {
                    citationCount = ((PaperDTO) publicationList.get(h_index)).getCitationCount();
                    if (citationCount >= (h_index + 1)) {
                        h_index++;
                    } else {
                        break;
                    }
                }
                // Calculate g-index for each author.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if ((h_index != 0) || (g_index != 0)) {
                    rankMapper.saveIndexesAuthor(idAuthor, h_index, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexAuthorSubdomain
     * @throws Exception 
     */
    public void calculateIndexAuthorSubdomain() throws Exception {
        ArrayList<IdDTO> idList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            idList = rankMapper.getAuthorSubdomainIdList(PubGuruConst.LIMIT_BULK_LOAD_ID);
            // For each id.
            for (IdDTO idDTO : idList) {
                int idAuthor = idDTO.getId1();
                int idSubdomain = idDTO.getId2();
                publicationList = rankMapper.getPublicationDTOListByAuthorIdSubdomainId(idAuthor, idSubdomain);
                // Calculate h-index for each author.
                h_index = 0;
                while (h_index < publicationList.size()) {
                    citationCount = ((PaperDTO) publicationList.get(h_index)).getCitationCount();
                    if (citationCount >= (h_index + 1)) {
                        h_index++;
                    } else {
                        break;
                    }
                }
                // Calculate g-index for each author.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if ((h_index != 0) || (g_index != 0)) {
                    rankMapper.saveIndexesAuthorSubdomain(idAuthor, idSubdomain, h_index, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexAuthorKeyword
     * @throws Exception 
     * 
     * Note: This method runs very slow.
     * + Reasons:
     * - Too many author and keyword so that number of loop may reach 10 billion with 400000 authors and 25000 keywords.
     * - In each loop, code is not optimized: Query DB in each loop, especially creating too many objects by new Comparator<PaperDTO>() method.
     * + Solutions:
     * - After running stored procedure to generate ranking data, 
     * only those authors and keywords in the ranking table should be considered to calculate indexes because only them have h_index or g_index > 0.
     * - Load bulk data and store in memory.
     * - Use another manner to handle sorting task so that not too many objects are created.
     * + Status: Fixed.
     * - Get ids from rank tables.
     * - Use sorting method of sql.
     * - Reduce data transfered through sql query.
     * 
     * + Future optimization:
     * - Use plain old array of int instead of ArrayList: use 2D array, select count(*) to get size.
     */
    public void calculateIndexAuthorKeyword() throws Exception {
        ArrayList<IdDTO> idList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            idList = rankMapper.getAuthorKeywordIdList(PubGuruConst.LIMIT_BULK_LOAD_ID);
            // For each id.
            for (IdDTO idDTO : idList) {
                int idAuthor = idDTO.getId1();
                int idKeyword = idDTO.getId2();
                publicationList = rankMapper.getPublicationDTOListByAuthorIdKeywordId(idAuthor, idKeyword);
                // Calculate h-index for each author.
                h_index = 0;
                while (h_index < publicationList.size()) {
                    citationCount = ((PaperDTO) publicationList.get(h_index)).getCitationCount();
                    if (citationCount >= (h_index + 1)) {
                        h_index++;
                    } else {
                        break;
                    }
                }
                // Calculate g-index for each author.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if ((h_index != 0) || (g_index != 0)) {
                    rankMapper.saveIndexesAuthorKeyword(idAuthor, idKeyword, h_index, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexOrg
     * @throws Exception 
     */
    public void calculateIndexOrg() throws Exception {
        ArrayList orgIdList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            orgIdList = rankMapper.getOrgIdList();
            // For each orgId.
            for (Object o : orgIdList) {
                int idOrg = Integer.parseInt(o.toString());
                publicationList = rankMapper.getPublicationDTOListByOrgId(idOrg);
                // Calculate h-index for each org.
                h_index = 0;
                while (h_index < publicationList.size()) {
                    citationCount = ((PaperDTO) publicationList.get(h_index)).getCitationCount();
                    if (citationCount >= (h_index + 1)) {
                        h_index++;
                    } else {
                        break;
                    }
                }
                // Calculate g-index for each org.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if ((h_index != 0) || (g_index != 0)) {
                    rankMapper.saveIndexesOrg(idOrg, h_index, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexOrgSubdomain
     * @throws Exception 
     */
    public void calculateIndexOrgSubdomain() throws Exception {
        ArrayList<IdDTO> idList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            idList = rankMapper.getOrgSubdomainIdList(PubGuruConst.LIMIT_BULK_LOAD_ID);
            // For each id.
            for (IdDTO idDTO : idList) {
                int idOrg = idDTO.getId1();
                int idSubdomain = idDTO.getId2();
                publicationList = rankMapper.getPublicationDTOListByOrgIdSubdomainId(idOrg, idSubdomain);
                // Calculate h-index for each org.
                h_index = 0;
                while (h_index < publicationList.size()) {
                    citationCount = ((PaperDTO) publicationList.get(h_index)).getCitationCount();
                    if (citationCount >= (h_index + 1)) {
                        h_index++;
                    } else {
                        break;
                    }
                }
                // Calculate g-index for each org.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if ((h_index != 0) || (g_index != 0)) {
                    rankMapper.saveIndexesOrgSubdomain(idOrg, idSubdomain, h_index, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexOrgKeyword
     * @throws Exception 
     */
    public void calculateIndexOrgKeyword() throws Exception {
        ArrayList<IdDTO> idList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            idList = rankMapper.getOrgKeywordIdList(PubGuruConst.LIMIT_BULK_LOAD_ID);
            // For each id.
            for (IdDTO idDTO : idList) {
                int idOrg = idDTO.getId1();
                int idKeyword = idDTO.getId2();
                publicationList = rankMapper.getPublicationDTOListByOrgIdKeywordId(idOrg, idKeyword);
                // Calculate h-index for each org.
                h_index = 0;
                while (h_index < publicationList.size()) {
                    citationCount = ((PaperDTO) publicationList.get(h_index)).getCitationCount();
                    if (citationCount >= (h_index + 1)) {
                        h_index++;
                    } else {
                        break;
                    }
                }
                // Calculate g-index for each author.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if ((h_index != 0) || (g_index != 0)) {
                    rankMapper.saveIndexesOrgKeyword(idOrg, idKeyword, h_index, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexJournal
     * @throws Exception 
     */
    public void calculateIndexJournal() throws Exception {
        ArrayList journalIdList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int g_index;
        int citationCountSum;

        try {
            journalIdList = rankMapper.getJournalIdList();
            // For each journalId.
            for (Object o : journalIdList) {
                int idJournal = Integer.parseInt(o.toString());
                publicationList = rankMapper.getPublicationDTOListByJournalId(idJournal);
                // Calculate g-index for each journal.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if (g_index != 0) {
                    rankMapper.saveIndexesJournal(idJournal, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexJournalSubdomain
     * @throws Exception 
     */
    public void calculateIndexJournalSubdomain() throws Exception {
        ArrayList<IdDTO> idList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int g_index;
        int citationCountSum;

        try {
            idList = rankMapper.getJournalSubdomainIdList(PubGuruConst.LIMIT_BULK_LOAD_ID);
            // For each id.
            for (IdDTO idDTO : idList) {
                int idJournal = idDTO.getId1();
                int idSubdomain = idDTO.getId2();
                publicationList = rankMapper.getPublicationDTOListByJournalIdSubdomainId(idJournal, idSubdomain);
                // Calculate g-index for each journal.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if (g_index != 0) {
                    rankMapper.saveIndexesJournalSubdomain(idJournal, idSubdomain, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexJournalKeyword
     * @throws Exception 
     */
    public void calculateIndexJournalKeyword() throws Exception {
        ArrayList<IdDTO> idList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int g_index;
        int citationCountSum;

        try {
            idList = rankMapper.getJournalKeywordIdList(PubGuruConst.LIMIT_BULK_LOAD_ID);
            // For each id.
            for (IdDTO idDTO : idList) {
                int idJournal = idDTO.getId1();
                int idKeyword = idDTO.getId2();
                publicationList = rankMapper.getPublicationDTOListByJournalIdKeywordId(idJournal, idKeyword);
                // Calculate g-index for each journal.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if (g_index != 0) {
                    rankMapper.saveIndexesJournalKeyword(idJournal, idKeyword, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexConference
     * @throws Exception 
     */
    public void calculateIndexConference() throws Exception {
        ArrayList conferenceIdList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int g_index;
        int citationCountSum;

        try {
            conferenceIdList = rankMapper.getConferenceIdList();
            // For each ConferenceId.
            for (Object o : conferenceIdList) {
                int idConference = Integer.parseInt(o.toString());
                publicationList = rankMapper.getPublicationDTOListByConferenceId(idConference);
                // Calculate g-index for each conference.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if (g_index != 0) {
                    rankMapper.saveIndexesConference(idConference, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexConference
     * @throws Exception 
     */
    public void calculateIndexConferenceSubdomain() throws Exception {
        ArrayList<IdDTO> idList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int g_index;
        int citationCountSum;

        try {
            idList = rankMapper.getConferenceSubdomainIdList(PubGuruConst.LIMIT_BULK_LOAD_ID);
            // For each id.
            for (IdDTO idDTO : idList) {
                int idConference = idDTO.getId1();
                int idSubdomain = idDTO.getId2();
                publicationList = rankMapper.getPublicationDTOListByConferenceIdSubdomainId(idConference, idSubdomain);
                // Calculate g-index for each conference.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if (g_index != 0) {
                    rankMapper.saveIndexesConferenceSubdomain(idConference, idSubdomain, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }

    /**
     * calculateIndexConference
     * @throws Exception 
     */
    public void calculateIndexConferenceKeyword() throws Exception {
        ArrayList<IdDTO> idList;
        ArrayList publicationList;
        RankMapper rankMapper = new RankMapper();
        int g_index;
        int citationCountSum;

        try {
            idList = rankMapper.getConferenceKeywordIdList(PubGuruConst.LIMIT_BULK_LOAD_ID);
            // For each id.
            for (IdDTO idDTO : idList) {
                int idConference = idDTO.getId1();
                int idKeyword = idDTO.getId2();
                publicationList = rankMapper.getPublicationDTOListByConferenceIdKeywordId(idConference, idKeyword);
                // Calculate g-index for each conference.
                g_index = 0;
                citationCountSum = 0;
                while (true) {
                    if (g_index < publicationList.size()) {
                        citationCountSum += ((PaperDTO) publicationList.get(g_index)).getCitationCount();
                    }
                    if (citationCountSum >= ((g_index + 1) * (g_index + 1))) {
                        g_index++;
                    } else {
                        break;
                    }
                }
                // Save indexes to DB.
                if (g_index != 0) {
                    rankMapper.saveIndexesConferenceKeyword(idConference, idKeyword, g_index);
                }
            }
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
            rankMapper.closeConnection();
        }
    }
}
