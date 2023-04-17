package ru.stqa.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;
import ru.stqa.adressbook.model.Contacts;
import ru.stqa.adressbook.model.GroupData;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class DeletionContactFromGroupTests extends TestBase {


    private String prepareTestGroup() {
        String testGroupName = "test" + UUID.randomUUID();
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(testGroupName));

        return testGroupName;
    }

    private String prepareTestContact(String testGroupName) {
        String testContactName = "test" + UUID.randomUUID();

        app.goTo().homePage();
        app.contact().create(new ContactDetails().withFirstname(testContactName).withMiddlename("Petrov").withLastname("Petrov")
            .withNickname("testuser").withCompany("TestCompany").withAddress("Country1,City1, Street1, 1-1-1").withMobile("+45123456789")
            .withWorkphone("+987654321").withGroup(testGroupName)
        );

        return testContactName;

    }


    @Test
    public void testDeletionContactFromGroup() {
        String testGroupName = prepareTestGroup();
        String testContactName = prepareTestContact(testGroupName);


        ContactDetails contactUnderTest = app.db().contacts().stream()
            .filter(contact -> contact.getFirstname().equals(testContactName))
            .findFirst()
            .orElseThrow();

        app.goTo().homePage();
        String groupId = app.contact().deletedContactFromGroup(contactUnderTest, testGroupName);

        ContactDetails contactAfterDeletionFromGroup = app.db().contacts().stream()
            .filter(c -> c.getId() == contactUnderTest.getId())
            .findFirst()
            .orElseThrow();

        assertTrue(
            contactAfterDeletionFromGroup.getGroups().stream().noneMatch(
                g -> g.getId() == Integer.parseInt(groupId)
            )
        );

        verifyContactListInUI();
    }
}
