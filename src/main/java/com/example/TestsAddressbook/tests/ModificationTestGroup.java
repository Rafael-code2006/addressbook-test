package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.annotations.Test;

public class ModificationTestGroup extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().clearGroup();
        app.getGroupHelper().newGroup(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupsPage();
    }
}
