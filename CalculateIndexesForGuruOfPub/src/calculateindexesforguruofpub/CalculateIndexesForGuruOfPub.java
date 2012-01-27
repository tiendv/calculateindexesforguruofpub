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
        System.out.println("BEGIN CALCULATION");
        System.out.println("**********************************************************");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        GuruOfPubLogger.logger.warning("**********************************************************");
        GuruOfPubLogger.logger.warning("BEGIN CALCULATION");
        GuruOfPubLogger.logger.warning("**********************************************************");
        GuruOfPubLogger.logger.warning(dateFormat.format(cal.getTime()));

        try {
            CalculateIndex calculateIndex = new CalculateIndex();

            System.out.println("Begin calculation index for author.");
            GuruOfPubLogger.logger.warning("Begin calculation index for author.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexAuthor();
            System.out.println("End calculation index for author.");
            GuruOfPubLogger.logger.warning("End calculation index for author.");
            
            System.out.println("Begin calculation index for org.");
            GuruOfPubLogger.logger.warning("Begin calculation index for org.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexOrg();
            System.out.println("End calculation index for org.");
            GuruOfPubLogger.logger.warning("End calculation index for org.");

            System.out.println("Begin calculation index for journal.");
            GuruOfPubLogger.logger.warning("Begin calculation index for journal.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexJournal();
            System.out.println("End calculation index for journal.");
            GuruOfPubLogger.logger.warning("End calculation index for journal.");

            System.out.println("Begin calculation index for conference.");
            GuruOfPubLogger.logger.warning("Begin calculation index for conference.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexConference();
            System.out.println("End calculation index for conference.");
            GuruOfPubLogger.logger.warning("End calculation index for conference.");

            System.out.println("Begin calculation index for author in subdomain.");
            GuruOfPubLogger.logger.warning("Begin calculation index for author in subdomain.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexAuthorSubdomain();
            System.out.println("End calculation index for author in subdomain.");
            GuruOfPubLogger.logger.warning("End calculation index for author in subdomain.");

            System.out.println("Begin calculation index for org in subdomain.");
            GuruOfPubLogger.logger.warning("Begin calculation index for org in subdomain.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexOrgSubdomain();
            System.out.println("End calculation index for org in subdomain.");
            GuruOfPubLogger.logger.warning("End calculation index for org in subdomain.");

            System.out.println("Begin calculation index for journal in subdomain.");
            GuruOfPubLogger.logger.warning("Begin calculation index for journal in subdomain.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexJournalSubdomain();
            System.out.println("End calculation index for journal in subdomain.");
            GuruOfPubLogger.logger.warning("End calculation index for journal in subdomain.");

            System.out.println("Begin calculation index for conference in subdomain.");
            GuruOfPubLogger.logger.warning("Begin calculation index for conference in subdomain.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexConferenceSubdomain();
            System.out.println("End calculation index for conference in subdomain.");
            GuruOfPubLogger.logger.warning("End calculation index for conference in subdomain.");

            System.out.println("Begin calculation index for author in keyword.");
            GuruOfPubLogger.logger.warning("Begin calculation index for author in keyword.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexAuthorKeyword();
            System.out.println("End calculation index for author in keyword.");
            GuruOfPubLogger.logger.warning("End calculation index for author in keyword.");

            System.out.println("Begin calculation index for org in keyword.");
            GuruOfPubLogger.logger.warning("Begin calculation index for org in keyword.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexOrgKeyword();
            System.out.println("End calculation index for org in keyword.");
            GuruOfPubLogger.logger.warning("End calculation index for org in keyword.");

            System.out.println("Begin calculation index for journal in keyword.");
            GuruOfPubLogger.logger.warning("Begin calculation index for journal in keyword.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexJournalKeyword();
            System.out.println("End calculation index for journal in keyword.");
            GuruOfPubLogger.logger.warning("End calculation index for journal in keyword.");

            System.out.println("Begin calculation index for conference in keyword.");
            GuruOfPubLogger.logger.warning("Begin calculation index for conference in keyword.");
            System.out.println("Caculating...");
            GuruOfPubLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexConferenceKeyword();
            System.out.println("End calculation index for conference in keyword.");
            GuruOfPubLogger.logger.warning("End calculation index for conference in keyword.");

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
        System.out.println("END CALCULATION");
        System.out.println("**********************************************************");
        GuruOfPubLogger.logger.warning(dateFormat.format(cal.getTime()));
        GuruOfPubLogger.logger.warning("**********************************************************");
        GuruOfPubLogger.logger.warning("END CALCULATION");
        GuruOfPubLogger.logger.warning("**********************************************************");
    }
}
