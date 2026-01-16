package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class CreateTestGroup extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

      app.getNavigationHelper().goToGroups();
      List<GroupData> before = app.getGroupHelper().getGroupList();
      app.getGroupHelper().createGroup(new GroupData(
              "Test2",
              "testGroup",
              "Test footer"));// Ввод данных в поля новой группы
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size()+1);
  }




}
