package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.annotations.*;

public class DeleteTestGroup extends TestBase {


    @Test
  public void DeleteTestGroup() {
        app.getNavigationHelper().goToGroups();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Alex`s group", "test2", "footer"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupsPage();
  }

}
