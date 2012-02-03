package uit.guruofpub.constant;
import java.util.logging.Level;

public class GuruOfPubConst {
        public static final String DB = "MYSQL";
        //public static final String DB = "SQLSERVER";
        
	public static final String HOST = "localhost";
	public static final String PORT = "3307";
	public static final String DATABASE = "guruofpub";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "hntin1975";
        
	public static final String HOSTMSSQLSERVER = "localhost";
	public static final String PORTMSSQLSERVER = "1433";
	public static final String DATABASEMSSQLSERVER = "CSPublicationCrawler";
	public static final String USERNAMEMSSQLSERVER = "sa";
	public static final String PASSWORDMSSQLSERVER = "12345";        
        
        public static final int MAX_RETRY_TIMES = 1;
        public static final int TIME_SLEEP_AFTER_EXCEPTION = 60000;
        public static final int TIME_SLEEP = 750;
        //public static final Level LOGGING_LEVEL = Level.ALL;
        public static final Level LOGGING_LEVEL = Level.WARNING;
        
        public static final int ITEM_LIST_SIZE = 100; //MAS supports max size 100
}
