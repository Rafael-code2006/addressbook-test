package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import org.testng.annotations.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateTestGroup extends TestBase {

    @Test
    public void testGroupCreate() throws Exception {

        app.goTo().groupPage();
        MySet<GroupData> before = app.group().all();
        GroupData groupData = new GroupData()
                .withName("Test2");
        app.group().createGroup(groupData);
        assertThat(app.group().count(), equalTo(before.size()+1));
        MySet<GroupData> after = app.group().all();
        assertThat(after, equalTo(before.withAdded(groupData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
