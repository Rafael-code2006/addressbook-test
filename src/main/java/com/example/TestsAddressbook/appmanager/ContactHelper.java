package com.example.TestsAddressbook.appmanager;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void selectContact(int contactId){
        click(By.xpath("//input[@name=\"selected[]\" and @id=\""+ contactId + "\"]"));
    }

    public void createContact(int group){

    }

    public void checkingContact(ContactData contactData) {
        if(isElementPresent(By.className("contact"))){
            createContact(contactData);
        }
    }

    public void goToEdit(int contactId){
        click(By.xpath("//a[@href=\"edit.php?id="+contactId+"\"]"));
    }

    public void returnToContacts(){
        click(By.linkText("home page"));
    }

    public void updateContact(){
        click(By.xpath("//*[@id=\"content\"]/form[1]/input[1]"));
    }

    public void saveContact(){
        click(By.xpath("//*[@value=\'Enter\']"));
    }

    public void selectedGroup(int group){
        click(By.xpath("//select[@name=\"new_group\"]/option[last()]"));


    }

    public void createContact(ContactData contactData) {
        newContact(contactData, true);
        saveContact();
        returnToContacts();
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    public void newContact(ContactData contactData, boolean creation){
        type("firstname", contactData.getFirstname());
        type("lastname", contactData.getLastname());
        type("email", contactData.getEmail());

        if(creation){
                new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

}
