package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationTestGroup extends TestBase{

    @BeforeMethod()
    public void esurePreconditions(){
        GroupData groupData = new GroupData()
                .withName("Test1")
                .withHeader("testGroup")
                .withFooter("TestFooter");
        app.goTo().groupPage();
        app.group().checkingGroup(groupData);
    }

    @Test
    public void testGroupModification(){
        MySet<GroupData> before = app.db().groups();
        GroupData modifyGroup = before.iterator().next();
        GroupData newGroupData = new GroupData()
                .withId(modifyGroup.getId())
                .withName("Test1")
                .withHeader("testGroup")
                .withFooter("TestFooter");
        app.group().modify(newGroupData);
        assertThat(app.group().count(), equalTo(before.size()));
        MySet<GroupData> after = app.db().groups();
        assertThat(after, equalTo(before.without(modifyGroup).withAdded(newGroupData)));
    }

}
