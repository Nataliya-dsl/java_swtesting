package ru.stqa.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.adressbook.model.ContactDetails;

public class NewContactCreationTest extends TestBase {

    @Test
    public void testCreationNewContact() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactDetails("Petr", "Pavlovich", "Smirnov", "testuser",
                "TestCompany", "Country1,City1, Street1, 1-1-1",
                "+45123456789", "+987654321", "test1"));
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }

}
