package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import org.testng.annotations.Test;

public class ModificationTestContact extends TestBase{

    @Test
    private void test(){
        app.getContactHelper().selectContact(1);
        app.getContactHelper().goToEdit(1);
        app.getContactHelper().newContact(new ContactData("Artem", "Alekseev", "art@mail.ru", 2), false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContacts();

    }
}
