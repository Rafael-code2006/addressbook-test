package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.appmanager.ApplicationManager;
import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Predicates.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static final ApplicationManager app;
    private static final Logger log = LoggerFactory.getLogger(TestBase.class);

    static {
        try {
            app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        log.info(String.format("Start %s method, with parameters %s", m.getName(), Arrays.asList(p)));
    }

    @AfterMethod
    public void logTestStop(Method m, Object[] p){
        log.info(String.format("Stop %s method, with parameters %s", m.getName(), Arrays.asList(p)));
    }


    @DataProvider
    public Iterator<Object[]> validProviderFromJsonToGroup() throws IOException {
        File file = new File("D:/Java/addressbook-web-test/src/test/resources/groups.json");
        return ApplicationManager.jsonParserGroup(file);
    }


    @DataProvider
    public Iterator<Object[]> validProviderFromXmlToGroup() throws IOException {
        File file = new File("D:/Java/addressbook-web-test/src/test/resources/groups.xml");
        return ApplicationManager.xmlParserGroup(file);
    }


    @DataProvider
    public Iterator<Object[]> validProviderFromJsonToContact() throws IOException {
        File file = new File("D:/Java/addressbook-web-test/src/test/resources/contacts.json");
        return ApplicationManager.jsonParserContact(file);
    }


    @DataProvider
    public Iterator<Object[]> validProviderFromXmlToContacts() throws IOException {
        File file = new File("D:/Java/addressbook-web-test/src/test/resources/contacts.xml");
        return ApplicationManager.xmlParserContact(file);
    }


    public void verifyGroupListInUI() {
        if(Boolean.getBoolean("verifyUI")) {
        MySet<GroupData> DBgroups = app.db().groups();
        MySet<GroupData> UIgroups = app.group().all();

        assertThat(UIgroups, CoreMatchers.equalTo(DBgroups));
        } else {
            System.out.println("Не сработало(");
        }
    }

    public void verifyContactListInUI() {

        if(Boolean.getBoolean("verifyUI")) {
            MySet<ContactData> DBcontact = app.db().contacts();
            MySet<ContactData> UIcontact = app.contact().all();

            assertThat(UIcontact, CoreMatchers.equalTo(DBcontact));
        }
    }

}
