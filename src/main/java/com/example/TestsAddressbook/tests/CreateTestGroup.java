package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.generators.GroupDataGenerator;
import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateTestGroup extends TestBase {
    @DataProvider
    public Iterator<Object[]> validProvider() throws IOException {
        List<Object[]> list = new ArrayList<>();
        File file = new File("D:/Java/addressbook-web-test/src/test/resources/groups.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(";");
                if (split.length >= 3) {
                    list.add(new Object[]{
                            new GroupData()
                                    .withName(split[0])
                                    .withHeader(split[1])
                                    .withFooter(split[2])
                    });
                }
            }
        }
        return list.iterator();
    }




    @Test(dataProvider = "validProvider")
    public void testGroupCreate(GroupData groupDataNew) throws Exception {
        app.goTo().groupPage();
        MySet<GroupData> before = app.group().all();
        app.group().createGroup(groupDataNew);
        assertThat(app.group().count(), equalTo(before.size()+1));
        MySet<GroupData> after = app.group().all();
        assertThat(after, equalTo(before.withAdded(groupDataNew.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
