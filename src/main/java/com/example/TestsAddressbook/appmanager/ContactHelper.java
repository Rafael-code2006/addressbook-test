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

    private static final String XPATH_INPUT_FIRSTNAME = "//input[@name='firstname']";
    private static final String XPATH_INPUT_LASTNAME = "//input[@name='lastname']";
    private static final String XPATH_INPUT_EMAIL = "//input[@name='email']";
    private static final String XPATH_INPUT_HOME = "//input[@name='home']";
    private static final String XPATH_INPUT_MOBILE = "//input[@name='mobile']";
    private static final String XPATH_INPUT_WORK = "//input[@name='work']";


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
        goToEdit(contact);
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
        click(By.cssSelector(String.format("input[value='%d']", contactData.getId())));
    }

    public void goToEdit(ContactData contactData) {
        select(contactData);
       // click(By.xpath("//input[@value='" + contactId + "']/../../td[@class='center'][3]/a"));
        click(By.xpath(String.format("//input[@value='%d']/../../td[@class='center'][3]/a", contactData.getId())));
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
            List<WebElement> cells = waitFindElements(By.xpath("//td"));
            String name = cells.get(0).getText();
            String lastname = cells.get(1).getText();
                int id = Integer.parseInt(x.getAttribute("id"));
                String allPhones = cells.get(5).getText();
                String[] phones = cells.get(5).getText().split("\n");
                contactCache.add(new ContactData()
                        .withId(id)
                        .withFirstName(name)
                        .withLastname(lastname)
                        .setAllPhones(allPhones));
            }
        return new MySet<ContactData>(contactCache);
    }

    public void checkedPageHome() {
        if(!isElementPresent(By.xpath("//a[text()=\"Last name\"]"))){
            returnToHome();
        }
    }

    public int count() {
        return waitFindElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        goToEdit(contact);
        String firstname = waitFindElement(By.xpath(XPATH_INPUT_FIRSTNAME)).getAttribute("value");
        String lastname = waitFindElement(By.xpath(XPATH_INPUT_LASTNAME)).getAttribute("value");
        String home = waitFindElement(By.xpath(XPATH_INPUT_HOME)).getAttribute("value");
        String mobile = waitFindElement(By.xpath(XPATH_INPUT_MOBILE)).getAttribute("value");
        String work = waitFindElement(By.xpath(XPATH_INPUT_WORK)).getAttribute("value");
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstname)
                .withLastname(lastname)
                .withHome(home)
                .withMobile(mobile)
                .withWork(work);
    }
}
