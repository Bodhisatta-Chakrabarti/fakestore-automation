package com.bodhisatta.automation.core.api.runner;

import com.bodhisatta.automation.core.utils.reporting.AllureEnvironmentWriterAPI;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.bodhisatta.automation.core.api",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class APITestRunner extends AbstractTestNGCucumberTests {

        @BeforeSuite
        public void setupEnvironment()
        {
                System.out.println("user.dir = " + System.getProperty("user.dir"));
                AllureEnvironmentWriterAPI.writeEnvironmentFile();
        }

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios()
        {
                return super.scenarios();
        }
}
