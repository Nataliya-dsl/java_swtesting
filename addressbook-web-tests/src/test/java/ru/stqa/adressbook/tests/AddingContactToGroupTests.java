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
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class AddingContactToGroupTests extends TestBase {


    public GroupContact ensurePreconditions() {
        String groupName = "test" + UUID.randomUUID();
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(groupName));

        String firstname = "Petr" + UUID.randomUUID();
        app.goTo().homePage();

        app.contact().create(new ContactDetails().withFirstname(firstname).withMiddlename("Pavlovich").withLastname("Smirnov")
            .withNickname("testuser").withCompany("TestCompany").withAddress("Country1,City1, Street1, 1-1-1").withMobile("+45123456789")
            .withWorkphone("+987654321"));
        return new GroupContact(groupName, firstname);
    }

    @Test
    public void testAddingContactToGroup() {
        GroupContact groupContact = ensurePreconditions();

        ContactDetails contactsBefore = app.db().contacts().stream().filter(
            it -> it.getFirstname().equals(groupContact.contactName)
        ).findFirst().orElseThrow();

        app.goTo().homePage();
        String groupId = app.contact().addContactToGroup(contactsBefore, groupContact.groupName);

        ContactDetails contactAfterAddingToGroup = app.db().contacts().stream()
            .filter(
                it -> it.getFirstname().equals(groupContact.contactName)
            )
            .findFirst()
            .orElseThrow();

        Assert.assertTrue(
            contactAfterAddingToGroup.getGroups().stream().anyMatch(
                g -> g.getId() == Integer.parseInt(groupId)
            )
        );

        verifyContactListInUI();
    }

    class GroupContact {
        private final String groupName;
        private final String contactName;

        public GroupContact(String groupName, String contactName) {
            this.groupName = groupName;
            this.contactName = contactName;
        }

        public String getGroupName() {
            return groupName;
        }

        public String getContactName() {
            return contactName;
        }
    }

}
