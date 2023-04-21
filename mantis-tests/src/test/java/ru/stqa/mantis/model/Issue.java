package ru.stqa.mantis.model;

import java.util.Objects;

public class Issue {
    private int id;
    private String summary;
    private String description;

    private int state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && Objects.equals(description, issue.description) && Objects.equals(subject, issue.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, subject);
    }

    private Project project;
    private String subject;

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public int getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    public Issue withStatus(int status) {
        this.state = status;
        return this;
    }


}
