package com.example.TestsAddressbook.tests;
import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.MySet;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateTestContact extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if(!app.isElementPresent(By.xpath("//a[text()=\"Last name\"]"))){
            app.contact().returnToHome();
        }
    }

    @Test(dataProvider = "validProviderFromJsonToContact", dataProviderClass = TestBase.class)
    public void test(ContactData contactData){
        MySet<ContactData> before = app.db().contacts();
        app.contact().create(contactData);
        MySet<ContactData> after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(contactData.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));

    }

}
