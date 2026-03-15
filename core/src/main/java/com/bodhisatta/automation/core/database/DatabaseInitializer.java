package com.bodhisatta.automation.core.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase()
    {
        try{
            Connection connection=DatabaseConnectionManager.getConnection();
            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS products (\" +\n" +
                    "                            \"id INT AUTO_INCREMENT PRIMARY KEY,\" +\n" +
                    "                            \"title VARCHAR(255),\" +\n" +
                    "                            \"price DOUBLE\" +\n" +
                    "                            \")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
