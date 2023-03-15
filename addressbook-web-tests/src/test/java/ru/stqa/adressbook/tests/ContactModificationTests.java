package ru.stqa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDetails("Petr", "Pavlovich", "Smirnov", "testuser",
                    "TestCompany", "Country1,City1, Street1, 1-1-1",
                    "+45123456789", "+987654321", "test1"));
        }

        List<ContactDetails> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() - 1);
        ContactDetails contact = new ContactDetails(before.get(before.size() - 1).getId(), "Test", "Test", "Test", "testuser",
                "TestCompany", "Country, City, Street, 0",
                "+45123456789", "+987654321", null);
        app.getContactHelper().fillContactDetails(contact,false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnHomePage();
        List<ContactDetails> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactDetails> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
