package com.bodhisatta.automation.core.utils.reporting;

import com.bodhisatta.automation.core.config.ConfigManager;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class AllureEnvironmentWriterAPI {

    public static void writeEnvironmentFile() {
        try {

            String resultsDir = Paths.get(
                    System.getProperty("user.dir"),
                    "target",
                    "allure-results"
            ).toString();

            File directory = new File(resultsDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(directory, "environment.properties");

            Properties properties = new Properties();
            properties.setProperty("Environment", System.getProperty("env", "default"));
            properties.setProperty("Base URL", ConfigManager.get("base.url"));

            try (FileOutputStream fos = new FileOutputStream(file)) {
                properties.store(fos, "Allure Environment Details");
            }

            System.out.println("Allure environment file created at: " + file.getAbsolutePath());

        } catch (Exception e) {
            //throw new RuntimeException("Failed to write Allure environment file", e);
            System.out.println("Could not write Allure environment file.");
            e.printStackTrace();
        }
    }
}
