package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import org.testng.annotations.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateTestGroup extends TestBase {

    @Test(dataProvider = "validProviderFromJsonToGroup", dataProviderClass = TestBase.class)
    public void testGroupCreate(GroupData groupDataNew) throws Exception {
        app.goTo().groupPage();
        MySet<GroupData> before = app.db().groups();
        app.group().createGroup(groupDataNew);
        assertThat(app.group().count(), equalTo(before.size()+1));
        MySet<GroupData> after = app.db().groups();
        assertThat(after, equalTo(before.withAdded(groupDataNew.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
