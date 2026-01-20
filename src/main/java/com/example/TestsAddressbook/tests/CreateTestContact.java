package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.MySet;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateTestContact extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if(!app.isElementPresent(By.xpath("//a[text()=\"Last name\"]"))){
            app.contact().returnToHome();
        }
    }

    @Test
    private void test(){
        File photo = new File("src/test/resources/photo.png");
        ContactData contactData = new ContactData()
                .withFirstName("Photo")
                .withLastname("Photeyev")
                .withFile(photo)
                .withEmail("rgg@mail.ru")
                .withGroup("Test2");

        MySet<ContactData> before = app.contact().all();
        app.contact().create(contactData);
        MySet<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(contactData.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));

    }

}
