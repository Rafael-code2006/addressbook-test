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

    public void selectContact(ContactData contactData){
        click(By.xpath("//input[@alt=\"Select ("+contactData.getFirstname() + " " + contactData.getLastname()+ ")\"]"));
    } // Выбор контактов

    public void createContact(int group){

    }

    public void checkingContact(ContactData contactData) {
        if(!isElementPresent(By.xpath("//input[@name=\\\"selected[]\\\" and @id=\\\"\"+ contactId + \"\\\"]"))){
            driver.findElement(By.linkText("add new")).click();
            createContact(contactData);
        }
    } // Проверка наличия контактов

    public void goToEdit(ContactData contactData){
        click(By.xpath("//input[@title=\"Select ("+contactData.getFirstname() + " " + contactData.getLastname() + ")\"]/../../td[@class=\"center\"][3]/a"));
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
    //*[@id="24"]
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
