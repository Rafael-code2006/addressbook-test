package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;



public class CreateTestGroup extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

      int before = app.getGroupHelper().getGroupCount();

      app.getNavigationHelper().goToGroups(); // Перейти к группам
      app.getGroupHelper().createGroup(new GroupData(
              "Rafael`s group",
              "testGroup",
              "Test footer"));// Ввод данных в поля новой группы
      int after = app.getGroupHelper().getGroupCount();
      Assert.assertEquals(after, 11);
  }


}
