package ru.stqa.mantis.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.stqa.mantis.model.Issue;
import ru.stqa.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase{

    @Test
    public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();

        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue()  throws MalformedURLException, ServiceException, RemoteException {
        Result result = createIssue();
        assertEquals(result.issue().getSummary(), result.created().getSummary());

    }

    @Test
    public void shouldSkipIfNotFixed() throws MalformedURLException, ServiceException, RemoteException {
        Issue newIssue = createIssue().created;
        assertThrows(SkipException.class, () -> skipIfNotFixed(newIssue.getState()));
    }

    private Result createIssue() throws MalformedURLException, ServiceException, RemoteException {
        Project project = app.soap().getProjects().stream()
            .filter( it -> it.getName().equals("test"))
            .findFirst()
            .orElseThrow();

        Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description")
            .withProject(project);
        Issue created = app.soap().addIssue(issue);

        return new Result(issue, created);
    }

    private record Result(Issue issue, Issue created) {
    }

    public void skipIfNotFixed(int status) {
        if (status != 80) {
            throw new SkipException("Status is 80");
        }
    }

}
