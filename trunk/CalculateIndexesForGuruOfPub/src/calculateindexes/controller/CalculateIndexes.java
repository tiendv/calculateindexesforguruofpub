/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculateindexes.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import uit.pubguru.utility.CalculateIndex;
import uit.pubguru.utility.PubGuruLogger;
/**
 *
 * @author THNghiep
 */
public class CalculateIndexes {

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
        PubGuruLogger.logger.warning("**********************************************************");
        PubGuruLogger.logger.warning("BEGIN CALCULATION");
        PubGuruLogger.logger.warning("**********************************************************");
        PubGuruLogger.logger.warning(dateFormat.format(cal.getTime()));

        try {
            CalculateIndex calculateIndex = new CalculateIndex();
// Basic.
            System.out.println("Begin calculation index for author.");
            PubGuruLogger.logger.warning("Begin calculation index for author.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexAuthor();
            System.out.println("End calculation index for author.");
            PubGuruLogger.logger.warning("End calculation index for author.");
            
            System.out.println("Begin calculation index for org.");
            PubGuruLogger.logger.warning("Begin calculation index for org.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexOrg();
            System.out.println("End calculation index for org.");
            PubGuruLogger.logger.warning("End calculation index for org.");

            System.out.println("Begin calculation index for journal.");
            PubGuruLogger.logger.warning("Begin calculation index for journal.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexJournal();
            System.out.println("End calculation index for journal.");
            PubGuruLogger.logger.warning("End calculation index for journal.");

            System.out.println("Begin calculation index for conference.");
            PubGuruLogger.logger.warning("Begin calculation index for conference.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexConference();
            System.out.println("End calculation index for conference.");
            PubGuruLogger.logger.warning("End calculation index for conference.");
// In Subdomain.
            System.out.println("Begin calculation index for author in subdomain.");
            PubGuruLogger.logger.warning("Begin calculation index for author in subdomain.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexAuthorSubdomain();
            System.out.println("End calculation index for author in subdomain.");
            PubGuruLogger.logger.warning("End calculation index for author in subdomain.");

            System.out.println("Begin calculation index for org in subdomain.");
            PubGuruLogger.logger.warning("Begin calculation index for org in subdomain.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexOrgSubdomain();
            System.out.println("End calculation index for org in subdomain.");
            PubGuruLogger.logger.warning("End calculation index for org in subdomain.");

            System.out.println("Begin calculation index for journal in subdomain.");
            PubGuruLogger.logger.warning("Begin calculation index for journal in subdomain.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexJournalSubdomain();
            System.out.println("End calculation index for journal in subdomain.");
            PubGuruLogger.logger.warning("End calculation index for journal in subdomain.");

            System.out.println("Begin calculation index for conference in subdomain.");
            PubGuruLogger.logger.warning("Begin calculation index for conference in subdomain.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexConferenceSubdomain();
            System.out.println("End calculation index for conference in subdomain.");
            PubGuruLogger.logger.warning("End calculation index for conference in subdomain.");
// In Keyword.
            System.out.println("Begin calculation index for author in keyword.");
            PubGuruLogger.logger.warning("Begin calculation index for author in keyword.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexAuthorKeyword();
            System.out.println("End calculation index for author in keyword.");
            PubGuruLogger.logger.warning("End calculation index for author in keyword.");

            System.out.println("Begin calculation index for org in keyword.");
            PubGuruLogger.logger.warning("Begin calculation index for org in keyword.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexOrgKeyword();
            System.out.println("End calculation index for org in keyword.");
            PubGuruLogger.logger.warning("End calculation index for org in keyword.");

            System.out.println("Begin calculation index for journal in keyword.");
            PubGuruLogger.logger.warning("Begin calculation index for journal in keyword.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexJournalKeyword();
            System.out.println("End calculation index for journal in keyword.");
            PubGuruLogger.logger.warning("End calculation index for journal in keyword.");

            System.out.println("Begin calculation index for conference in keyword.");
            PubGuruLogger.logger.warning("Begin calculation index for conference in keyword.");
            System.out.println("Caculating...");
            PubGuruLogger.logger.warning("Caculating...");
            calculateIndex.calculateIndexConferenceKeyword();
            System.out.println("End calculation index for conference in keyword.");
            PubGuruLogger.logger.warning("End calculation index for conference in keyword.");

        } catch (Exception ex) {
            ex.printStackTrace();
            PubGuruLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    PubGuruLogger.logger.severe("\tat " + stackTraceElement.toString());
        }

        cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        System.out.println("**********************************************************");
        System.out.println("END CALCULATION");
        System.out.println("**********************************************************");
        PubGuruLogger.logger.warning(dateFormat.format(cal.getTime()));
        PubGuruLogger.logger.warning("**********************************************************");
        PubGuruLogger.logger.warning("END CALCULATION");
        PubGuruLogger.logger.warning("**********************************************************");
    }
}
