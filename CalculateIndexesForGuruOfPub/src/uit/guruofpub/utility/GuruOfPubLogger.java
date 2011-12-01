/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.guruofpub.utility;

import java.util.logging.*;
import uit.guruofpub.constant.GuruOfPubConst;

/**
 *
 * @author THNghiep
 */
public class GuruOfPubLogger {
 
    public static Logger logger;
    public static Handler logHandler;
    static {
        try {
          boolean append = true;
          logHandler = new FileHandler("C:\\CalculateIndexesForGuruOfPubLog\\CalculateIndexesForGuruOfPubLog.log", 10000000, 1000, append);
          logHandler.setLevel(Level.ALL);
          logHandler.setFormatter(new Formatter() {
             public String format(LogRecord logRecord) {
                StringBuffer strBuffer = new StringBuffer(10000);
                strBuffer.append("[ ");
                strBuffer.append(new java.util.Date());
                strBuffer.append(" ] - ");
                strBuffer.append(logRecord.getLevel());
                strBuffer.append(": ");
                strBuffer.append(formatMessage(logRecord));
                strBuffer.append('\n');
                return strBuffer.toString();
                }
              });
          logger = Logger.getLogger("CalculateIndexesForGuruOfPubLog");
          logger.setUseParentHandlers(false);
          logger.addHandler(logHandler);
          // Change LOGGING_LEVEL in Constant to WARNING in order to disable logging on normal info, only log important info.
          logger.setLevel(GuruOfPubConst.LOGGING_LEVEL);
        }
        catch (Exception ex) {
            System.out.println("Create folder [C:\\CalculateIndexesForGuruOfPubLog\\] if not existing.");
          ex.printStackTrace();
        }
    }   
    
}
