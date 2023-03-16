package ru.stqa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test(enabled = false)
    public void testContactDeletion() {
        app.goTo().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDetails("Petr", "Pavlovich", "Smirnov", "testuser",
                    "TestCompany", "Country1,City1, Street1, 1-1-1",
                    "+45123456789", "+987654321", "test1"));
        }
        List<ContactDetails> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmationDeleteContact();
        app.goTo().gotoHomePage();
        List<ContactDetails> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
