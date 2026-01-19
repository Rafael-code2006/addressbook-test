package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
        Set<GroupData> before = app.group().all();
        GroupData modifyGroup = before.iterator().next();
        GroupData newGroupData = new GroupData()
                .withId(modifyGroup.getId())
                .withName("Test1")
                .withHeader("testGroup")
                .withFooter("TestFooter");
        app.group().modifyGroup(newGroupData);
        Set<GroupData> after = app.group().all();


        before.remove(modifyGroup);
        before.add(newGroupData);
        Assert.assertEquals(before, after);
    }

}
