package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.Contacts;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateTestContact extends TestBase {




    @Test
    private void test(){
        ContactData contactData = new ContactData()
                .withFirstName("Rafael")
                .withLastname("Gimadeyev")
                .withEmail("rgg@mail.ru")
                .withGroup("Test2");
        Contacts before = app.contact().all();
        app.goTo().AddNewContact();
        app.contact().create(contactData);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(contactData.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));

    }

}
