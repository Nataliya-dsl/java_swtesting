package ru.stqa.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.adressbook.model.ContactDetails;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactDetails().withFirstname("Test").withMiddlename(null).withLastname("Testov").withNickname(null)
                    .withCompany("TestCompany").withAddress("Country1,City1, Street1, 1-1-1").withHomePhone("+45123456789")
                    .withMobile("+45123456789").withWorkphone("+987654321").withGroup("test1"));
        }
    }
    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactDetails contact = app.contact().all().iterator().next();
        ContactDetails contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactDetails contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
