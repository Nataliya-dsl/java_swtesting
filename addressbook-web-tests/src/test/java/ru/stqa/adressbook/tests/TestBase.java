package ru.stqa.adressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.adressbook.appmanager.ApplicationManager;
import ru.stqa.adressbook.model.ContactDetails;
import ru.stqa.adressbook.model.Contacts;
import ru.stqa.adressbook.model.GroupData;
import ru.stqa.adressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));
    //(Browser.CHROME.browserName())

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                .map((c) -> new ContactDetails().withId(c.getId()).withFirstname(c.getFirstname()).withLastname(c.getLastname()))
                .collect(Collectors.toSet())));
        }
    }
}
