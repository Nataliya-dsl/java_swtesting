package ru.stqa.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDetails("Petr", "Pavlovich", "Smirnov", "testuser",
                    "TestCompany", "Country1,City1, Street1, 1-1-1",
                    "+45123456789", "+987654321", "test1"));
        }
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactDetails(new ContactDetails("Test", "Test", "Test", "testuser",
                "TestCompany", "Country, City, Street, 0",
                "+45123456789", "+987654321", null), false);
        app.getContactHelper().updateContactData();
        app.getContactHelper().returnHomePage();
    }
}
