package ru.stqa.adressbook.tests;

import org.testng.annotations.*;
import ru.stqa.adressbook.model.ContactDetails;

public class NewContactCreationTest extends TestBase {

    @Test
    public void testCreationNewContact() {
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillContactDetails(new ContactDetails("Petr", "Pavlovich", "Smirnov", "testuser",
                "TestCompany", "Country1,City1, Street1, 1-1-1",
                "+45123456789", "+987654321", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnHomePage();
    }

}
