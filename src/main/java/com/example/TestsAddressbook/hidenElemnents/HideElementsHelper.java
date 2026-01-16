package com.example.TestsAddressbook.hidenElemnents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class HideElementsHelper {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Указываем путь к драйверу
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ra_gimadeyev\\chromedriver.exe");

        // Создаём драйвер
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Открываем страницу и логинимся
        driver.get("http://localhost/addressbook");
        type("user", "admin");
        type("pass", "secret");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void type(String fieldName, String text) {
        driver.findElement(By.name(fieldName)).clear();
        driver.findElement(By.name(fieldName)).sendKeys(text);
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }
}
