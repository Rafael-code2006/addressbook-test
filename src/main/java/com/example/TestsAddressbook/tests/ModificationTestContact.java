package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationTestContact extends TestBase{

    private ContactData contactData = new ContactData()
            .withFirstName("Artem")
            .withLastname("Alekseev")
            .withEmail("art@mail.ru")
            .withGroup("Test2");


    @BeforeMethod
   public void esurePreconditions(){
        app.contact().checking(contactData);
   }

    @Test
    private void test(){
        ContactData modifyContact = new ContactData()
                .withId(contactData.getId())
                .withFirstName("Rafael")
                .withLastname("Alekseev")
                .withEmail("art@mail.ru")
                .withGroup("Test2");
        Contacts before = app.contact().all();
        app.contact().modify(before, modifyContact);
        int counter = app.contact().count();
        assertThat(counter, equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}
