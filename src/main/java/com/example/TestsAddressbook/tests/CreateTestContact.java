package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;


public class CreateTestContact extends TestBase {


    @Test(enabled = false)
    private void test(){
        ContactData contactData = new ContactData("Rafael", "Gimadeyev", "rgg@mail.ru", "Test2");
        List<ContactData> before = app.contact().getContactList();
        app.goTo().AddNewContact();
        app.contact().createContact(contactData);
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size()+1);


        contactData.setFirstName(contactData.getFirstname() +" "+ contactData.getLastname());
        before.add(contactData);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
