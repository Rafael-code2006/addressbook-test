package com.example.TestsAddressbook.appmanager;

import com.example.TestsAddressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void selectGroup(){
        click(By.name("selected[]"));
    }

    public void deleteGroup(){
        click(By.name("delete"));
    }

    public void returnToGroupsPage(){
        click(By.linkText("group page"));
    }

    public void createGroup(GroupData groupData){
        initGroupCreation();
        newGroup(groupData);
        submitGroupCreation();
        System.out.println("createGroup");
    }

    public void newGroup(GroupData groupData) {
        type("group_name", groupData.getName());
        timerSecond(20);
        type("group_header", groupData.getHeader());
        timerSecond(20);
        type("group_footer", groupData.getFooter());
        timerSecond(20);
    }


    public void clearGroup(){
        clear(By.name("group_name"));
        timerSecond(20);
        clear(By.name("group_header"));
        timerSecond(20);
        clear(By.name("group_footer"));
        timerSecond(20);
        System.out.println("Clear group");
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
    click(By.name("update"));
    }
}
