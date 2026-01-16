package com.example.TestsAddressbook.appmanager;

import com.example.TestsAddressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }



    private void goToNewContact() {
        driver.findElement(By.linkText("add new")).click();
    }

    public void returnToContacts() {
        click(By.linkText("home page"));
    }



    public void createContact(ContactData contactData) {
        goToNewContact();
        newContact(contactData, true);
        returnToContacts();
    }

    public void newContact(ContactData contactData, boolean creation) {
        type("firstname", contactData.getFirstname());
        type("lastname", contactData.getLastname());
        type("email", contactData.getEmail());

        if (creation) {
            try {
                new Select(driver.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroup());
            } catch(Exception ex){
                saveContact();
            }

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void saveContact() {
        click(By.xpath("//*[@value='Enter']"));
    }



    public void checkingContact(ContactData contactData) {
        String user = contactData.getFirstname() + " " + contactData.getLastname();
        if (!isElementPresent(By.xpath("//input[@alt='Select (" + user + ")']"))) {
            createContact(contactData);
        }
    }

    public void selectContact(ContactData contactData) {
        String user = contactData.getFirstname() + " " + contactData.getLastname();
        click(By.xpath("//input[@alt='Select (" + user + ")']"));
    }



    public void goToEdit(ContactData contactData) {
        String user = contactData.getFirstname() + " " + contactData.getLastname();
        click(By.xpath("//input[@title='Select (" + user + ")']/../../td[@class='center'][3]/a"));
    }

    public void updateContact() {
        click(By.xpath("//*[@id='content']/form[1]/input[1]"));
    }


}
