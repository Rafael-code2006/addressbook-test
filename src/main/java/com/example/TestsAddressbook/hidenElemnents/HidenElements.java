package com.example.TestsAddressbook.hidenElemnents;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.MySet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HidenElements extends HideElementsHelper {

    @Test
    public void testCheckboxes() {
        List<WebElement> elements = driver.findElements(By.xpath("//td"));


    }
}
