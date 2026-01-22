package com.example.TestsAddressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.example.TestsAddressbook.model.ContactData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
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
        List<ContactData> contact = generateContacts(count);
        if(format.equals("csv")) {
            saveAsCsv(contact, new File(file));
        }
        else if(format.equals("xml")){
            saveAsXml(contact, new File(file));
        }
        else if(format.equals("json")){
            saveAsJson(contact, new File(file));
        }
        else {
            System.out.println("Unrecognized format");
        }
    }

    private void saveAsJson(List<ContactData> contact, File file) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contact);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void saveAsXml(List<ContactData> contact, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("contact",ContactData.class);
        String xml = xStream.toXML(contact);

        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void saveAsCsv(List<ContactData> contact, File file) throws IOException {


        try(Writer writer = new FileWriter(file)) {
            for (ContactData x : contact) {
                writer.write(String.format("%s;%s;%s;%s%n", x.getFirstname(), x.getLastname(), x.getEmail(), x.getMobile()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for(int i=0; i< count; i++){
            contacts.add(new ContactData()
                    .withFirstName(String.format("FirstName %s", i))
                    .withLastname(String.format("LastName %s", i))
                    .withEmail(String.format("Email %s", i))
                    .withMobile(String.format("Mobile %s", i)));
        }
        return contacts;
    }
}
