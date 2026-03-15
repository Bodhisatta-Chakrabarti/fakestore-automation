package com.bodhisatta.automation.core.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryExecutor {

    public static ResultSet executeQuery(String query)
    {
        try{
            Connection connection=DatabaseConnectionManager.getConnection();
            Statement statement=connection.createStatement();
            return statement.executeQuery(query);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void executeUpdate(String query)
    {
        try{
            Connection connection=DatabaseConnectionManager.getConnection();
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
