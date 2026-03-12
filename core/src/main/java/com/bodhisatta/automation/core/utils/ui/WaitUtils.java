package com.bodhisatta.automation.core.utils.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static WebDriver driver;

    public WaitUtils(WebDriver driver)
    {
        WaitUtils.driver=driver;
    }

    public static WebElement waitForVisibility(By locator)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
