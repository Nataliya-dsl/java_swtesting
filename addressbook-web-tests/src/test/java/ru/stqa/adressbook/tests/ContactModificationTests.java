package ru.stqa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactDetails().withFirstname("Petr").withMiddlename("Pavlovich").withLastname("Smirnov")
                    .withNickname("testuser").withCompany("TestCompany").withAddress("Country1,City1, Street1, 1-1-1").withMobile("+45123456789")
                    .withWorkphone("+987654321").withGroup("test1"));
        }
    }


    @Test
    public void testContactModification() {
        Set<ContactDetails> before = app.contact().all();
        ContactDetails modifiedContact = before.iterator().next();
        ContactDetails contact = new ContactDetails().withId(modifiedContact.getId()).withFirstname("Test").withMiddlename("Test")
                .withLastname("Test").withNickname("testuser").withCompany("TestCompany").withAddress("Country, City, Street, 0")
                .withMobile("+45123456789").withWorkphone("+987654321").withGroup(null);

        app.contact().modify(contact);
        Set<ContactDetails> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
