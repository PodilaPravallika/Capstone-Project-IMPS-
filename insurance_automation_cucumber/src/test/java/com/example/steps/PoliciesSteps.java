package com.example.steps;

import com.aventstack.extentreports.ExtentTest;
import com.example.hooks.Hooks;
import com.example.utils.DriverManager;
import com.example.pages.PoliciesPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PoliciesSteps {

    WebDriver driver = DriverManager.getDriver();
    PoliciesPage policiesPage = new PoliciesPage(driver);
    ExtentTest test = Hooks.getTest();

    @Given("I am on the Insurance Dashboard")
    public void goToDashboard() {
        driver.get("http://localhost:8081/insurance-mysql-crud/dashboard");
        test.info("Navigated to Dashboard");
    }

    @When("I click on {string}")
    public void clickOn(String button) {
        policiesPage.clickButtonByName(button);
        test.info("Clicked on button: " + button);
    }

    @When("I fill the policy form with type {string}, holder {string}, age {string}, coverage {string}, and start date {string}")
    public void fillPolicyForm(String type, String holder, String age, String coverage, String startDate) {
        policiesPage.selectPolicyType(type);
        policiesPage.setHolderName(holder);
        policiesPage.setAge(Integer.parseInt(age));
        policiesPage.setCoverageAmount(Double.parseDouble(coverage));
        policiesPage.setStartDate(startDate);
        test.info("Filled policy form with: Type=" + type + ", Holder=" + holder + ", Age=" + age + ", Coverage=" + coverage + ", StartDate=" + startDate);
    }

    @When("I submit the form")
    public void submitForm() {
        policiesPage.clickSavePolicy();
        test.info("Submitted the policy form");
    }

    @Then("I should see {string} policy in the list")
    public void verifyPolicyAdded(String holder) {
        Assert.assertTrue(policiesPage.isPolicyPresent(holder));
        test.pass("Verified that policy for " + holder + " is present in the list");
    }

    @Given("I am on the Manage Policies page")
    public void goToManagePoliciesPage() {
        driver.get("http://localhost:8081/insurance-mysql-crud/policies");
        test.info("Navigated to Manage Policies page");
    }

    @When("I click on the Edit button of {string}")
    public void clickEdit(String holder) {
        policiesPage.clickEditButton(holder);
        test.info("Clicked Edit for holder: " + holder);
    }

    @When("I update the age to {string} and coverage to {string}")
    public void updatePolicy(String age, String coverage) {
        policiesPage.setAge(Integer.parseInt(age));
        policiesPage.setCoverageAmount(Double.parseDouble(coverage));
        test.info("Updated Age to " + age + ", Coverage to " + coverage);
    }

    @Then("I should see {string} policy updated with age {string} and coverage {string}")
    public void verifyPolicyUpdated(String holder, String age, String coverage) {
        Assert.assertTrue(policiesPage.isPolicyUpdated(holder, Integer.parseInt(age), Double.parseDouble(coverage)));
        test.pass("Verified policy for " + holder + " updated to Age: " + age + ", Coverage: " + coverage);
    }

    @When("I click on the Delete button of {string}")
    public void clickDelete(String holder) {
        policiesPage.clickDeleteButton(holder);
        test.info("Clicked Delete for holder: " + holder);
    }

    @Then("the policy for {string} should be removed from the list")
    public void verifyPolicyDeleted(String holder) {
        Assert.assertFalse(policiesPage.isPolicyPresent(holder));
        test.pass("Verified policy for " + holder + " is deleted");
    }
}

