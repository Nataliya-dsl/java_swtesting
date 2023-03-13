package ru.stqa.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactDetails(new ContactDetails("Test", "Test", "Test", "testuser",
                "TestCompany", "Country, City, Street, 0",
                "+45123456789", "+987654321"));
        app.getContactHelper().updateContactData();
        app.getContactHelper().returnHomePage();
    }
}
