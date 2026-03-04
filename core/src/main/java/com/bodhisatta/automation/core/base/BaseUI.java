package com.bodhisatta.automation.core.base;

import com.bodhisatta.automation.core.driver.DriverFactory;
import org.openqa.selenium.WebDriver;

public class BaseUI {

    protected WebDriver driver;

    public void init()
    {
        DriverFactory.initDriver();
        driver=DriverFactory.getDriver();
    }

    public void quit()
    {
        DriverFactory.quitDriver();
    }
}
