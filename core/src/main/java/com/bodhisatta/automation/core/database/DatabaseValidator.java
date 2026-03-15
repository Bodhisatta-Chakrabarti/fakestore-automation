package com.bodhisatta.automation.core.database;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DatabaseValidator {

    public static boolean productExists(String title)
    {
        try {
            String query="SELECT * FROM products WHERE title='\" + title + \"'";
            ResultSet resultSet=QueryExecutor.executeQuery(query);
            return resultSet.next();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static Map<String, Object> getProductByTitle(String title)
    {
        Map<String, Object> productData=new HashMap<>();

        try{
            String query="SELECT * FROM products WHERE title='\" + title + \"'";
            ResultSet rs=QueryExecutor.executeQuery(query);

            if (rs.next())
            {
                productData.put("title", rs.getString("title"));
                productData.put("price", rs.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productData;
    }
}
