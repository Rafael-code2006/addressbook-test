package com.example.TestsAddressbook.appmanager;

import com.example.TestsAddressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    private List<WebElement> groups = new ArrayList<>();


    public List<WebElement> getGroups(){
        groups.add(driver.findElement(By.name("new_group")));
        return groups;
    }

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToContacts(){
        click(By.linkText("home page"));
    }

    public void saveContact(){
        click(By.xpath("//*[@value=\'Enter\']"));
    }

    public void selectedGroup(int group){
        click(By.xpath("//select[@name=\"new_group\"]/option[" + group + "]"));
    }

    public void newContact(ContactData contactData){
        type("firstname", contactData.getFirstname());
        timerSecond(30);
        type("lastname", contactData.getLastname());
        timerSecond(30);
        type("email", contactData.getEmail());
        timerSecond(30);
    }
}
