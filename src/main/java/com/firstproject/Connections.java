package com.firstproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connections {
    static Connection connection;

    public static void toConnect() {
        try {
            if(connection == null) {
                String url = "jdbc:postgresql://localhost:5432/rocket";
                Properties props = new Properties();
                props.setProperty("user", "postgres");
                props.setProperty("password", "2704");
                // props.setProperty("ssl", "true");
                connection = DriverManager.getConnection(url, props);
                
                System.out.println("Successfully connected!");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
