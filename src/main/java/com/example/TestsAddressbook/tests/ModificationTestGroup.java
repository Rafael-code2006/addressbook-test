package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ModificationTestGroup extends TestBase{

    @Test
    public void testGroupModification(){
        GroupData groupData = new GroupData("Test2", "Test5", "Test7");
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().checkingGroup(groupData);
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 2);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().clearGroup();
        app.getGroupHelper().newGroup(groupData);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupsPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }

}
