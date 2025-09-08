package com.example.steps;

import com.aventstack.extentreports.ExtentTest;
import com.example.hooks.Hooks;
import com.example.utils.DriverManager;
import com.example.pages.ClaimsPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ClaimsSteps {

    WebDriver driver = DriverManager.getDriver();
    ClaimsPage claimsPage = new ClaimsPage(driver);
    ExtentTest test = Hooks.getTest();

    @Given("I am on the Claims page")
    public void goToClaimsPage() {
        driver.get("http://localhost:8081/insurance-mysql-crud/claims");
        test.info("Navigated to Claims page");
    }

    @When("I select policy {string}")
    public void selectPolicy(String policy) {
        claimsPage.selectPolicy(policy);
        test.info("Selected policy: " + policy);
    }

    @When("I enter description {string}")
    public void enterDescription(String desc) {
        claimsPage.setDescription(desc);
        test.info("Entered description: " + desc);
    }

    @When("I enter claim amount {string}")
    public void enterClaimAmount(String amount) {
        claimsPage.setClaimAmount(amount);
        test.info("Entered claim amount: " + amount);
    }

    @When("I submit the claim")
    public void submitClaim() {
        claimsPage.submitClaim();
        test.info("Submitted the claim");
    }

    @Then("I should see claim with description {string} in the list")
    public void verifyClaimPresent(String desc) {
        Assert.assertTrue(claimsPage.isClaimPresent(desc), "Claim not found in list!");
        test.pass("Verified claim with description " + desc + " is present");
    }

    @When("I click on the Delete button of claim {string}")
    public void deleteClaim(String desc) {
        claimsPage.clickDeleteButton(desc);
        test.info("Clicked delete for claim: " + desc);
    }

    @Then("the claim with description {string} should be removed from the list")
    public void verifyClaimDeleted(String desc) {
        Assert.assertFalse(claimsPage.isClaimPresent(desc), "Claim still present in list!");
        test.pass("Verified claim with description " + desc + " is deleted");
    }
}
