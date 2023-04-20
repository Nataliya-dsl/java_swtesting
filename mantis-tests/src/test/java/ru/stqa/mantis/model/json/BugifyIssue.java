package ru.stqa.mantis.model.json;

import java.util.Date;
import java.util.List;

public class BugifyIssue {
    public int assignee_id;
    public int category_id;
    public Date created;
    public Creator creator;
    public int creator_id;
    public String description;
    public int id;
    public List<String> labels;
    public int milestone_id;
    public int percentage;
    public String priority;
    public String priority_name;
    public int project_id;
    public List<Object> related_issue_ids;
    public Date resolved;
    public String state;
    public String state_name;
    public String subject;
    public Date updated;

    @Override
    public String toString() {
        return "BugifyIssue{" +
            "assignee_id=" + assignee_id +
            ", category_id=" + category_id +
            ", created=" + created +
            ", creator=" + creator +
            ", creator_id=" + creator_id +
            ", description='" + description + '\'' +
            ", id=" + id +
            ", labels=" + labels +
            ", milestone_id=" + milestone_id +
            ", percentage=" + percentage +
            ", priority='" + priority + '\'' +
            ", priority_name='" + priority_name + '\'' +
            ", project_id=" + project_id +
            ", related_issue_ids=" + related_issue_ids +
            ", resolved=" + resolved +
            ", state='" + state + '\'' +
            ", state_name='" + state_name + '\'' +
            ", subject='" + subject + '\'' +
            ", updated=" + updated +
            '}';
    }

    public static class Creator{
        public String id;
        public Date created;
        public Date updated;
        public String firstname;
        public String lastname;
        public String name;
        public String email;
        public String username;
        public Notifications notifications;
        public List<Object> groups;
        public Settings settings;
        public boolean owner;
        public String timezone;
        public int state;
        public String api_key;
    }

    public static class Notifications{
        public boolean creator;
        public boolean assignee;
        public boolean following;
        public boolean commented;
        public boolean mychange;
        public boolean mentioned;
        public boolean allcreates;
    }

    public static class Settings{
        public String scm_usernames;
        public int page_size;
        public String language;
        public String use_editor;
    }



}
