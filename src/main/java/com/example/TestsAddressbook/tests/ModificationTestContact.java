package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import org.testng.annotations.Test;

public class ModificationTestContact extends TestBase{

    @Test
    private void test(){
        ContactData contactData = new ContactData("Artem", "Alekseev", "art@mail.ru", "Rafael`s group");
        app.getContactHelper().checkingContact(contactData);
        app.getContactHelper().selectContact(14);
        app.getContactHelper().goToEdit(14);
        app.getContactHelper().newContact(contactData, false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContacts();

    }
}
