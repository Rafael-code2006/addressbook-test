package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Scanner;

public class CreateTestContact extends TestBase {

    private Scanner sc = new Scanner(System.in);

    @Test
    private void test(){

        app.getNavigationHelper().goToAddNewContacts();
        app.getContactHelper().newContact(new ContactData("Pavel", "Barancev", "rgg@mail.ru"));
        app.getContactHelper().selectedGroup(3);
        app.getContactHelper().saveContact();
        app.getContactHelper().returnToContacts();
    }
}
