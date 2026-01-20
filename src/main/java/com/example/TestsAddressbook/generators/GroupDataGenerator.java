package com.example.TestsAddressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.example.TestsAddressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

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
        save(group, new File(file));
    }

    private void save(List<GroupData> group, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());

            Writer writer = new FileWriter(file);
            for(GroupData x : group){
                writer.write(String.format("%s;%s;%s%n", x.getName(), x.getHeader(), x.getFooter()));
            }
            writer.close();
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
