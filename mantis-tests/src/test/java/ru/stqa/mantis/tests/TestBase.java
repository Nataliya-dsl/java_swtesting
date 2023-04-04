package ru.stqa.mantis.tests;

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

}
