package ru.stqa.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }
    @Test
    public void testChangePassword() throws IOException, InterruptedException {
        long now = System.currentTimeMillis();
        String user = "user" + now;
        String password = "password";
        String newPassword = "newpassword";
        String email = String.format("user%s@localhost", now);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);

        app.usermanagment().login("administrator", "root");
        app.usermanagment().goToUserManagementPage();
        app.usermanagment().chooseUser(user);
        app.usermanagment().resetPassword();
        List<MailMessage> resetMailMessages = app.mail().waitForMail(1, 10000);

        String resetConfirmationLink = findLink(
            List.of(resetMailMessages.get(resetMailMessages.size() - 1)),
            email
        );
        app.usermanagment().goToResetconfirmationLink(resetConfirmationLink);
        app.usermanagment().createNewPassword(user, newPassword);

        assertTrue(app.newSession().login(user, newPassword));


    }
    private String findLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream()
            .filter((MailMessage m) -> m.to.equals(email))
            .findFirst()
            .get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
