package com.example.TestsAddressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void goToGroups(){
        click(By.linkText("groups"));
    }

    public void goToAddNewContacts(){
        click(By.linkText("add new"));
    }


}
