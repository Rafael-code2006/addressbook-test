package com.example.TestsAddressbook;

import org.testng.annotations.*;



public class CreateTestGroup extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

      app.login(ApplicationManager.username, ApplicationManager.password);
      app.goToGroups();
      app.createGroup("Rafael`s group", "testGroup", "Test footer");
      app.returnToGroups();
  }


}
