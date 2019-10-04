package app;

import java.sql.*;

public class InitDBConnection {

    public Connection initConnectionDB() {
        Connection conn = null;
        try {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/food";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "root", "");
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return conn;
    }

}
