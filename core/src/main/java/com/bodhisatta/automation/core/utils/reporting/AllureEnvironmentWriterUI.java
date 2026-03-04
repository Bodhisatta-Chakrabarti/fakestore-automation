package com.bodhisatta.automation.core.utils.reporting;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class AllureEnvironmentWriterUI {

    public static void writeEnvironmentFile() {
//        String path=System.getProperty("allure.results.directory");
//
//        Properties props=new Properties();
//        props.setProperty("Browser", "Chrome");
//        props.setProperty("OS", System.getProperty("os.name"));
//        props.setProperty("Environment", "QA");
//
//        try(FileWriter writer=new FileWriter(path+"/environment.properties")){
//            props.store(writer, "Allure Environment");
//        }catch (IOException e)
//        {
//            e.printStackTrace();
//        }

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

            Properties props=new Properties();
            props.setProperty("Browser", "Chrome");
            props.setProperty("OS", System.getProperty("os.name"));
            props.setProperty("Environment", "QA");

            try (FileOutputStream fos = new FileOutputStream(file)) {
                props.store(fos, "Allure Environment Details");
            }

            System.out.println("Allure environment file created at: " + file.getAbsolutePath());

        } catch (Exception e) {
            //throw new RuntimeException("Failed to write Allure environment file", e);
            System.out.println("Could not write Allure environment file.");
            e.printStackTrace();
        }
    }
}
