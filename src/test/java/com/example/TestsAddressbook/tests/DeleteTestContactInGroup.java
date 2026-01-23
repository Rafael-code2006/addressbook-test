package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.function.Consumer;

public class DeleteTestContactInGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(!app.isElementPresent(By.xpath("//a[text()=\"Last name\"]"))){
            app.contact().returnToHome();
        }
    }


    @Test
    public void test(){
        if(!app.isElementPresent(By.xpath("//select[@name=\"group\"]/option[3]"))) {
            app.goTo().groupPage();
            GroupData group = new GroupData().withName("Test1").withHeader("Header1").withFooter("Footer1");
            app.group().checkingGroup(group);
            app.contact().returnToHome();
        }
        MySet<ContactData> before = app.db().contacts();
        ContactData select = before.iterator().next();

        MySet<GroupData> groups = app.db().groups();


            ContactData modifyContact = new ContactData()
                    .withId(select.getId())
                    .withFirstName("TestIterators")
                    .withLastname("TestIteator")
                    .withEmail("art@mail.ru")
                    .inGroup(groups.iterator().next());

            app.contact().DeleteInGroup(modifyContact);
}
}
