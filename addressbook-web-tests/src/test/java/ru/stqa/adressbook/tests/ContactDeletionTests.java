package ru.stqa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;
import ru.stqa.adressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDetails("Petr", "Pavlovich", "Smirnov", "testuser",
                    "TestCompany", "Country1,City1, Street1, 1-1-1",
                    "+45123456789", "+987654321", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmationDeleteContact();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
