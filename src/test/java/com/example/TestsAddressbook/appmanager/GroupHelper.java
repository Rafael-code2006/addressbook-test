package com.example.TestsAddressbook.appmanager;
import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void selectGroupById(int id){
        waitFindElement(By.cssSelector("input[value='"+ id +"']")).click();
    } // Выбор группы

    public void deleteGroup(){
        click(By.name("delete"));
    } // Удаление группы

    public void returnToGroupsPage(){
        click(By.linkText("group page"));
    } // Возврат к списку групп

    public void createGroup(GroupData groupData){
        newGroup(groupData);
        submitGroupCreation();
        groupsCache = null;
        returnToGroupsPage();
        System.out.println("createGroup");
    } // Путь создания группу

    private void goToNewGroup() {
        click(By.xpath("//input[@value=\"New group\"]"));
    }

    public void modify(GroupData newGroup) {
        selectGroupById(newGroup.getId());
        editGroupModification();
        newGroup(newGroup);
        updateGroupModification();
        groupsCache = null;
        returnToGroupsPage();
    }

    public void newGroup(GroupData groupData) {
        type("group_name", groupData.getName());
        type("group_header", groupData.getHeader());
        type("group_footer", groupData.getFooter());
    } // Создание группы

    public void checkingGroup(GroupData groupData) {
        if(!isElementPresent(By.xpath("//*[@name=\"selected[]\"]"))){
            goToNewGroup();
            createGroup(groupData);
        }
    } // Проверка наличия групп

    private void newGroupPage() {
        click(By.name("new"));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void editGroupModification() {
        click(By.name("edit"));
    }

    public void updateGroupModification() {
    click(By.name("update"));
    }

    public void delete(GroupData groupData) {
        selectGroupById(groupData.getId());
        deleteGroup();
        groupsCache = null;
        returnToGroupsPage();
    }

    private MySet<GroupData> groupsCache;

    public MySet<GroupData> all() {
        if(groupsCache != null){
            return new MySet<GroupData>(groupsCache);
        }
        groupsCache = new MySet<GroupData>();
        /*if(!isElementPresent(By.cssSelector("span.group"))){
            createGroup(new GroupData(null, "Test1", null, null));
        }
         */
        List<WebElement> elementsWeb = waitFindElements(By.cssSelector("span.group"));
         for(WebElement x : elementsWeb){
            int id = Integer.parseInt(x.findElement(By.tagName("input")).getAttribute("value"));
            String name = x.getText();
            GroupData groupData = new GroupData()
                    .withId(id)
                    .withName(name)
                    .withHeader(null)
                    .withFooter(null);
             groupsCache.add(groupData);
        }

        return new MySet<GroupData>(groupsCache);
    }

    public int count() {
    return waitFindElements(By.name("selected[]")).size();
    }
}
