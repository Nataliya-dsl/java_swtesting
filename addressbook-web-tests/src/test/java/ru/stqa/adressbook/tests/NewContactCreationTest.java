package ru.stqa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.adressbook.model.ContactDetails;

import java.util.*;
import java.util.Optional;

public class NewContactCreationTest extends TestBase {

    @Test
    public void testCreationNewContact() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactDetails> before = app.getContactHelper().getContactList();
        ContactDetails contact = new ContactDetails("Petr", "Pavlovich", "Smirnov", "testuser",
                "TestCompany", "Country1,City1, Street1, 1-1-1",
                "+45123456789", "+987654321", "test1");
        app.getContactHelper().createContact(contact);
        List<ContactDetails> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactDetails> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
