package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PremiumPage {
    private WebDriver driver;

    private By ageInput = By.name("age");
    private By coverageInput = By.name("coverage");
    private By policyTypeDropdown = By.name("policyType");
    private By calculateButton = By.xpath("//button[@type='submit']");
    private By resultText = By.cssSelector(".result");

    public PremiumPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterAge(String age) {
        driver.findElement(ageInput).clear();
        driver.findElement(ageInput).sendKeys(age);
    }

    public void enterCoverage(String coverage) {
        driver.findElement(coverageInput).clear();
        driver.findElement(coverageInput).sendKeys(coverage);
    }

    public void selectPolicyType(String type) {
        Select dropdown = new Select(driver.findElement(policyTypeDropdown));
        dropdown.selectByVisibleText(type);
    }

    public void clickCalculate() {
        driver.findElement(calculateButton).click();
    }
    
    public String getResultText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(resultText))
                .getText();
    }
}

