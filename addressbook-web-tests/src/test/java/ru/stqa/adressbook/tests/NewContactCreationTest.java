package ru.stqa.adressbook.tests;

import org.testng.annotations.*;
import ru.stqa.adressbook.model.NewContactDatas;

public class NewContactCreationTest extends TestBase {

  @Test
  public void testCreationNewContact() {
    app.getNewContactHelper().addNewContact();
    app.getNewContactHelper().fillContactData(new NewContactDatas("Petr", "Pavlovich", "Smirnov", "testuser",
            "TestCompany", "Country1,City1, Street1, 1-1-1",
            "+45123456789", "+987654321"));
    app.getNewContactHelper().submitContactCreation();
    app.getNavigationHelper().returnHomePage();
  }

}
