package ru.stqa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {


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
    public void testContactDeletion() {
        Set<ContactDetails> before = app.contact().all();
        ContactDetails deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Set<ContactDetails> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
