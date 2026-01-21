package com.example.TestsAddressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.example.TestsAddressbook.model.GroupData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(generator)
                .build();
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }


    private void run( ) throws IOException {
        List<GroupData> group = generateGroups(count);
        if(format.equals("csv")) {
            saveAsCsv(group, new File(file));
        }
        else if(format.equals("xml")){
            saveAsXml(group, new File(file));
        }
        else if(format.equals("json")){
            saveAsJson(group, new File(file));
        }
        else {
                System.out.println("Unrecognized format");
        }
    }

    private void saveAsJson(List<GroupData> group, File file) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(group);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void saveAsXml(List<GroupData> group, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("group",GroupData.class);
        String xml = xStream.toXML(group);

        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void saveAsCsv(List<GroupData> group, File file) throws IOException {


            try(Writer writer = new FileWriter(file)) {
                for (GroupData x : group) {
                    writer.write(String.format("%s;%s;%s%n", x.getName(), x.getHeader(), x.getFooter()));
                }
            }
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for(int i=0; i< count; i++){
            groups.add(new GroupData().withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i))
                    .withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}
