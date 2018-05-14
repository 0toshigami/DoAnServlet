/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author NoName
 */
public class DataProvider {
    public static Connection Connect() {
         try {
            String driver = "com.mysql.jdbc.Driver";            
            String url = "jdbc:mysql://127.0.0.1:3306/qlthi";
            String user = "root";
            String password = "root";
            Class.forName(driver);
//            Properties info = new Properties();
//            info.setProperty("characterEncoding", "utf8");
//            info.setProperty("user", "root");
//            info.setProperty("password", "root");
            Connection conn = DriverManager.getConnection(url, user, password); 
            
            return conn;
            
        } catch (Exception ex) {
            return null;
        }
    }
}
