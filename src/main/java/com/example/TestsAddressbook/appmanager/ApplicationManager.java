package com.example.TestsAddressbook.appmanager;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.GroupData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver driver;
    public static final String username = "admin";
    public static final String password = "secret";
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private String browser;

    public ApplicationManager(String browser) throws IOException {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if(Objects.equals(browser, BrowserType.CHROME)){
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\ra_gimadeyev\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if(Objects.equals(browser, BrowserType.FIREFOX)){
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\ra_gimadeyev\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\ra_gimadeyev\\msedgedriver.exe");
             driver = new EdgeDriver();
        }
        groupHelper = new GroupHelper(driver);
        contactHelper = new ContactHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        driver.get(properties.getProperty("web.baseUrl"));
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        if(driver != null){
           driver.quit();
        }
    }

    public boolean isElementPresent(By by) {
      try {
        driver.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }


    public ContactHelper contact() {
        return contactHelper;
    }

    public GroupHelper group() {
        return groupHelper;
}

    public NavigationHelper goTo() {
        return navigationHelper;
    }


    public static Iterator<Object[]> jsonParserGroup(File file) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String json = "";
            while ((line = reader.readLine()) != null) {
                json += line;
            }

            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream()
                    .map((g) -> new Object[]{g})
                    .collect(Collectors.toList()).iterator();
        }
    }

    public static Iterator<Object[]> jsonParserContact(File file) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String json = "";
            while ((line = reader.readLine()) != null) {
                json += line;
            }

            Gson gson = new Gson();
            List<ContactData> contact = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contact.stream()
                    .map((g) -> new Object[]{g})
                    .collect(Collectors.toList()).iterator();
        }
    }

    public static Iterator<Object[]> xmlParserGroup(File file) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String xml = "";
            while ((line = reader.readLine()) != null) {
                xml += line;
            }

            XStream xStream = new XStream();
            xStream.alias("group", GroupData.class);
            @SuppressWarnings("unchecked")
            List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);

            return groups.stream()
                    .map((g) -> new Object[]{g})
                    .collect(Collectors.toList()).iterator();
        }
    }

    public static Iterator<Object[]> xmlParserContact(File file) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String xml = "";
            while ((line = reader.readLine()) != null) {
                xml += line;
            }

            XStream xStream = new XStream();
            xStream.alias("group", ContactData.class);
            @SuppressWarnings("unchecked")
            List<ContactData> contact = (List<ContactData>) xStream.fromXML(xml);

            return contact.stream()
                    .map((g) -> new Object[]{g})
                    .collect(Collectors.toList()).iterator();
        }
    }




    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
