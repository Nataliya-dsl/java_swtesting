package ru.stqa.adressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.adressbook.model.ContactDetails;
import ru.stqa.adressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDetailsGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDetailsGenerator generator = new ContactDetailsGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }

        generator.run();
    }

    private void run() throws IOException {
        List<ContactDetails> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }

    }


    private void saveAsJson(List<ContactDetails> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactDetails> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactDetails.class);
        xstream.allowTypes(new Class[]{ContactDetails.class});
        xstream.processAnnotations(ContactDetails.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();

    }

    private void saveAsCsv(List<ContactDetails> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactDetails contact : contacts) {
            writer.write(String.format("%s%s%s\n", contact.getFirstname(), contact.getLastname(), contact.getNickname(),
                contact.getAddress(), contact.getMobile(), contact.getWorkPhone()));
        }
        writer.close();

    }

    private List<ContactDetails> generateContacts(int count) {
        List<ContactDetails> contacts = new ArrayList<ContactDetails>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactDetails().withFirstname(String.format("FirstName %s;", i)).withLastname(String.format("LastName\n%s;", i))
                .withNickname(String.format("testuser\n%s;", i)).withAddress(String.format("Address\n%s;", i))
                    .withMobile(String.format("1111\n%s;", i)).withWorkphone(String.format("2222\n%s;", i)));
        }
        return contacts;
    }
}

