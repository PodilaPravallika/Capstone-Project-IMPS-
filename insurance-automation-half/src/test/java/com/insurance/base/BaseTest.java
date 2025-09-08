package com.insurance.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Start on the login page before each test class
        driver.get("http://localhost:8081/insurance-mysql-crud/login");
    }

    /**
     * Utility method for logging in as admin user
     */
    protected void loginAsAdmin() {
        driver.get("http://localhost:8081/insurance-mysql-crud/login");
        driver.findElement(org.openqa.selenium.By.name("username")).sendKeys("admin");
        driver.findElement(org.openqa.selenium.By.name("password")).sendKeys("admin123");
        driver.findElement(org.openqa.selenium.By.tagName("button")).click();
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
