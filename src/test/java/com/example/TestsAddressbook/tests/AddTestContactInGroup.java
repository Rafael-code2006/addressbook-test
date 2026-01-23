package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.function.DoubleToIntFunction;

import static org.hamcrest.MatcherAssert.assertThat;

public class AddTestContactInGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(!app.isElementPresent(By.xpath("//a[text()=\"Last name\"]"))){
            app.contact().returnToHome();
        }
    }


    @Test
    public void test(){
        checkingGroup();
        MySet<GroupData> groups = app.db().groups();
        checkingContact(groups);
        MySet<ContactData> before = app.db().contactsInGroups();
        ContactData select = before.iterator().next();



        ContactData modifyContact = new ContactData()
                .withId(select.getId())
                .withFirstName("TestIterators")
                .withLastname("TestIteator")
                .withEmail("art@mail.ru")
                .inGroup(groups.iterator().next());

        app.contact().addInGroup(modifyContact);

        MySet<ContactData> after = app.db().contactsInGroups();
        assertThat(after.size(), CoreMatchers.equalTo(before.size()));
    }

    private void checkingContact(MySet<GroupData> groups) {
        if(!app.isElementPresent(By.name("selected[]"))){
            app.contact().create(new ContactData().withFirstName("Rafael").withLastname("Gimadeyev").withMobile("111").inGroup(groups.iterator().next()));
        }
    }

    private static void checkingGroup() {
        if(!app.isElementPresent(By.xpath("//select[@name=\"group\"]/option[3]"))) {
            app.goTo().groupPage();
            GroupData group = new GroupData().withName("Test1").withHeader("Header1").withFooter("Footer1");
            app.group().checkingGroup(group);
            app.contact().returnToHome();
        }
    }

}
