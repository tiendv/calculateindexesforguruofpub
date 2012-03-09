/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.pubguru.utility;

import java.util.Comparator;
import uit.pubguru.dto.PaperDTO;

/**
 *
 * @author THNghiep
 * Note: This class is deprecated because its task is replaced by sorting in sql query.
 */
public class PublicationCitationComparator implements Comparator<PaperDTO> {

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
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null) {
                for (Object stackTraceElement : arrObj) {
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
                }
            }
        }
        return result;
    }
}
