package com.bodhisatta.automation.core.driver;

import com.bodhisatta.automation.core.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver=new ThreadLocal<>();

    public static void initDriver()
    {
        String browser= ConfigManager.get("browser");
        boolean headless=Boolean.parseBoolean(ConfigManager.get("headless"));

        switch (browser.toLowerCase())
        {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions=new ChromeOptions();
                if (headless)
                {
                    chromeOptions.addArguments("--headless=new");
                }
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions=new FirefoxOptions();
                if (headless)
                {
                    firefoxOptions.addArguments("--headless=new");
                }
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.get().manage().window().maximize();
    }

    public static WebDriver getDriver()
    {
        return driver.get();
    }

    public static void quitDriver()
    {
        if (driver.get()!=null)
        {
            driver.get().quit();
            driver.remove();
        }
    }
}
