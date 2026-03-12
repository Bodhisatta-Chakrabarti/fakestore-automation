package com.bodhisatta.automation.ui.stepdefinitions;

import com.bodhisatta.automation.core.driver.DriverFactory;
import com.bodhisatta.automation.ui.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage=new LoginPage(DriverFactory.getDriver());

    @Given("I am on the login page")
    public void i_am_on_the_login_page()
    {
        //loginPage.clickSignupLogin();
    }

    @When("I login with valid credentials")
    public void i_login_with_valid_credentials()
    {
//        loginPage.enterEmail("bschakrabarti@gmail.com");
//        loginPage.enterPassword("Protecti0n$");
//        loginPage.clickLogin();
    }

    @Then("I should see logged in message")
    public void i_should_see_logged_in_message()
    {
        Assert.assertTrue(loginPage.isUserLoggedIn(), "User is not logged in!");
        //Assert.assertFalse(loginPage.isUserLoggedIn());
    }
}
