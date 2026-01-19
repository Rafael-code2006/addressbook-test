package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Set;


public class CreateTestGroup extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

      GroupData groupData = new GroupData()
              .withName("Test1")
              .withHeader("testGroup")
              .withFooter("TestFooter");
      app.goTo().groupPage();
      Set<GroupData> before = app.group().all();
      app.group().createGroup(groupData);
      Set<GroupData> after = app.group().all();
      Assert.assertEquals(after.size(), before.size()+1);

      groupData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
      before.add(groupData);
      Assert.assertEquals(before,after);

  }




}
