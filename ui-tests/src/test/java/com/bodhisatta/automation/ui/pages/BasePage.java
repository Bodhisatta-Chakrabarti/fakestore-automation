package com.bodhisatta.automation.ui.pages;

import com.bodhisatta.automation.core.utils.ui.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver)
    {
        this.driver=driver;
        new WaitUtils(driver);
    }

    protected WebElement waitForElement(By locator)
    {
        return WaitUtils.waitForVisibility(locator);
    }

    protected void click(By locator)
    {
        WaitUtils.waitForClickable(locator);
    }

    protected void type(By locator, String text)
    {
        WebElement element=WaitUtils.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }
}
