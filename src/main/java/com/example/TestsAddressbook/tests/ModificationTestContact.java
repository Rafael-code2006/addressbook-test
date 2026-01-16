package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ModificationTestContact extends TestBase{

    @Test
    private void test(){
        for(int i=0; i<2; i++){
            tests();
        }

    }

    private void tests() {
        ContactData contactData = new ContactData("Artem", "Alekseev", "art@mail.ru", "Test2");
        List<ContactData>before = app.getContactHelper().getContactList();
        ContactData contactData1 = new ContactData(contactData.getId(), "Artem", "Alekseev", "art@mail.ru", "Test2");
        app.getContactHelper().checkingContact(contactData);
        app.getContactHelper().selectContact(before.get(0));
        app.getContactHelper().goToEdit(before.get(0));
        app.getContactHelper().newContact(contactData, false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContacts();
        List<ContactData>after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
