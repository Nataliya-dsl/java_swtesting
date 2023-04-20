package ru.stqa.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.Browser;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));
    //(Browser.CHROME.browserName())

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        //app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        //app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) {
        try {
            ObjectRef status = app.soap().getIssue(BigInteger.valueOf(issueId)).getStatus();
            return status.getId().intValue() != 80;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
