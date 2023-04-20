package ru.stqa.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.mantis.model.Issue;
import ru.stqa.mantis.model.json.BugifyIssue;
import ru.stqa.mantis.model.json.BugifyIssue1;

import java.io.IOException;

public class BugifyTest extends TestBase {

    public BugifyIssue1 createIssue() throws IOException {
        BugifyIssue1 bugifyIssue = new BugifyIssue1();

        bugifyIssue.description = "test description";
        bugifyIssue.subject = "test123";

        if (app.newSession().createBugifyIssue(bugifyIssue))
            return bugifyIssue;
        else throw new RuntimeException("Failed to create issue");
    }


    @Test
    void checkIssue() throws IOException {
        BugifyIssue1 issue = createIssue();
    }
}
