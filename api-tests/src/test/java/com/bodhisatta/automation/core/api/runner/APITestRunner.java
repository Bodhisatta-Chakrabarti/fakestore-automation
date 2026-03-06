package com.bodhisatta.automation.core.api.runner;

import com.bodhisatta.automation.core.api.utils.WireMockServerManager;
import com.bodhisatta.automation.core.utils.reporting.AllureEnvironmentWriterAPI;
import com.bodhisatta.automation.core.utils.retry.RetryListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

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
@Listeners(RetryListener.class)
public class APITestRunner extends AbstractTestNGCucumberTests {

        @BeforeSuite
        public void setupEnvironment()
        {
                //Start mock server
                WireMockServerManager.startServer();

                System.out.println("user.dir = " + System.getProperty("user.dir"));
                AllureEnvironmentWriterAPI.writeEnvironmentFile();
        }

        @Override
        //@DataProvider(parallel = true)
        @DataProvider
        public Object[][] scenarios()
        {
                return super.scenarios();
        }

        @AfterSuite
        public void stopMockServer()
        {
                WireMockServerManager.stopServer();
        }
}
