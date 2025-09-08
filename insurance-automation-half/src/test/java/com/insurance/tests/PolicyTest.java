package com.insurance.tests;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.insurance.base.BaseTest;

public class PolicyTest extends BaseTest {

	protected void loginAsAdmin() {
        driver.get("http://localhost:8081/insurance-mysql-crud/login.jsp"); // adjust if port is different
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).click();
    }
    @Test(priority = 1)
    public void createPolicyTest() {
    	loginAsAdmin();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Go to policies page
        driver.findElement(By.linkText("Manage Policies")).click();
        driver.findElement(By.linkText("+ New Policy")).click();

        // Fill form
        driver.findElement(By.name("holderName")).sendKeys("John Doe");
        driver.findElement(By.name("age")).sendKeys("35");
        driver.findElement(By.name("coverageAmount")).sendKeys("500000");
        driver.findElement(By.name("startDate")).sendKeys("2025-09-01");

        // Save
        driver.findElement(By.tagName("button")).click();

        // Verify
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("John Doe"), "Policy not created!");
    }

    @Test(priority = 2)
    public void editPolicyTest() {
    	loginAsAdmin();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	driver.findElement(By.linkText("Manage Policies")).click();
        driver.findElement(By.linkText("Edit")).click();
        driver.findElement(By.name("holderName")).clear();
        driver.findElement(By.name("holderName")).sendKeys("John Smith");
        driver.findElement(By.tagName("button")).click();

        Assert.assertTrue(driver.getPageSource().contains("John Smith"), "Policy not updated!");
    }

    @Test(priority = 3)
    public void deletePolicyTest() {
    	loginAsAdmin();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	driver.findElement(By.linkText("Manage Policies")).click();
        driver.findElement(By.linkText("Delete")).click();
        driver.switchTo().alert().accept(); // confirm delete

        Assert.assertFalse(driver.getPageSource().contains("John Smith"), "Policy not deleted!");
    }
}
