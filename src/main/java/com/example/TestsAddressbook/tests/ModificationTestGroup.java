package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationTestGroup extends TestBase{

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
    public void testGroupModification(){
       Groups before = app.group().all();
        GroupData modifyGroup = before.iterator().next();
        GroupData newGroupData = new GroupData()
                .withId(modifyGroup.getId())
                .withName("Test1")
                .withHeader("testGroup")
                .withFooter("TestFooter");
        app.group().modify(newGroupData);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(modifyGroup).withAdded(newGroupData)));
    }

}
