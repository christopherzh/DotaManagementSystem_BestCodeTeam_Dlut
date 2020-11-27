package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    //Establish a connection to the database, and sql.Sql is to get the connect established by DbUtil
    private final String dbUrl = "";// need to complete it later
    private final String dbUserName = "root";// user name
    private final String dbPassword = "rootsbliutianhao";// the password
    private final String jdbcName = "com.mysql.jdbc.Driver";// Driver name

    //get connection
    public Connection getCon() {
        try {
            Class.forName(jdbcName);
        } catch (ClassNotFoundException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (SQLException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;

    }
}
