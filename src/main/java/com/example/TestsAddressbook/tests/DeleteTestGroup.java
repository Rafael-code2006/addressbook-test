package com.example.TestsAddressbook.tests;

import org.testng.annotations.*;

public class DeleteTestGroup extends TestBase {


    @Test
  public void DeleteTestGroup() {
        for(int i=0; i<3; i++){
            test();
        }

    }

    private void test() {
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupsPage();
    }

}
