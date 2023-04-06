package ru.stqa.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;
import ru.stqa.adressbook.model.Contacts;
import ru.stqa.adressbook.model.GroupData;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class DeletionContactFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactDetails().withFirstname("Petr").withMiddlename("Pavlovich").withLastname("Smirnov")
                .withNickname("testuser").withCompany("TestCompany").withAddress("Country1,City1, Street1, 1-1-1").withMobile("+45123456789")
                .withWorkphone("+987654321"));
        }
    }

    @Test
    public void testDeletionContactFromGroup() {
        Contacts before = app.db().contacts();
        ContactDetails deletedContactFromGroup = before.iterator().next();

        app.goTo().homePage();
        String groupId = app.contact().deletedContactFromGroup(deletedContactFromGroup);
        app.goTo().homePage();

        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.db().contacts();
        ContactDetails contactAfterDeletionFromGroup = after.stream().filter(
            c -> c.getId() == deletedContactFromGroup.getId()
        ).findFirst().orElseThrow();

        assertTrue(
            contactAfterDeletionFromGroup.getGroups().stream().noneMatch(
                g -> g.getId() == Integer.parseInt(groupId)
            )
        );

        verifyContactListInUI();
    }
}
