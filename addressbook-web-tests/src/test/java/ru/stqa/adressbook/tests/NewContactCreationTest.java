package ru.stqa.adressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.adressbook.model.ContactDetails;
import ru.stqa.adressbook.model.Contacts;
import ru.stqa.adressbook.model.GroupData;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NewContactCreationTest extends TestBase {

    public static final String TEST_GROUP = "test1";

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        Optional<GroupData> group = app.group().all().stream()
                .filter(g -> TEST_GROUP.equals(g.getName()))
                .findFirst();

        if (group.isEmpty())
            app.group().create(new GroupData().withName(TEST_GROUP));

        app.goTo().homePage();
    }
    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/java/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactDetails.class);
        xstream.allowTypes(new Class[]{ContactDetails.class});
        xstream.processAnnotations(ContactDetails.class);
        List<ContactDetails> contacts = (List<ContactDetails>) xstream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
    @DataProvider(name = "validContactsFromJson")
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/java/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        //Gson gson = new Gson();
        List<ContactDetails> contacts = gson.fromJson(json, new TypeToken<List<ContactDetails>>(){}.getType());
        return contacts.stream()
            .map((g) -> new Object[] {g})
            .collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testCreationNewContact(ContactDetails contact) {
        Contacts before = app.contact().all();
        //File photo = new File("src/test/java/resources/tt.png");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadCreationNewContact() {
        Contacts before = app.contact().all();
        ContactDetails contact = new ContactDetails().withFirstname("Dima'").withMiddlename(null).withLastname("Dmitriev'")
                .withNickname(null).withCompany(null).withAddress(null).withMobile(null).withWorkphone(null).withGroup("test1");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }


}
