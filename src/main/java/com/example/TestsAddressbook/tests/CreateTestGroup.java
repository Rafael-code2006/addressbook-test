package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateTestGroup extends TestBase {

    @DataProvider
    public Iterator<Object[]> validProvider(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {"test1", "header 1", "footer 1"});
        list.add(new Object[] {"test2", "header 2", "footer 2"});
        list.add(new Object[] {"test3", "header 3", "footer 3"});
        return list.iterator();
    }



    @Test(dataProvider = "validProvider")
    public void testGroupCreate(String name, String header, String footer) throws Exception {
        GroupData groupData = new GroupData()
                .withName(name)
                .withHeader(header)
                .withFooter(footer);
        app.goTo().groupPage();
        MySet<GroupData> before = app.group().all();
        app.group().createGroup(groupData);
        assertThat(app.group().count(), equalTo(before.size()+1));
        MySet<GroupData> after = app.group().all();
        assertThat(after, equalTo(before.withAdded(groupData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
