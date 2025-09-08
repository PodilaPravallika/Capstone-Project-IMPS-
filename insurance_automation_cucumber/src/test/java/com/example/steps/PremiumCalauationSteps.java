package com.example.steps;


import com.example.pages.PremiumPage;
import com.example.utils.DriverManager;
import com.example.hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class PremiumCalauationSteps {

    private WebDriver driver;
    private PremiumPage premiumPage;

    public PremiumCalauationSteps() {
        this.driver = DriverManager.getDriver();
        this.premiumPage = new PremiumPage(driver);
    }

    @Given("user is on the Premium Calculation page")
    public void userIsOnPremiumCalculationPage() {
        driver.get("http://localhost:8081/insurance-mysql-crud/premium_calculation.jsp");
        Hooks.getTest().info("Navigated to Premium Calculation page");
    }

    @When("user enters age {string}")
    public void userEntersAge(String age) {
        premiumPage.enterAge(age);
        Hooks.getTest().info("Entered age: " + age);
    }

    @When("user enters coverage amount {string}")
    public void userEntersCoverageAmount(String coverage) {
        premiumPage.enterCoverage(coverage);
        Hooks.getTest().info("Entered coverage: " + coverage);
    }

    @When("user selects policy type {string}")
    public void userSelectsPolicyType(String type) {
        premiumPage.selectPolicyType(type);
        Hooks.getTest().info("Selected policy type: " + type);
    }

    @When("user submits the premium calculation")
    public void userSubmitsThePremiumCalculation() {
        premiumPage.clickCalculate();
        Hooks.getTest().info("Clicked Calculate button");
    }

    @Then("calculated premium should be displayed")
    public void calculatedPremiumShouldBeDisplayed() {
        String result = premiumPage.getResultText();
        Hooks.getTest().info("Result displayed: " + result);
        Assert.assertTrue("Premium not displayed", result.contains("Calculated Premium"));
    }

    @Then("error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        String result = premiumPage.getResultText();
        Hooks.getTest().info("Error displayed: " + result);
        Assert.assertTrue("Error not displayed", result.contains("Error"));
    }
}

