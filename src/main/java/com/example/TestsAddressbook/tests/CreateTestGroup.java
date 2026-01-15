package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.annotations.*;



public class CreateTestGroup extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

      app.getNavigationHelper().goToGroups(); // Перейти к группам
      app.getGroupHelper().createGroup(new GroupData(
              "Test`s group",
              "test",
              "Test footer"));  // Ввод данных в поля новой группы
      app.getGroupHelper().returnToGroupsPage(); // Вернутся к группам
  }


}
