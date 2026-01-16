package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ModificationTestGroup extends TestBase{

    @Test
    public void testGroupModification(){
        GroupData groupData = new GroupData("Test2", "Test5", "Test7");
        app.getNavigationHelper().goToGroups();
        int beforeGroups = app.getGroupHelper().counterGroups();
        app.getGroupHelper().checkingGroup(groupData);
        app.getGroupHelper().selectGroup(beforeGroups - 2);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().clearGroup();
        app.getGroupHelper().newGroup(groupData);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupsPage();
        int afterGroups = app.getGroupHelper().counterGroups();
        System.out.println(afterGroups);
        Assert.assertEquals(afterGroups, beforeGroups);
    }

}
