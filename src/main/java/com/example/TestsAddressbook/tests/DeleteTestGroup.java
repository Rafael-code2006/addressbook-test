package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.annotations.*;

public class DeleteTestGroup extends TestBase {


    @Test
  public void DeleteTestGroup() {
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().checkingGroup(new GroupData("Test1", "Test2", "Test3"));
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupsPage();
  }

}
