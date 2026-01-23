package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.MySet;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationTestContact extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if(!app.isElementPresent(By.xpath("//a[text()=\"Last name\"]"))){
            app.contact().returnToHome();
        }
    }

    @Test
    public void test() {
        MySet<ContactData> before = app.db().contacts();
        ContactData modified = before.iterator().next();

        ContactData modifyContact = new ContactData()
                .withId(modified.getId())
                .withFirstName("Rafael")
                .withLastname("Alekseev")
                .withEmail("art@mail.ru");

        app.contact().modify(before, modifyContact);

        MySet<ContactData> after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modified).withAdded(modifyContact)));
    }
}
