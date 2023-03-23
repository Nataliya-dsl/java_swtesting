package ru.stqa.adressbook.tests;

import org.testng.annotations.*;
import ru.stqa.adressbook.model.ContactDetails;
import ru.stqa.adressbook.model.Contacts;
import ru.stqa.adressbook.model.GroupData;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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

    @Test
    public void testCreationNewContact() {
        Contacts before = app.contact().all();
        File photo = new File("src/test/java/resources/tt.png");
        ContactDetails contact = new ContactDetails().withFirstname("Petr").withMiddlename("Pavlovich").withLastname("Smirnov")
                .withCompany("TestCompany").withAddress("Country1,City1, Street1, 1-1-1")
                .withHomePhone("111111").withMobile("+45123456789").withPhoto(photo).withGroup(TEST_GROUP);
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
