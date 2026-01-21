package com.example.TestsAddressbook.tests;

import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateTestGroup extends TestBase {
    @DataProvider
    public Iterator<Object[]> validProviderFromXml() throws IOException {
        File file = new File("D:/Java/addressbook-web-test/src/test/resources/groups.xml");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String xml = "";
            while ((line = reader.readLine()) != null) {
                xml += line;
            }

            XStream xStream = new XStream();
            xStream.alias("group", GroupData.class);
            @SuppressWarnings("unchecked")
            List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);

            return groups.stream()
                    .map((g) -> new Object[]{g})
                    .collect(Collectors.toList()).iterator();
        }
    }


    @DataProvider
    public Iterator<Object[]> validProviderFromJson() throws IOException {
        File file = new File("D:/Java/addressbook-web-test/src/test/resources/groups.json");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String json = "";
            while ((line = reader.readLine()) != null) {
                json += line;
            }

            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {}.getType());
            return groups.stream()
                    .map((g) -> new Object[]{g})
                    .collect(Collectors.toList()).iterator();
        }
    }




    @Test(dataProvider = "validProviderFromJson")
    public void testGroupCreate(GroupData groupDataNew) throws Exception {
        app.goTo().groupPage();
        MySet<GroupData> before = app.group().all();
        app.group().createGroup(groupDataNew);
        assertThat(app.group().count(), equalTo(before.size()+1));
        MySet<GroupData> after = app.group().all();
        assertThat(after, equalTo(before.withAdded(groupDataNew.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
