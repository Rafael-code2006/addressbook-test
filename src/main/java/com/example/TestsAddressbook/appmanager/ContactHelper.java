package com.example.TestsAddressbook.appmanager;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.MySet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public void returnToHome(){
        click(By.xpath("//a[text()='home']"));
    }

    public void fillForm(ContactData contactData, boolean creation) {
        if (contactData.getFirstname() != null) {
            type("firstname", contactData.getFirstname());
        }
        if (contactData.getLastname() != null) {
            type("lastname", contactData.getLastname());
        }
        if (contactData.getEmail() != null) {
            type("email", contactData.getEmail());
        }

        if (creation) {
            try {
                new Select(driver.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroup());
                save();
            } catch (Exception ex) {
                save();
            }
        } else {
            Assert.assertTrue(isElementPresent(By.name("new_group")));
        }
    }

    private void save() {
        click(By.xpath("//*[@value='Enter']"));
    }

    public void modify(MySet<ContactData> before, ContactData modifyContact) {
        if (before == null || before.isEmpty()) {
            throw new IllegalArgumentException("Передан пустой набор контактов для модификации");
        }
        ContactData contact = before.iterator().next();
        goToEdit(contact.getId());
        fillForm(modifyContact, false);
        updateContact();
        contactCache = null;
        returnToHomePages();
    }

    private void timer(int l) {
        driver.manage().timeouts().implicitlyWait(l, TimeUnit.SECONDS);
    }

    public void checking(ContactData contactData) {
        String user = contactData.getFirstname() + " " + contactData.getLastname();
        if (!isElementPresent(By.xpath("//input[@alt='Select (" + user + ")']"))) {
            create(contactData);
            // обновляем id после создания
            int newId = all().stream().mapToInt(ContactData::getId).max().getAsInt();
            contactData.withId(newId);
        }
    }

    public void select(ContactData contactData) {
        timer(30);
        click(By.cssSelector("input[value='" + contactData.getId() + "']"));
    }

    public void goToEdit(int contactId) {
        click(By.xpath("//input[@value='" + contactId + "']/../../td[@class='center'][3]/a"));
    }

    public void updateContact() {
        click(By.xpath("//*[@id='content']/form[1]/input[1]"));
    }

    private static MySet<ContactData> contactCache;

    public MySet<ContactData> all() {
        if (contactCache != null) {
            return new MySet<ContactData>(contactCache);
        }
        contactCache = new MySet<ContactData>();
        List<WebElement> elementsWeb = waitFindElements(By.xpath("//input[@name='selected[]']"));
        for (WebElement x : elementsWeb) {
            String title = x.getAttribute("title");
            if (title != null && title.contains("(") && title.contains(")")) {
                String name = title.substring(title.indexOf("(") + 1, title.indexOf(")"));
                int id = Integer.parseInt(x.getAttribute("id"));
                contactCache.add(new ContactData().withId(id).withFirstName(name));
            }
        }
        return new MySet<ContactData>(contactCache);
    }

    public int count() {
        return waitFindElements(By.name("selected[]")).size();
    }
}
