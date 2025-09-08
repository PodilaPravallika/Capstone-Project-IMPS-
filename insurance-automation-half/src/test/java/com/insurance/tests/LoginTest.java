package com.insurance.tests;

import com.insurance.base.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void validLoginTest() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed!");
        //driver.findElement(By.linkText("Logout")).click();
    }

    @Test(priority = 2)
    public void invalidLoginTest() {
        driver.get("http://localhost:8081/insurance-mysql-crud/login.jsp");
        driver.findElement(By.name("username")).sendKeys("wrong");
        driver.findElement(By.name("password")).sendKeys("wrong");
        driver.findElement(By.tagName("button")).click();

        String errorText = driver.findElement(By.xpath("/html/body/div/div[1]")).getText();
        Assert.assertEquals(errorText, "Invalid credentials", "Error message not shown!");
    }
}
