package com.portal.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;

        try {
            // MySQL 8.x aur 9.x dono ke liye yeh driver class perfectly compatible hai
            Class.forName("com.mysql.cj.jdbc.Driver");

            // database name: institute_db | user: root | password: "" (XAMPP default empty string)
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/institute_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                    "root",
                    ""   
            );

            System.out.println("Database Connected Successfully via MySQL Connector.");

        } catch (Exception e) {
            System.err.println("Database Connection Failed! Check if XAMPP MySQL is running.");
            e.printStackTrace();
        }

        return con;
    }
}