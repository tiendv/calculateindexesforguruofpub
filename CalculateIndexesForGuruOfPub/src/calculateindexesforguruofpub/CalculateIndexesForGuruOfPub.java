/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculateindexesforguruofpub;

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
        System.out.println("Start Running");
        try {
            CalculateIndex calculateIndex = new CalculateIndex();
            System.out.println("Start calculating index for author.");
            calculateIndex.calculateIndexAuthor();
            System.out.println("End calculating index for author.");
            System.out.println("Start calculating index for org.");
            calculateIndex.calculateIndexOrg();
            System.out.println("End calculating index for org.");
        } catch (Exception ex) {
            ex.printStackTrace();
            GuruOfPubLogger.logger.severe("EXCEPTION: " + ex.toString());
            Object[] arrObj = ex.getStackTrace();
            if (arrObj != null)
                for (Object stackTraceElement : arrObj)
                    GuruOfPubLogger.logger.severe("\tat " + stackTraceElement.toString());
        }
        System.out.println("End Running");
    }
}
