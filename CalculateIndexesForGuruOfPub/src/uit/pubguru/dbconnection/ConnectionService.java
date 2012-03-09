/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uit.pubguru.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

import uit.pubguru.constant.GuruOfPubConst;

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
        if (GuruOfPubConst.DB.compareTo("MYSQL") == 0) {
            return getConnectionMySQL();
        }
        else {
            return getConnectionMSSQLServer();
        }
        
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
            String url = "jdbc:mysql://" + GuruOfPubConst.HOST
                    + ":" + GuruOfPubConst.PORT
                    + "/" + GuruOfPubConst.DATABASE
                    + "?user=" + GuruOfPubConst.USERNAME
                    + "&password=" + GuruOfPubConst.PASSWORD
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
            String url = "jdbc:sqlserver://" + GuruOfPubConst.HOSTMSSQLSERVER
                    + ":" + GuruOfPubConst.PORTMSSQLSERVER
                    + ";databaseName=" + GuruOfPubConst.DATABASEMSSQLSERVER
                    + ";user=" + GuruOfPubConst.USERNAMEMSSQLSERVER
                    + ";password=" + GuruOfPubConst.PASSWORDMSSQLSERVER
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
