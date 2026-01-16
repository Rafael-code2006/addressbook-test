package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ModificationTestGroup extends TestBase{

    @Test
    public void testGroupModification(){
        for(int i=0; i<15; i++){
            tests();
        }

    }

    private void tests() {
        GroupData groupData = new GroupData("Test2", "Test5", "Test7");
        app.getNavigationHelper().goToGroups();
        app.getGroupHelper().checkingGroup(groupData);
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData newGroupData = new GroupData(before.get(before.size()-1).getId(), "Test2", "Test5", "Test7");
        app.getGroupHelper().selectGroup(0);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().newGroup(newGroupData);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupsPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(before.size() -1);
        before.add(newGroupData);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
