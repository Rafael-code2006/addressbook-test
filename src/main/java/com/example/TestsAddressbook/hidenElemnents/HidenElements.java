package com.example.TestsAddressbook.hidenElemnents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HidenElements extends HideElementsHelper {

    @Test
    public void testCheckboxes() {

        List<WebElement> elementsWeb = driver.findElements(By.xpath("//input[@name='selected[]']"));


        Assert.assertTrue(elementsWeb.size() > 0, "Не найдено ни одного чекбокса!");


        for (WebElement input : elementsWeb) {
            String id = input.getAttribute("id");
            String title = input.getAttribute("title");
            String name = title.substring(title.indexOf("(") + 1, title.indexOf(")"));
            String email = input.getAttribute("accept");
            System.out.println("Checkbox id=" + id + ", title=" + name + ", email=" + email);
        }
    }
}
