package com.example.TestsAddressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleToIntFunction;

public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }


    protected void click(By locator) {
        waitFindElement(locator).click();
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void type(String group_name, String text) {
        click(By.name(group_name));
        waitFindElement(By.name(group_name)).clear();
        if(text != null) {
            waitFindElement(By.name(group_name)).sendKeys(text);
        }
    }

    protected boolean isElementPresent(By locator) {
            long currentTime = System.currentTimeMillis();
            while(System.currentTimeMillis() < currentTime + 1000){
                try {
                    waitFindElement(locator);
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

    protected List<WebElement> waitFindElements(By xpath) {
        long currentTime = System.currentTimeMillis();
        while(System.currentTimeMillis() < currentTime + 3000){
            List<WebElement> elements = new ArrayList<>();
            if(!driver.findElements(xpath).isEmpty()){
                elements = driver.findElements(xpath);
                if(!elements.isEmpty()) return elements;
            }
        }
        return null;
    }

    public WebElement waitFindElement(By locator) {
        long currentTime = System.currentTimeMillis();
        while(System.currentTimeMillis() < currentTime + 500){
            try{
                return driver.findElement(locator);
            } catch (Exception ex){
                // Do nothing
            }
        }
        return null;
    }

}
