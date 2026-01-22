package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneTestContact extends TestBase{

    @BeforeMethod
    public void before(){
        if(!app.isElementPresent(By.xpath("//a[text()=\"Last name\"]"))){
            app.contact().returnToHome();
        }
    }

    @Test
    public void test() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);


        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));


    }


        private String mergePhones(ContactData contact){
           return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
                    .stream().filter((s) -> ! s.equals(""))
                   .map(PhoneTestContact::cleanedPhones)
                   .collect(Collectors.joining("\n"));
        }

        private static String cleanedPhones(String phone){
            return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
        }



}
