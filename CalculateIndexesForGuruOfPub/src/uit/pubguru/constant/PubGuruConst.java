package uit.pubguru.constant;

import java.util.logging.Level;

public class PubGuruConst {

    // Choose DB Server to use:
    public static final String DB = "DS"; // Use Data source to provide connection, configuring in Glassfish.
    //public static final String DB = "MYSQL";
    //public static final String DB = "MSSQLSERVER";
    
    //JDBC Resources :
    public static final String JNDI_NAME = "jdbc/IndexesCalculatorDS";//"jdbc/PubGuruDS";
    
    public static final String HOST = "localhost";
    public static final String PORT = "3307";//"3306";
    public static final String DATABASE = "GuruOfPub";//"CSPublicationCrawler";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "hntin1975";//"root";
    
    public static final String HOSTMSSQLSERVER = "localhost";
    public static final String PORTMSSQLSERVER = "1433";
    public static final String DATABASEMSSQLSERVER = "CSPublicationCrawler";
    public static final String USERNAMEMSSQLSERVER = "sa";
    public static final String PASSWORDMSSQLSERVER = "12345";
    
    public static final int MAX_RETRY_TIMES = 1;
    public static final int TIME_SLEEP_AFTER_EXCEPTION = 60000;
    public static final int TIME_SLEEP = 750;
    public static final int ITEM_LIST_SIZE = 100; //MAS supports max size 100
    
    //public static final Level LOGGING_LEVEL = Level.ALL;
    public static final Level LOGGING_LEVEL = Level.WARNING;
    
    public static final int LIMIT_BULK_LOAD_ID = 100000;
}
