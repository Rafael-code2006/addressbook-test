package com.example.TestsAddressbook.appmanager;

import com.example.TestsAddressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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
                saveContact();
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
        click(By.cssSelector("input[value='" + contactData.getId() + "']"));
    }




    public void goToEdit(ContactData contactData) {
        click(By.xpath("//input[@value='" + contactData.getId() + "']/../../td[@class='center'][3]/a"));
    }


    public void updateContact() {
        click(By.xpath("//*[@id='content']/form[1]/input[1]"));
    }


    public List<ContactData> getContactList() {
        List<ContactData> elements = new ArrayList<>();
        List<WebElement> elementsWeb = waitFindElements(By.xpath("//input[@name='selected[]']"));


        for(WebElement x : elementsWeb){
            String title = x.getAttribute("title");
            String name = title.substring(title.indexOf("(") + 1, title.indexOf(")"));
            int id = Integer.parseInt(x.getAttribute("id"));
            ContactData contactData = new ContactData(id, name, null, null, null);
            elements.add(contactData);
        }
        return elements;
    }
}
