/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculateindexesforguruofpub;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import uit.guruofpub.utility.CalculateIndex;
import uit.guruofpub.utility.GuruOfPubLogger;
/**
 *
 * @author THNghiep
 */
public class CalculateIndexesForGuruOfPub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("BEGIN CALCULATING");
        System.out.println("**********************************************************");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        GuruOfPubLogger.logger.warning("**********************************************************");
        GuruOfPubLogger.logger.warning("BEGIN CALCULATING");
        GuruOfPubLogger.logger.warning("**********************************************************");
        GuruOfPubLogger.logger.warning(dateFormat.format(cal.getTime()));

        try {
            CalculateIndex calculateIndex = new CalculateIndex();

            System.out.println("Begin calculating index for author.");
            GuruOfPubLogger.logger.warning("Begin calculating index for author.");
            calculateIndex.calculateIndexAuthor();
            System.out.println("End calculating index for author.");
            GuruOfPubLogger.logger.warning("End calculating index for author.");
            
            System.out.println("Begin calculating index for org.");
            GuruOfPubLogger.logger.warning("Begin calculating index for org.");
            calculateIndex.calculateIndexOrg();
            System.out.println("End calculating index for org.");
            GuruOfPubLogger.logger.warning("End calculating index for org.");
            
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
        }

        cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        System.out.println("**********************************************************");
        System.out.println("END CALCULATING");
        System.out.println("**********************************************************");
        GuruOfPubLogger.logger.warning(dateFormat.format(cal.getTime()));
        GuruOfPubLogger.logger.warning("**********************************************************");
        GuruOfPubLogger.logger.warning("END CALCULATING");
        GuruOfPubLogger.logger.warning("**********************************************************");
    }
}
