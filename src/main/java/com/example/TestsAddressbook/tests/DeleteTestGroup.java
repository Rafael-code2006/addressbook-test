package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

public class DeleteTestGroup extends TestBase {


    @Test
  public void DeleteTestGroup() {
        app.getNavigationHelper().goToGroups();
        int beforeGroups = app.getGroupHelper().counterGroups();
        app.getGroupHelper().checkingGroup(new GroupData("Test1", "Test2", "Test3"));
        app.getGroupHelper().selectGroup(beforeGroups -2);
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupsPage();
        int afterGroups = app.getGroupHelper().counterGroups();
        System.out.println(afterGroups);
        Assert.assertEquals(afterGroups, beforeGroups-1);
  }

}
