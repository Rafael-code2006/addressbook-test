package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.annotations.Test;

public class ModificationTestGroup extends TestBase{

    @Test
    public void testGroupModification(){
        GroupData groupData = new GroupData("Test2", "Test5", "Test7");
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().checkingGroup(groupData);
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().clearGroup();
        app.getGroupHelper().newGroup(groupData);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupsPage();
    }

}
