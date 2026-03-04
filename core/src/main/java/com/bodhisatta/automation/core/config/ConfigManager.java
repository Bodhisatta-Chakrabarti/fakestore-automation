package com.bodhisatta.automation.core.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties=new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties()
    {
        String env=System.getProperty("env", "dev");

        try(InputStream input=ConfigManager.class.getClassLoader()
                .getResourceAsStream("config/" + env + ".properties"))
        {
            properties.load(input);
        }catch (Exception e)
        {
            throw new RuntimeException("Failed to load config file for environment: " + env);
        }
    }

    public static String get(String key)
    {
        return properties.getProperty(key);
    }
}
