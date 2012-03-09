/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.pubguru.utility;

import java.util.logging.*;
import uit.pubguru.constant.PubGuruConst;

/**
 *
 * @author THNghiep
 */
public class PubGuruLogger {
 
    public static Logger logger;
    public static Handler logHandler;
    static {
        try {
          boolean append = true;
          logHandler = new FileHandler("C:\\CalculateIndexesForPubGuruLog\\CalculateIndexesForPubGuruLog.log", 10000000, 1000, append);
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
          logger = Logger.getLogger("CalculateIndexesForPubGuruLog");
          logger.setUseParentHandlers(false);
          logger.addHandler(logHandler);
          // Change LOGGING_LEVEL in Constant to WARNING in order to disable logging on normal info, only log important info.
          logger.setLevel(PubGuruConst.LOGGING_LEVEL);
        }
        catch (Exception ex) {
            System.out.println("Create folder [C:\\CalculateIndexesForPubGuruLog\\] if not existing.");
          ex.printStackTrace();
        }
    }   
    
}
