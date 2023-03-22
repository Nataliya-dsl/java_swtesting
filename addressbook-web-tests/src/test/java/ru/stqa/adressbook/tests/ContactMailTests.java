package ru.stqa.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactDetails().withFirstname("Test").withMiddlename(null).withLastname("Testov").withNickname(null)
                    .withCompany("TestCompany").withAddress("Country1,City1, Street1, 1-1-1")
                    .withHomePhone("+45123456789").withMobile("+45123456789").withWorkphone("+987654321")
                    .withEmail("test@test.ru").withEmail2("test2@test.ru").withEmail3("test3@test.ru").withGroup("test1"));
        }
    }

    @Test
    public void testContactMails() {
        app.goTo().homePage();
        ContactDetails contact = app.contact().all().iterator().next();
        ContactDetails contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergeEmails(ContactDetails contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
