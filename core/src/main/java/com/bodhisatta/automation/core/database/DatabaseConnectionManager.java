package com.bodhisatta.automation.core.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionManager {

    private static Connection connection;

    public static Connection getConnection()
    {
        try{
            if (connection==null || connection.isClosed())
            {
                connection= DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
                        "sa",
                        "");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return connection;
    }
}
