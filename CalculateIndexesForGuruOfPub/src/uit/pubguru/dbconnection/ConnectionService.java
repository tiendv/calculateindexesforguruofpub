/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.pubguru.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import uit.pubguru.constant.PubGuruConst;
import uit.pubguru.utility.PubGuruLogger;

public class ConnectionService {

    /**
     * loadJDBCDriver
     * @throws Exception 
     */
    protected static void loadJDBCDriver() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            throw new Exception("SQL JDBC Driver not found ...");
        }
    }

    /**
     * getConnection
     * @return
     * @throws Exception 
     */
    public static Connection getConnection() throws Exception {
        DataSource dataSource = null;
        Connection connection = null;
        try {
            if (PubGuruConst.DB.compareTo("DS") == 0) {
                dataSource = (DataSource) new InitialContext().lookup(PubGuruConst.JNDI_NAME);
                connection = dataSource.getConnection();
            } else if (PubGuruConst.DB.compareTo("MYSQL") == 0) {
                connection = getConnectionMySQL();
            } else if (PubGuruConst.DB.compareTo("MSSQLSERVER") == 0) {
                connection = getConnectionMSSQLServer();
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
            throw ex;
        }
        return connection;
    }

    /**
     * getConnectionMySQL
     * @return
     * @throws Exception 
     */
    public static Connection getConnectionMySQL() throws Exception {
        Connection connect = null;
        if (connect == null) {
            loadJDBCDriver();
            String url = "jdbc:mysql://" + PubGuruConst.HOST
                    + ":" + PubGuruConst.PORT
                    + "/" + PubGuruConst.DATABASE
                    + "?user=" + PubGuruConst.USERNAME
                    + "&password=" + PubGuruConst.PASSWORD
                    + "&autoReconnect=true"
                    + "&connectTimeout=300"
                    + "&useBlobToStoreUTF8OutsideBMP=true";

            try {
                connect = DriverManager.getConnection(url);
            } catch (java.sql.SQLException e) {
                throw new Exception("Can not access to Database Server ..." + url + e.getMessage());
            }
        }
        return connect;
    }

    /**
     * getConnectionMSSQLServer
     * @return
     * @throws Exception 
     */
    public static Connection getConnectionMSSQLServer() throws Exception {
        Connection connect = null;
        if (connect == null) {
            String url = "jdbc:sqlserver://" + PubGuruConst.HOSTMSSQLSERVER
                    + ":" + PubGuruConst.PORTMSSQLSERVER
                    + ";databaseName=" + PubGuruConst.DATABASEMSSQLSERVER
                    + ";user=" + PubGuruConst.USERNAMEMSSQLSERVER
                    + ";password=" + PubGuruConst.PASSWORDMSSQLSERVER
                    + ";loginTimeout=300";
            try {
                connect = DriverManager.getConnection(url);
            } catch (java.sql.SQLException e) {
                throw new Exception("Can not access to Database Server ..." + url + e.getMessage());
            } catch (Exception ex) {
                throw new Exception();
            }

        }
        return connect;
    }
}
