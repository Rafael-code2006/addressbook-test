package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class DeleteTestGroup extends TestBase {


    @BeforeMethod
    public void esurePreconditions(){
        GroupData groupData = new GroupData()
                .withName("Test1")
                .withHeader("testGroup")
                .withFooter("TestFooter");
        app.goTo().groupPage();
        app.group().checkingGroup(groupData);
    }

    @Test
  public void DeleteTestGroup() {
        List<GroupData> before = app.group().grouplist();
        app.group().delete(before);
        List<GroupData> after = app.group().grouplist();
        Assert.assertEquals(after.size(),before.size()-1);
            before.remove(before.size() -1);
            Assert.assertEquals(before, after);


  }
}
