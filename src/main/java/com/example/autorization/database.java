package com.example.autorization;

import java.sql.Connection;
import java.sql.DriverManager;


public class database {

    public static Connection connectDb() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/autorization", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}