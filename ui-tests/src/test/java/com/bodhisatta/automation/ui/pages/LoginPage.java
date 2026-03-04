package com.bodhisatta.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }

    private By signupLoginButton=By.xpath("//a[contains(text(), 'Signup / Login')]");
    private By emailField=By.name("email");
    private By passwordField=By.name("password");
    private By loginButton=By.xpath("//button[@data-qa='login-button']");
    private By loggedInText=By.xpath("//a[contains(text(), 'Logged in as')]");

    public void clickSignupLogin()
    {
        driver.findElement(signupLoginButton).click();
    }

    public void enterEmail(String email)
    {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password)
    {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin()
    {
        driver.findElement(loginButton).click();
    }

    public boolean isUserLoggedIn()
    {
        return driver.findElement(loggedInText).isDisplayed();
    }
}
