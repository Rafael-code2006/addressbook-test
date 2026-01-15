package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import org.testng.annotations.Test;


public class CreateTestContact extends TestBase {


    @Test
    private void test(){

        app.getNavigationHelper().goToAddNewContacts();
        app.getContactHelper().newContact(new ContactData("Pavel", "Barancev", "rgg@mail.ru", 3), false);
        app.getContactHelper().selectedGroup(3);
        app.getContactHelper().saveContact();
        app.getContactHelper().returnToContacts();
    }
}
