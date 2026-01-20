package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.MySet;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationTestContact extends TestBase {

    private ContactData contactData = new ContactData()
            .withFirstName("Artem")
            .withLastname("Alekseev")
            .withEmail("art@mail.ru")
            .withGroup("Test2");

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().checking(contactData);
    }

    @Test
    public void test() {
        app.contact().checkedPageHome();
        MySet<ContactData> before = app.contact().all();
        ContactData modified = before.iterator().next();

        ContactData modifyContact = new ContactData()
                .withId(modified.getId())
                .withFirstName("Rafael")
                .withLastname("Alekseev")
                .withEmail("art@mail.ru")
                .withGroup("Test2");

        app.contact().modify(before, modifyContact);

        MySet<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modified).withAdded(modifyContact)));
    }
}
