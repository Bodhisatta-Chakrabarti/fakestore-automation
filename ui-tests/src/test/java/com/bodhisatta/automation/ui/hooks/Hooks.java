package com.bodhisatta.automation.ui.hooks;

import com.bodhisatta.automation.core.base.BaseUI;
import com.bodhisatta.automation.core.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class Hooks extends BaseUI {

    @Before
    public void setUp()
    {
        init();
        //DriverFactory.initDriver();
        driver.get("https://automationexercise.com/");
    }

    @After
    public void tearDown(Scenario scenario)
    {
        if (scenario.isFailed())
        {
            byte[] screenshot=((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            //scenario.attach(screenshot, "image/png", "Failure Screenshot");
            Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
        }

        //quit();
        DriverFactory.quitDriver();
    }
}
