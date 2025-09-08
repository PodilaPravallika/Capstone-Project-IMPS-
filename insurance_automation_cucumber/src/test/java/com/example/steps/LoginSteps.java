package com.example.steps;

import com.example.pages.LoginPage;
import com.example.utils.DriverManager;
import com.example.hooks.Hooks;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;

public class LoginSteps {
    private WebDriver driver = DriverManager.getDriver();
    private LoginPage loginPage = new LoginPage(driver);

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage.open("http://localhost:8081");
        Hooks.getTest().info("Opened Login Page");
    }

    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        Hooks.getTest().info("Entered Username and Password");
    }

    @When("clicks the login button")
    public void clicks_the_login_button() {
        loginPage.clickLogin();
        Hooks.getTest().info("Clicked Login Button");
    }

    @Then("the user should be redirected to the homepage {string}")
    public void the_user_should_be_redirected_to_the_homepage(String expectedUrl) {
        assertEquals(expectedUrl, loginPage.getCurrentUrl());
        Hooks.getTest().pass("Redirected to homepage successfully");
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedMessage) {
        assertEquals(expectedMessage, loginPage.getErrorMessage());
        Hooks.getTest().fail("Error message displayed correctly");
    }
}
