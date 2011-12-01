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
import uit.guruofpub.dbaccess.OrgMapper;
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
                        if (one.getCitationCount() > other.getCitationCount()) 
                            result = -1;
                        else if (one.getCitationCount() < other.getCitationCount()) 
                            result = 1;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                        Object[] arrObj = ex.getStackTrace();
                        if (arrObj != null)
                            for (Object stackTraceElement : arrObj)
                                GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                    }
                    return result;
                }
            });
            // Calculate h-index for each author.
            h_index = 0;
            while (h_index < publicationList.size()) {
                citationCount = ((PaperDTO)publicationList.get(h_index)).getCitationCount();
                if (citationCount >= (h_index + 1))
                    h_index++;
                else break;
            }
            // Calculate g-index for each author.
            g_index = 0;
            citationCountSum = 0;
            while (true) {
                if (g_index < publicationList.size()) {
                    citationCountSum += ((PaperDTO)publicationList.get(g_index)).getCitationCount();
                }
                if (citationCountSum >= (g_index + 1) * (g_index + 1)) 
                    g_index++;
                else break;
            }
            // Save indexes to DB.
            authorMapper.saveIndexes(idAuthor, h_index, g_index);
        }
    }
    
    /**
     * calculateIndexOrg
     * @throws Exception 
     */
    public void calculateIndexOrg() throws Exception {
        ArrayList orgIdList;
        ArrayList authorList;
        ArrayList publicationList;
        OrgMapper orgMapper = new OrgMapper();
        int h_index;
        int g_index;
        int authorH_index;
        int citationCountSum;
        
        orgIdList = orgMapper.getOrgIdList();
        // For each orgId.
        for (Object o : orgIdList) {
            int idOrg = Integer.parseInt(o.toString());
            authorList = orgMapper.getAuthorDTOListByOrgId(idOrg);
            // Sort authorList of org by h-index.
            Collections.sort(authorList, new Comparator<AuthorDTO>() {
                public int compare(AuthorDTO one, AuthorDTO other) {
                    int result = 0;
                    try {
                        if (one.getH_index() > other.getH_index())
                            result = -1;
                        else if (one.getH_index() < other.getH_index())
                            result = 1;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                        Object[] arrObj = ex.getStackTrace();
                        if (arrObj != null)
                            for (Object stackTraceElement : arrObj)
                                GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                    }
                    return result;
                }
            });
            // Calculate h-index for each org.
            h_index = 0;
            while (h_index < authorList.size()) {
                authorH_index = ((AuthorDTO)authorList.get(h_index)).getH_index();
                if (authorH_index >= (h_index + 1))
                    h_index++;
                else break;
            }
            publicationList = orgMapper.getPublicationDTOListByOrgId(idOrg);
            // Sort publicationList of author by citationCount.
            Collections.sort(publicationList, new Comparator<PaperDTO>() {
                public int compare(PaperDTO one, PaperDTO other) {
                    int result = 0;
                    try {
                        if (one.getCitationCount() > other.getCitationCount()) 
                            result = -1;
                        else if (one.getCitationCount() < other.getCitationCount()) 
                            result = 1;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
                        Object[] arrObj = ex.getStackTrace();
                        if (arrObj != null)
                            for (Object stackTraceElement : arrObj)
                                GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
                    }
                    return result;
                }
            });
            // Calculate g-index for each author.
            g_index = 0;
            citationCountSum = 0;
            while (true) {
                if (g_index < publicationList.size()) {
                    citationCountSum += ((PaperDTO)publicationList.get(g_index)).getCitationCount();
                }
                if (citationCountSum >= (g_index + 1) * (g_index + 1)) 
                    g_index++;
                else break;
            }
            // Save indexes to DB.
            orgMapper.saveIndexes(idOrg, h_index, g_index);
        }
    }
}
