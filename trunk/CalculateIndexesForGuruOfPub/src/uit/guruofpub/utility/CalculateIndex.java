/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.guruofpub.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import uit.guruofpub.dbaccess.AuthorMapper;
import uit.guruofpub.dbaccess.AuthorPaperMapper;
import uit.guruofpub.dbaccess.ConferenceMapper;
import uit.guruofpub.dbaccess.JournalMapper;
import uit.guruofpub.dbaccess.KeywordMapper;
import uit.guruofpub.dbaccess.OrgMapper;
import uit.guruofpub.dbaccess.PaperMapper;
import uit.guruofpub.dbaccess.SubdomainMapper;
import uit.guruofpub.dto.AuthorDTO;
import uit.guruofpub.dto.PaperDTO;

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
        AuthorMapper authorMapper = new AuthorMapper();
        AuthorPaperMapper authorPaperMapper = new AuthorPaperMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            authorIdList = authorMapper.getAuthorIdList();
            // For each authorId.
            for (Object o : authorIdList) {
                int idAuthor = Integer.parseInt(o.toString());
                publicationList = authorPaperMapper.getPublicationDTOListByAuthorId(idAuthor);
                // Sort publicationList of author by citationCount.
                Collections.sort(publicationList, new Comparator<PaperDTO>() {

                    public int compare(PaperDTO one, PaperDTO other) {
                        int result = 0;
                        try {
                            if (one.getCitationCount() > other.getCitationCount()) {
                                result = -1;
                            } else if (one.getCitationCount() < other.getCitationCount()) {
                                result = 1;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                            Object[] arrObj = ex.getStackTrace();
                            if (arrObj != null) {
                                for (Object stackTraceElement : arrObj) {
                                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                }
                            }
                        }
                        return result;
                    }
                });
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
                    authorMapper.saveIndexes(idAuthor, h_index, g_index);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            authorMapper.closeConnection();
            authorPaperMapper.closeConnection();
        }
    }

    /**
     * calculateIndexAuthorSubdomain
     * @throws Exception 
     */
    public void calculateIndexAuthorSubdomain() throws Exception {
        ArrayList subdomainIdList;
        ArrayList authorIdList;
        ArrayList publicationList;
        SubdomainMapper subdomainMapper = new SubdomainMapper();
        AuthorMapper authorMapper = new AuthorMapper();
        AuthorPaperMapper authorPaperMapper = new AuthorPaperMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            subdomainIdList = subdomainMapper.getSubdomainIdList();
            authorIdList = authorMapper.getAuthorIdList();
            // For each authorId.
            for (Object o : authorIdList) {
                for (Object o2 : subdomainIdList) {
                    int idAuthor = Integer.parseInt(o.toString());
                    int idSubdomain = Integer.parseInt(o2.toString());
                    publicationList = authorPaperMapper.getPublicationDTOListByAuthorIdSubdomainId(idAuthor, idSubdomain);
                    // Sort publicationList of author by citationCount.
                    Collections.sort(publicationList, new Comparator<PaperDTO>() {

                        public int compare(PaperDTO one, PaperDTO other) {
                            int result = 0;
                            try {
                                if (one.getCitationCount() > other.getCitationCount()) {
                                    result = -1;
                                } else if (one.getCitationCount() < other.getCitationCount()) {
                                    result = 1;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                                Object[] arrObj = ex.getStackTrace();
                                if (arrObj != null) {
                                    for (Object stackTraceElement : arrObj) {
                                        GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                    }
                                }
                            }
                            return result;
                        }
                    });
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
                        authorMapper.saveIndexesAuthorSubdomain(idAuthor, idSubdomain, h_index, g_index);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            authorMapper.closeConnection();
            authorPaperMapper.closeConnection();
            subdomainMapper.closeConnection();
        }
    }

    /**
     * calculateIndexAuthorKeyword
     * @throws Exception 
     * Note: This method runs very slow.
     * Reasons:
     * - Too many author and keyword so that number of loop may reach 10 billion. 
     * - In each loop, code is not optimized: Query DB in each loop, especially creating too many objects by new Comparator<PaperDTO>() method.
     * Solutions:
     * - After running stored procedure to generate ranking data, 
     * only those authors and keywords in the ranking table should be considered to calculate indexes because only them have h_index and g_index > 0.
     * - Load bulk data and store in memory.
     * - Use another manner to handle sorting task so that not too many objects are created.
     */
    public void calculateIndexAuthorKeyword() throws Exception {
        ArrayList keywordIdList;
        ArrayList authorIdList;
        ArrayList publicationList;
        KeywordMapper keywordMapper = new KeywordMapper();
        AuthorMapper authorMapper = new AuthorMapper();
        AuthorPaperMapper authorPaperMapper = new AuthorPaperMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            keywordIdList = keywordMapper.getKeywordIdList();
            authorIdList = authorMapper.getAuthorIdList();
            // For each authorId.
            for (Object o : authorIdList) {
                for (Object o2 : keywordIdList) {
                    int idAuthor = Integer.parseInt(o.toString());
                    int idKeyword = Integer.parseInt(o2.toString());
                    publicationList = authorPaperMapper.getPublicationDTOListByAuthorIdKeywordId(idAuthor, idKeyword);
                    // Sort publicationList of author by citationCount.
                    Collections.sort(publicationList, new Comparator<PaperDTO>() {

                        public int compare(PaperDTO one, PaperDTO other) {
                            int result = 0;
                            try {
                                if (one.getCitationCount() > other.getCitationCount()) {
                                    result = -1;
                                } else if (one.getCitationCount() < other.getCitationCount()) {
                                    result = 1;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                                Object[] arrObj = ex.getStackTrace();
                                if (arrObj != null) {
                                    for (Object stackTraceElement : arrObj) {
                                        GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                    }
                                }
                            }
                            return result;
                        }
                    });
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
                        authorMapper.saveIndexesAuthorKeyword(idAuthor, idKeyword, h_index, g_index);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            authorMapper.closeConnection();
            authorPaperMapper.closeConnection();
            keywordMapper.closeConnection();
        }
    }

    /**
     * calculateIndexOrg
     * @throws Exception 
     */
    public void calculateIndexOrg() throws Exception {
        ArrayList orgIdList;
        ArrayList publicationList;
        OrgMapper orgMapper = new OrgMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            orgIdList = orgMapper.getOrgIdList();
            // For each orgId.
            for (Object o : orgIdList) {
                int idOrg = Integer.parseInt(o.toString());
                publicationList = orgMapper.getPublicationDTOListByOrgId(idOrg);
                // Sort publicationList of org by citationCount.
                Collections.sort(publicationList, new Comparator<PaperDTO>() {

                    public int compare(PaperDTO one, PaperDTO other) {
                        int result = 0;
                        try {
                            if (one.getCitationCount() > other.getCitationCount()) {
                                result = -1;
                            } else if (one.getCitationCount() < other.getCitationCount()) {
                                result = 1;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                            Object[] arrObj = ex.getStackTrace();
                            if (arrObj != null) {
                                for (Object stackTraceElement : arrObj) {
                                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                }
                            }
                        }
                        return result;
                    }
                });
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
                    orgMapper.saveIndexes(idOrg, h_index, g_index);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            orgMapper.closeConnection();
        }
    }

    /**
     * calculateIndexOrgSubdomain
     * @throws Exception 
     */
    public void calculateIndexOrgSubdomain() throws Exception {
        ArrayList subdomainIdList;
        ArrayList orgIdList;
        ArrayList publicationList;
        SubdomainMapper subdomainMapper = new SubdomainMapper();
        OrgMapper orgMapper = new OrgMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            subdomainIdList = subdomainMapper.getSubdomainIdList();
            orgIdList = orgMapper.getOrgIdList();
            // For each orgId.
            for (Object o : orgIdList) {
                for (Object o2 : subdomainIdList) {
                    int idOrg = Integer.parseInt(o.toString());
                    int idSubdomain = Integer.parseInt(o2.toString());
                    publicationList = orgMapper.getPublicationDTOListByOrgIdSubdomainId(idOrg, idSubdomain);
                    // Sort publicationList of author by citationCount.
                    Collections.sort(publicationList, new Comparator<PaperDTO>() {

                        public int compare(PaperDTO one, PaperDTO other) {
                            int result = 0;
                            try {
                                if (one.getCitationCount() > other.getCitationCount()) {
                                    result = -1;
                                } else if (one.getCitationCount() < other.getCitationCount()) {
                                    result = 1;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                                Object[] arrObj = ex.getStackTrace();
                                if (arrObj != null) {
                                    for (Object stackTraceElement : arrObj) {
                                        GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                    }
                                }
                            }
                            return result;
                        }
                    });
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
                        orgMapper.saveIndexesOrgSubdomain(idOrg, idSubdomain, h_index, g_index);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            orgMapper.closeConnection();
            subdomainMapper.closeConnection();
        }
    }

    /**
     * calculateIndexOrgKeyword
     * @throws Exception 
     */
    public void calculateIndexOrgKeyword() throws Exception {
        ArrayList keywordIdList;
        ArrayList orgIdList;
        ArrayList publicationList;
        KeywordMapper keywordMapper = new KeywordMapper();
        OrgMapper orgMapper = new OrgMapper();
        int h_index;
        int g_index;
        int citationCount;
        int citationCountSum;

        try {
            keywordIdList = keywordMapper.getKeywordIdList();
            orgIdList = orgMapper.getOrgIdList();
            // For each orgId.
            for (Object o : orgIdList) {
                for (Object o2 : keywordIdList) {
                    int idOrg = Integer.parseInt(o.toString());
                    int idKeyword = Integer.parseInt(o2.toString());
                    publicationList = orgMapper.getPublicationDTOListByOrgIdKeywordId(idOrg, idKeyword);
                    // Sort publicationList of author by citationCount.
                    Collections.sort(publicationList, new Comparator<PaperDTO>() {

                        public int compare(PaperDTO one, PaperDTO other) {
                            int result = 0;
                            try {
                                if (one.getCitationCount() > other.getCitationCount()) {
                                    result = -1;
                                } else if (one.getCitationCount() < other.getCitationCount()) {
                                    result = 1;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                                Object[] arrObj = ex.getStackTrace();
                                if (arrObj != null) {
                                    for (Object stackTraceElement : arrObj) {
                                        GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                    }
                                }
                            }
                            return result;
                        }
                    });
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
                        orgMapper.saveIndexesOrgKeyword(idOrg, idKeyword, h_index, g_index);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            orgMapper.closeConnection();
            keywordMapper.closeConnection();
        }
    }

    /**
     * calculateIndexJournal
     * @throws Exception 
     */
    public void calculateIndexJournal() throws Exception {
        ArrayList journalIdList;
        ArrayList publicationList;
        JournalMapper journalMapper = new JournalMapper();
        PaperMapper paperMapper = new PaperMapper();
        int g_index;
        int citationCountSum;

        try {
            journalIdList = journalMapper.getJournalIdList();
            // For each journalId.
            for (Object o : journalIdList) {
                int idJournal = Integer.parseInt(o.toString());
                publicationList = paperMapper.getPublicationDTOListByJournalId(idJournal);
                // Sort publicationList of journal by citationCount.
                Collections.sort(publicationList, new Comparator<PaperDTO>() {

                    public int compare(PaperDTO one, PaperDTO other) {
                        int result = 0;
                        try {
                            if (one.getCitationCount() > other.getCitationCount()) {
                                result = -1;
                            } else if (one.getCitationCount() < other.getCitationCount()) {
                                result = 1;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                            Object[] arrObj = ex.getStackTrace();
                            if (arrObj != null) {
                                for (Object stackTraceElement : arrObj) {
                                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                }
                            }
                        }
                        return result;
                    }
                });
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
                    journalMapper.saveIndexes(idJournal, g_index);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            journalMapper.closeConnection();
            paperMapper.closeConnection();
        }
    }

    /**
     * calculateIndexJournalSubdomain
     * @throws Exception 
     */
    public void calculateIndexJournalSubdomain() throws Exception {
        ArrayList subdomainIdList;
        ArrayList journalIdList;
        ArrayList publicationList;
        SubdomainMapper subdomainMapper = new SubdomainMapper();
        JournalMapper journalMapper = new JournalMapper();
        PaperMapper paperMapper = new PaperMapper();
        int g_index;
        int citationCountSum;

        try {
            subdomainIdList = subdomainMapper.getSubdomainIdList();
            journalIdList = journalMapper.getJournalIdList();
            // For each journalId.
            for (Object o : journalIdList) {
                for (Object o2 : subdomainIdList) {
                    int idJournal = Integer.parseInt(o.toString());
                    int idSubdomain = Integer.parseInt(o2.toString());
                    publicationList = paperMapper.getPublicationDTOListByJournalIdSubdomainId(idJournal, idSubdomain);
                    // Sort publicationList of journal by citationCount.
                    Collections.sort(publicationList, new Comparator<PaperDTO>() {

                        public int compare(PaperDTO one, PaperDTO other) {
                            int result = 0;
                            try {
                                if (one.getCitationCount() > other.getCitationCount()) {
                                    result = -1;
                                } else if (one.getCitationCount() < other.getCitationCount()) {
                                    result = 1;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                                Object[] arrObj = ex.getStackTrace();
                                if (arrObj != null) {
                                    for (Object stackTraceElement : arrObj) {
                                        GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                    }
                                }
                            }
                            return result;
                        }
                    });
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
                        journalMapper.saveIndexesJournalSubdomain(idJournal, idSubdomain, g_index);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            journalMapper.closeConnection();
            paperMapper.closeConnection();
            subdomainMapper.closeConnection();
        }
    }

    /**
     * calculateIndexJournalKeyword
     * @throws Exception 
     */
    public void calculateIndexJournalKeyword() throws Exception {
        ArrayList keywordIdList;
        ArrayList journalIdList;
        ArrayList publicationList;
        KeywordMapper keywordMapper = new KeywordMapper();
        JournalMapper journalMapper = new JournalMapper();
        PaperMapper paperMapper = new PaperMapper();
        int g_index;
        int citationCountSum;

        try {
            keywordIdList = keywordMapper.getKeywordIdList();
            journalIdList = journalMapper.getJournalIdList();
            // For each journalId.
            for (Object o : journalIdList) {
                for (Object o2 : keywordIdList) {
                    int idJournal = Integer.parseInt(o.toString());
                    int idKeyword = Integer.parseInt(o2.toString());
                    publicationList = paperMapper.getPublicationDTOListByJournalIdKeywordId(idJournal, idKeyword);
                    // Sort publicationList of journal by citationCount.
                    Collections.sort(publicationList, new Comparator<PaperDTO>() {

                        public int compare(PaperDTO one, PaperDTO other) {
                            int result = 0;
                            try {
                                if (one.getCitationCount() > other.getCitationCount()) {
                                    result = -1;
                                } else if (one.getCitationCount() < other.getCitationCount()) {
                                    result = 1;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                                Object[] arrObj = ex.getStackTrace();
                                if (arrObj != null) {
                                    for (Object stackTraceElement : arrObj) {
                                        GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                    }
                                }
                            }
                            return result;
                        }
                    });
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
                        journalMapper.saveIndexesJournalKeyword(idJournal, idKeyword, g_index);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            journalMapper.closeConnection();
            paperMapper.closeConnection();
            keywordMapper.closeConnection();
        }
    }

    /**
     * calculateIndexConference
     * @throws Exception 
     */
    public void calculateIndexConference() throws Exception {
        ArrayList conferenceIdList;
        ArrayList publicationList;
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        PaperMapper paperMapper = new PaperMapper();
        int g_index;
        int citationCountSum;

        try {
            conferenceIdList = conferenceMapper.getConferenceIdList();
            // For each ConferenceId.
            for (Object o : conferenceIdList) {
                int idConference = Integer.parseInt(o.toString());
                publicationList = paperMapper.getPublicationDTOListByConferenceId(idConference);
                // Sort publicationList of conference by citationCount.
                Collections.sort(publicationList, new Comparator<PaperDTO>() {

                    public int compare(PaperDTO one, PaperDTO other) {
                        int result = 0;
                        try {
                            if (one.getCitationCount() > other.getCitationCount()) {
                                result = -1;
                            } else if (one.getCitationCount() < other.getCitationCount()) {
                                result = 1;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                            Object[] arrObj = ex.getStackTrace();
                            if (arrObj != null) {
                                for (Object stackTraceElement : arrObj) {
                                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                }
                            }
                        }
                        return result;
                    }
                });
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
                    conferenceMapper.saveIndexes(idConference, g_index);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            conferenceMapper.closeConnection();
            paperMapper.closeConnection();
        }
    }

    /**
     * calculateIndexConference
     * @throws Exception 
     */
    public void calculateIndexConferenceSubdomain() throws Exception {
        ArrayList subdomainIdList;
        ArrayList conferenceIdList;
        ArrayList publicationList;
        SubdomainMapper subdomainMapper = new SubdomainMapper();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        PaperMapper paperMapper = new PaperMapper();
        int g_index;
        int citationCountSum;

        try {
            subdomainIdList = subdomainMapper.getSubdomainIdList();
            conferenceIdList = conferenceMapper.getConferenceIdList();
            // For each ConferenceId.
            for (Object o : conferenceIdList) {
                for (Object o2 : subdomainIdList) {
                    int idConference = Integer.parseInt(o.toString());
                    int idSubdomain = Integer.parseInt(o2.toString());
                    publicationList = paperMapper.getPublicationDTOListByConferenceIdSubdomainId(idConference, idSubdomain);
                    // Sort publicationList of conference by citationCount.
                    Collections.sort(publicationList, new Comparator<PaperDTO>() {

                        public int compare(PaperDTO one, PaperDTO other) {
                            int result = 0;
                            try {
                                if (one.getCitationCount() > other.getCitationCount()) {
                                    result = -1;
                                } else if (one.getCitationCount() < other.getCitationCount()) {
                                    result = 1;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                                Object[] arrObj = ex.getStackTrace();
                                if (arrObj != null) {
                                    for (Object stackTraceElement : arrObj) {
                                        GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                    }
                                }
                            }
                            return result;
                        }
                    });
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
                        conferenceMapper.saveIndexesConferenceSubdomain(idConference, idSubdomain, g_index);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            conferenceMapper.closeConnection();
            paperMapper.closeConnection();
            subdomainMapper.closeConnection();
        }
    }

    /**
     * calculateIndexConference
     * @throws Exception 
     */
    public void calculateIndexConferenceKeyword() throws Exception {
        ArrayList keywordIdList;
        ArrayList conferenceIdList;
        ArrayList publicationList;
        KeywordMapper keywordMapper = new KeywordMapper();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        PaperMapper paperMapper = new PaperMapper();
        int g_index;
        int citationCountSum;

        try {
            keywordIdList = keywordMapper.getKeywordIdList();
            conferenceIdList = conferenceMapper.getConferenceIdList();
            // For each ConferenceId.
            for (Object o : conferenceIdList) {
                for (Object o2 : keywordIdList) {
                    int idConference = Integer.parseInt(o.toString());
                    int idKeyword = Integer.parseInt(o2.toString());
                    publicationList = paperMapper.getPublicationDTOListByConferenceIdKeywordId(idConference, idKeyword);
                    // Sort publicationList of conference by citationCount.
                    Collections.sort(publicationList, new Comparator<PaperDTO>() {

                        public int compare(PaperDTO one, PaperDTO other) {
                            int result = 0;
                            try {
                                if (one.getCitationCount() > other.getCitationCount()) {
                                    result = -1;
                                } else if (one.getCitationCount() < other.getCitationCount()) {
                                    result = 1;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                                Object[] arrObj = ex.getStackTrace();
                                if (arrObj != null) {
                                    for (Object stackTraceElement : arrObj) {
                                        GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                                    }
                                }
                            }
                            return result;
                        }
                    });
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
                        conferenceMapper.saveIndexesConferenceKeyword(idConference, idKeyword, g_index);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
            throw ex;
        } finally {
            conferenceMapper.closeConnection();
            paperMapper.closeConnection();
            keywordMapper.closeConnection();
        }
    }
}
