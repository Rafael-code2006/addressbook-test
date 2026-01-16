package com.example.TestsAddressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Wait;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private WebDriver driver;
    public static final String username = "admin";
    public static final String password = "secret";
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if(Objects.equals(browser, BrowserType.CHROME)){
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\ra_gimadeyev\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if(Objects.equals(browser, BrowserType.FIREFOX)){
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\ra_gimadeyev\\geckodriver.exe");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\ra_gimadeyev\\msedgedriver.exe");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
             driver = new EdgeDriver();
        }
        groupHelper = new GroupHelper(driver);
        contactHelper = new ContactHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        sessionHelper.login(username, password);
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


    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
}

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }



    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
