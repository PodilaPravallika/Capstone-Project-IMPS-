package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ClaimsPage {

    private WebDriver driver;

    // Locators
    private By policyDropdown = By.id("policyId");
    private By descriptionField = By.id("description");
    private By claimAmountField = By.id("amount");
    private By submitButton = By.xpath("//button[text()='Submit Claim']");

    public ClaimsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectPolicy(String policy) {
        Select select = new Select(driver.findElement(policyDropdown));
        select.selectByVisibleText(policy);
    }

    public void setDescription(String desc) {
        driver.findElement(descriptionField).clear();
        driver.findElement(descriptionField).sendKeys(desc);
    }

    public void setClaimAmount(String amount) {
        driver.findElement(claimAmountField).clear();
        driver.findElement(claimAmountField).sendKeys(amount);
    }

    public void submitClaim() {
        driver.findElement(submitButton).click();
    }

    public boolean isClaimPresent(String desc) {
        return driver.getPageSource().contains(desc);
    }

    public void clickDeleteButton(String desc) {
        driver.findElement(By.xpath("//td[text()='" + desc + "']/following-sibling::td/a[text()='Delete']")).click();
        driver.switchTo().alert().accept();
    }
}
