package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;



public class CreateTestGroup extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

      app.getNavigationHelper().goToGroups();
      int beforeGroups = app.getGroupHelper().counterGroups();
      System.out.println(beforeGroups);// Перейти к группам
      app.getGroupHelper().createGroup(new GroupData(
              "Test2",
              "testGroup",
              "Test footer"));// Ввод данных в поля новой группы
      int afterGroups = app.getGroupHelper().counterGroups();
      System.out.println(afterGroups);
      Assert.assertEquals(afterGroups, beforeGroups+1);
  }




}
