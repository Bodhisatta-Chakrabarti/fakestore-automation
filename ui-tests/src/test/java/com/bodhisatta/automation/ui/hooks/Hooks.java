package com.bodhisatta.automation.ui.hooks;

import com.bodhisatta.automation.core.base.BaseUI;
import com.bodhisatta.automation.core.driver.DriverFactory;
import com.bodhisatta.automation.core.utils.logging.LogManagerUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class Hooks extends BaseUI {

    private static final Logger logger= LogManagerUtil.getLogger(Hooks.class);

    @Before
    public void setUp()
    {
        logger.info("Starting UI test execution");
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
