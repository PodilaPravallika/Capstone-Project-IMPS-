package com.insurance.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.insurance.base.BaseTest;

public class PaymentTest extends BaseTest {
	
	protected void loginAsAdmin() {
        driver.get("http://localhost:8081/insurance-mysql-crud/login.jsp"); // adjust if port is different
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).click();
    }
	
    @Test(priority = 1)
    public void createPaymentTest() {
    	loginAsAdmin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.linkText("Payments")).click();

        driver.findElement(By.name("amount")).sendKeys("2000");
        driver.findElement(By.name("success")).sendKeys("true");
        driver.findElement(By.tagName("button")).click();

        Assert.assertTrue(driver.getPageSource().contains("SUCCESS"), "Payment not processed!");
    }

    @Test(priority = 2)
    public void deletePaymentTest() {
    	loginAsAdmin();

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement paymentsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Payments")));
    	paymentsLink.click();

        driver.findElement(By.linkText("Delete")).click();
        driver.switchTo().alert().accept();

        Assert.assertFalse(driver.getPageSource().contains("SUCCESS"), "Payment not deleted!");
    }
}

