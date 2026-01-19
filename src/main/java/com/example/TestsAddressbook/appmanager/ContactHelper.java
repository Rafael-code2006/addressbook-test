package com.example.TestsAddressbook.appmanager;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    private void goToAddNew() {
        driver.findElement(By.linkText("add new")).click();
    }

    public void returnToHomePages() {
        click(By.linkText("home page"));
    }

    public void create(ContactData contactData) {
        goToAddNew();
        fillForm(contactData, true);
        contactCache = null;
        returnToHomePages();
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type("firstname", contactData.getFirstname());
        type("lastname", contactData.getLastname());
        type("email", contactData.getEmail());

        if (creation) {
            try {
                new Select(driver.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroup());
                save();
            } catch(Exception ex){
                save();
            }

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    private void save() {
        click(By.xpath("//*[@value='Enter']"));
    }

    public void modify(Contacts before, ContactData contactData) {
        select(before.iterator().next());
        goToEdit(before.iterator().next());
        fillForm(contactData, false);
        updateContact();
        contactCache = null;
        returnToHomePages();
    }

    public void checking(ContactData contactData) {
        String user = contactData.getFirstname() + " " + contactData.getLastname();
        if (!isElementPresent(By.xpath("//input[@alt='Select (" + user + ")']"))) {
            create(contactData);
        }
    }

    public void select(ContactData contactData) {
        click(By.cssSelector("input[value='" + contactData.getId() + "']"));
    }

    public void goToEdit(ContactData contactData) {
        click(By.xpath("//input[@value='" + contactData.getId() + "']/../../td[@class='center'][3]/a"));
    }

    public void updateContact() {
        click(By.xpath("//*[@id='content']/form[1]/input[1]"));
    }

    private Contacts contactCache;

    public Contacts all() {
        if(contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elementsWeb = waitFindElements(By.xpath("//input[@name='selected[]']"));
        for(WebElement x : elementsWeb){
            String title = x.getAttribute("title");
            String name = title.substring(title.indexOf("(") + 1, title.indexOf(")"));
            int id = Integer.parseInt(x.getAttribute("id"));
            contactCache.add(new ContactData().withId(id).withFirstName(name));
        }
        return new Contacts(contactCache);
    }

    public int count() {
        return all().size();
    }
}