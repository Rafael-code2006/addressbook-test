package com.example.TestsAddressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    protected static final String username = "admin";
    protected static final String password = "secret";
    protected WebDriver driver;



    protected void login(String username, String password){
        driver.get("http://localhost/addressbook/group.php");
        driver.findElement(By.name("user")).click();
        driver.findElement(By.name("user")).clear();
        driver.findElement(By.name("user")).sendKeys(username);
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void goToGroups(){
        driver.findElement(By.linkText("groups")).click();
    }

    protected void selectGroup(){
        driver.findElement(By.name("selected[]")).click();
    }

    protected void deleteGroup(){
        driver.findElement(By.name("delete")).click();
    }

    protected void returnToGroups(){
        driver.findElement(By.linkText("group page")).click();
    }

    protected void createGroup(final String title, final String header, final String footer){
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).clear();
        driver.findElement(By.name("group_name")).sendKeys(title);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).clear();
        driver.findElement(By.name("group_header")).sendKeys(header);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).clear();
        driver.findElement(By.name("group_footer")).sendKeys(footer);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.name("submit")).click();
    }

    protected void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ra_gimadeyev\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30 , TimeUnit.SECONDS);
    }

    protected void stop() {
        if(driver != null){
            driver.quit();
        }
    }

    private boolean isElementPresent(By by) {
      try {
        driver.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    private boolean isAlertPresent() {
      try {
        driver.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }
}
