package me.phatlee.demotuan3.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnectMySQL {
    private static Connection con = null;
    private static String USERNAME = "root";
    private static String PASSWORD = "1492004";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/demoTuan3";

    public static Connection getDatabaseConnection(){
        try {
            Class.forName(DRIVER);
            return con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static String checkConnection(){
        Connection con = DBconnectMySQL.getDatabaseConnection();
        if(con != null){
            return "Kết nối thành công!";
        } else {
            return "Kết nối thất bại!";
        }
    }

    public static void main(String[] args) {
        try(Connection con = DBconnectMySQL.getDatabaseConnection()){
            if(con != null){
                System.out.println("Kết nối thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
