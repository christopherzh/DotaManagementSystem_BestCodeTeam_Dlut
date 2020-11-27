package sql;

import util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql {
    // Create a database connection object, and the whole project connect with mySQL through this class
    //Get the connection of programme and mysql
    public Connection connection = new DbUtil().getCon();
    //also we need to close the data base
    public void closeConnection(){
        try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
