package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class DeleteTestGroup extends TestBase {


    @Test
  public void DeleteTestGroup() {
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().checkingGroup(new GroupData("Test1", "Test2", "Test3"));
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() -2);
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupsPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size()-1);
  }

}
