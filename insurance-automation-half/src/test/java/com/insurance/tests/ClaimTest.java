package com.insurance.tests;

import com.insurance.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ClaimTest extends BaseTest {

    protected void loginAsAdmin() {
        driver.get("http://localhost:8081/insurance-mysql-crud/login.jsp"); // adjust if port is different
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).click();
    }

    @Test(priority = 1)
    public void createClaimTest() {
        loginAsAdmin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on Claims menu
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Submit/View Claims"))).click();

        // Fill claim form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("description")))
                .sendKeys("Hospitalization claim");
        driver.findElement(By.name("amount")).sendKeys("10000");
        driver.findElement(By.tagName("button")).click();

        // Verify claim created
        Assert.assertTrue(driver.getPageSource().contains("Hospitalization claim"),
                "Claim not created!");
    }

    @Test(priority = 2)
    public void deleteClaimTest() {
        loginAsAdmin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Claims page
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Submit/View Claims"))).click();

        // Verify claim exists before deleting
        Assert.assertTrue(driver.getPageSource().contains("Hospitalization claim"),
                "Claim not found before delete!");

        // Locate delete link for the specific row containing "Hospitalization claim"
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//table//tr[td[contains(text(),'Hospitalization claim')]]//a[text()='Delete']")
        ));

        // Click delete
        deleteBtn.click();

        // Handle confirmation alert
        driver.switchTo().alert().accept();

        // Wait until that row is gone
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//table//tr[td[contains(text(),'Hospitalization claim')]]")
        ));

        // Verify claim is deleted
        Assert.assertFalse(driver.getPageSource().contains("Hospitalization claim"),
                "Claim not deleted!");
    }

}
