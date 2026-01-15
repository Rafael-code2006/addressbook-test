package com.example.TestsAddressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(By locator) {
        WebElement elementClick = (new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(locator)));
        if(elementClick != null){
        elementClick.click();
        }

    }

    protected void clear(By locator){
        WebElement elementClear = (new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(locator)));
        if(elementClear != null){
            elementClear.clear();
        }
    }

    protected void timerSecond(int seconds){
        driver.manage().timeouts().implicitlyWait(30 ,TimeUnit.SECONDS);
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
        if(test1 != null){
            driver.findElement(By.name(group_name)).clear();
            driver.findElement(By.name(group_name)).sendKeys(test1);
        }
        }

}
