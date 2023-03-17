package ru.stqa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.adressbook.model.ContactDetails;

import java.util.*;

public class NewContactCreationTest extends TestBase {

    @Test
    public void testCreationNewContact() {
        app.goTo().homePage();
        Set<ContactDetails> before = app.contact().all();
        ContactDetails contact = new ContactDetails().withFirstname("Petr").withMiddlename("Pavlovich").withLastname("Smirnov")
                .withNickname("testuser").withCompany("TestCompany").withAddress("Country1,City1, Street1, 1-1-1").withMobile("+45123456789")
                .withWorkphone("+987654321").withGroup("test1");
        app.contact().create(contact);
        Set<ContactDetails> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
