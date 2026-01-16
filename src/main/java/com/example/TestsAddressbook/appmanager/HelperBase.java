package com.example.TestsAddressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }




    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void clear(By locator){
        driver.findElement(locator).clear();
    }

    protected void timerSecond(int seconds){
        driver.manage().timeouts().implicitlyWait(seconds ,TimeUnit.SECONDS);
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void type(String group_name, String test1) {
        click(By.name(group_name));
        driver.findElement(By.name(group_name)).clear();
        driver.findElement(By.name(group_name)).sendKeys(test1);
    }

    protected boolean isElementPresent(By locator) {
            long currentTime = System.currentTimeMillis();
            while(System.currentTimeMillis() < currentTime + 3000){
                try {
                    driver.findElement(locator);
                    return true;
                }catch (Exception ex) {
                    // Do nothing
                }
            }
            return false;
    }

    public boolean isThereAGroup(String nameClass) {
        if(!isElementPresent(By.className(nameClass))){
            return false;
        } else {
            return true;
        }
    }
}
