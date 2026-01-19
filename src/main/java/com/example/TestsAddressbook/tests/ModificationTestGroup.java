package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

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
        List<GroupData> before = app.group().grouplist();
        int index = 0;
        GroupData newGroupData = new GroupData()
                .whithId(before.get(index).getId())
                .withName("Test1")
                .withHeader("testGroup")
                .withFooter("TestFooter");
        app.group().modifyGroup(index, newGroupData);
        List<GroupData> after = app.group().grouplist();


        before.remove(index);
        before.add(newGroupData);

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
