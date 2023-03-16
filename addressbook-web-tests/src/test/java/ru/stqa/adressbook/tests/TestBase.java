package ru.stqa.adressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.adressbook.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(Browser.CHROME.browserName());

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();

    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }


}
