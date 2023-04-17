package ru.stqa.adressbook.tests;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;
import ru.stqa.adressbook.model.Contacts;
import ru.stqa.adressbook.model.GroupData;
import ru.stqa.adressbook.model.Groups;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class AddingContactToGroupTests extends TestBase {

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
    public void testAddingContactToGroup() {
        Contacts contactsBefore = app.db().contacts();
        ContactDetails addingContactToGroup = contactsBefore.iterator().next();

        app.goTo().homePage();
        String groupId = app.contact().addContactToGroup(addingContactToGroup);
        app.goTo().homePage();

        Contacts contactsAfter = app.db().contacts();

        ContactDetails contactAfterAddingToGroup = contactsAfter.stream().filter(
            c -> c.getId() == addingContactToGroup.getId()
        ).findFirst().orElseThrow();

        Assert.assertTrue(
            contactAfterAddingToGroup.getGroups().stream().anyMatch(
                g -> g.getId() == Integer.parseInt(groupId)
            )
        );

        verifyContactListInUI();
    }

}
