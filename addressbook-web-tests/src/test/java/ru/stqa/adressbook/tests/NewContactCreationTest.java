package ru.stqa.adressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.adressbook.model.ContactDetails;
import ru.stqa.adressbook.model.Contacts;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class NewContactCreationTest extends TestBase {

    @Test
    public void testCreationNewContact() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactDetails contact = new ContactDetails().withFirstname("Petr").withMiddlename("Pavlovich").withLastname("Smirnov")
                .withNickname("testuser").withCompany("TestCompany").withAddress("Country1,City1, Street1, 1-1-1").withMobile("+45123456789")
                .withWorkphone("+987654321").withGroup("test1");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
