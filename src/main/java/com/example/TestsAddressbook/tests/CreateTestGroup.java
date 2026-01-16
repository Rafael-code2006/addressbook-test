package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.GroupData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class CreateTestGroup extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

      GroupData groupData = new GroupData("Test1", "testGroup", "Test footer");
      app.getNavigationHelper().goToGroups();
      List<GroupData> before = app.getGroupHelper().getGroupList();
      app.getGroupHelper().createGroup(groupData);
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size()+1);

      before.add(groupData);
      for(GroupData x : before){
          System.out.println("before: " + x.getName());
      }
      for(GroupData x : after){
          System.out.println("after: " + x.getName());
      }
      Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before,after);

  }




}
