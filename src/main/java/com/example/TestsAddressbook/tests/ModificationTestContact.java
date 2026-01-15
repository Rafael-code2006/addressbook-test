package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import org.testng.annotations.Test;

public class ModificationTestContact extends TestBase{

    @Test
    private void test(){
        ContactData contactData = new ContactData("Artem", "Alekseev", "art@mail.ru", "Test2");
        app.getContactHelper().checkingContact(contactData);
        app.getContactHelper().selectContact(contactData);
        app.getContactHelper().goToEdit(contactData);
        app.getContactHelper().newContact(contactData, false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContacts();

    }
}
