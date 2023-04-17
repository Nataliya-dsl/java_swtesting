package ru.stqa.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;
import ru.stqa.adressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactDetails().withFirstname("Petr").withMiddlename("Pavlovich").withLastname("Smirnov")
                    .withNickname("testuser").withCompany("TestCompany").withAddress("Country1,City1, Street1, 1-1-1").withMobile("+45123456789")
                    .withWorkphone("+987654321").withGroup(null));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactDetails modifiedContact = before.iterator().next();
        ContactDetails contact = new ContactDetails().withId(modifiedContact.getId()).withFirstname("Test").withMiddlename("Test")
                .withLastname("Test").withNickname("testuser").withCompany("TestCompany").withAddress("Country, City, Street, 0")
                .withMobile("+45123456789").withWorkphone("+987654321").withGroup(null).withHomePhone(null);
        app.goTo().homePage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }
}
