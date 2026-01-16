package com.example.TestsAddressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.List;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void goToGroups(){
        if(isElementPresent(By.tagName("h1"))
                && waitFindElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))){
            return;
        }
        click(By.linkText("groups"));
    }

    private WebElement waitFindElement(By locator) {
        long currentTime = System.currentTimeMillis();
        while(System.currentTimeMillis() < currentTime + 3000){
            try{
                return driver.findElement(locator);
            } catch (Exception ex){
                // Do nothing
            }
        }
        return null;
    }

    public void goToAddNewContacts(){

        if(!isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("add new"));
    }


}
