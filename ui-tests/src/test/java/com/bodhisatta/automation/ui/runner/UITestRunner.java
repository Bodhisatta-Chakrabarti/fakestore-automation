package com.bodhisatta.automation.ui.runner;

import com.bodhisatta.automation.core.utils.reporting.AllureEnvironmentWriterUI;
import com.bodhisatta.automation.core.utils.retry.RetryListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.bodhisatta.automation.ui",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
@Listeners(RetryListener.class)
public class UITestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void setupEnvironment()
    {
        System.out.println("user.dir = " + System.getProperty("user.dir"));
        AllureEnvironmentWriterUI.writeEnvironmentFile();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios()
    {
        return super.scenarios();
    }
}
