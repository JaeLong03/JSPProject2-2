package org.example.project22.common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static Connection con = null;

    public static Connection getConnection(){
        try {
            if(con == null || con.isClosed()) {
                Class.forName("org.mariadb.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mariadb://walab.handong.edu:3306/W25_22400583", "W25_22400583", "zei1Ch");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    public static void main(String[] args){
        Connection con = JDBCUtil.getConnection();
        if(con != null) {
            System.out.println("Hello");
        }
    }

}
