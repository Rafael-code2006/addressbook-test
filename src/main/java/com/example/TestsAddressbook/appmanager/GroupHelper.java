package com.example.TestsAddressbook.appmanager;
import com.example.TestsAddressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver driver) {
        super(driver);
    }



    public void selectGroup(int index){
        timerSecond(3);
        waitFindElements(By.name("selected[]")).get(index).click();
    } // Выбор группы

    public void deleteGroup(){
        timerSecond(30);
        click(By.name("delete"));
    } // Удаление группы

    public void returnToGroupsPage(){
        click(By.linkText("group page"));
    } // Возврат к списку групп

    public void createGroup(GroupData groupData){
        timerSecond(50);
        newGroupCreation();
        newGroup(groupData);
        submitGroupCreation();
        returnToGroupsPage();
        System.out.println("createGroup");
    } // Путь создания группу

    public void modifyGroup(int index, GroupData newGroupData) {
        selectGroup(index);
        editGroupModification();
        newGroup(newGroupData);
        updateGroupModification();
        returnToGroupsPage();
    }


    public void newGroup(GroupData groupData) {
        type("group_name", groupData.getName());
        timerSecond(20);
        type("group_header", groupData.getHeader());
        timerSecond(20);
        type("group_footer", groupData.getFooter());
        timerSecond(20);
    } // Создание группы

    public void checkingGroup(GroupData groupData) {
        if(!isElementPresent(By.className("group"))){
            createGroup(groupData);
        }
    } // Проверка наличия групп

    public void clearGroup(){
        clear(By.name("group_name"));
        timerSecond(20);
        clear(By.name("group_header"));
        timerSecond(20);
        clear(By.name("group_footer"));
        timerSecond(20);
        System.out.println("Clear group");
    } // Очистить информацию о группе

    private void newGroupCreation() {
        click(By.name("new"));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void editGroupModification() {
        timerSecond(3);
        click(By.name("edit"));
    }

    public void updateGroupModification() {
    click(By.name("update"));
    }


    public void delete(List<GroupData> before) {
       selectGroup(before.size() -1);
       deleteGroup();
       returnToGroupsPage();
    }


    public List<GroupData> grouplist() {
        List<GroupData>elements = new ArrayList<>();
        /*if(!isElementPresent(By.cssSelector("span.group"))){
            createGroup(new GroupData(null, "Test1", null, null));
        }
         */
        List<WebElement> elementsWeb = waitFindElements(By.cssSelector("span.group"));
        for(WebElement x : elementsWeb){
            int id = Integer.parseInt(x.findElement(By.tagName("input")).getAttribute("value"));
            String name = x.getText();
            GroupData groupData = new GroupData()
                    .whithId(id)
                    .withName(name)
                    .withHeader(null)
                    .withFooter(null);
            elements.add(groupData);
        }

        return elements;
    }
}
