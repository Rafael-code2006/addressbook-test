package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.Groups;
import org.testng.annotations.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletedTestGroup extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){
        GroupData groupData = new GroupData()
                .withName("Test1")
                .withHeader("testGroup")
                .withFooter("TestFooter");
        app.goTo().groupPage();
        app.group().checkingGroup(groupData);
    }

    @Test
  public void DeletedTestGroup() {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().all();
        assertThat(app.group().count(), equalTo(before.size()-1));
        assertThat(after, equalTo(before.without(deletedGroup)));


  }
}
