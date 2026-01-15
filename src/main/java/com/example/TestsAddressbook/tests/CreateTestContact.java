package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import org.testng.annotations.Test;


public class CreateTestContact extends TestBase {


    @Test
    private void test(){
        ContactData contactData = new ContactData("Rafael", "Gimadeyev", "rgg@mail.ru", "Test2");
        app.getNavigationHelper().goToAddNewContacts();
        app.getContactHelper().createContact(contactData);
    }

}
