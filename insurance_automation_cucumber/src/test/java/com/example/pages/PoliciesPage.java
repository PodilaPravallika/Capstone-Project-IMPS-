package com.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PoliciesPage {

    WebDriver driver;

    public PoliciesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonByName(String name) {
        WebElement btn = driver.findElement(By.xpath("//button[contains(text(),'" + name + "')] | //a[contains(text(),'" + name + "')]"));
        btn.click();
    }

    public void selectPolicyType(String type) {
        Select dropdown = new Select(driver.findElement(By.name("type")));
        dropdown.selectByVisibleText(type);
    }

    public void setHolderName(String name) {
        WebElement field = driver.findElement(By.name("holderName"));
        field.clear();
        field.sendKeys(name);
    }

    public void setAge(int age) {
        WebElement field = driver.findElement(By.name("age"));
        field.clear();
        field.sendKeys(String.valueOf(age));
    }

    public void setCoverageAmount(double amount) {
        WebElement field = driver.findElement(By.name("coverageAmount"));
        field.clear();
        field.sendKeys(String.valueOf(amount));
    }

    public void setStartDate(String date) {
        WebElement field = driver.findElement(By.name("startDate"));
        field.clear();
        field.sendKeys(date);
    }

    public void clickSavePolicy() {
        driver.findElement(By.xpath("//button[text()='Save Policy']")).click();
    }

    public boolean isPolicyPresent(String holderName) {
        return driver.getPageSource().contains(holderName);
    }

    public void clickEditButton(String holderName) {
        WebElement row = getPolicyRow(holderName);
        row.findElement(By.xpath(".//a[contains(text(),'Edit')]")).click();
    }

    public void clickDeleteButton(String holderName) {
        WebElement row = getPolicyRow(holderName);
        row.findElement(By.xpath(".//a[contains(text(),'Delete')]")).click();
        driver.switchTo().alert().accept(); // handle confirm dialog
    }


    public boolean isPolicyUpdated(String holderName, int age, double coverage) {
        WebElement row = getPolicyRow(holderName);
        return row.getText().contains(String.valueOf(age)) && row.getText().contains(String.valueOf(coverage));
    }

    private WebElement getPolicyRow(String holderName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
        for (WebElement row : rows) {
            if (row.getText().contains(holderName)) {
                return row;
            }
        }
        throw new NoSuchElementException("Policy with holder " + holderName + " not found.");
    }
}

