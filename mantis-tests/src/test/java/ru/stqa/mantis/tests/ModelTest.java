package ru.stqa.mantis.tests;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import ru.stqa.mantis.model.json.BugifyIssue;

public class ModelTest {

    @Test
    void testSerialization() {
        String jsonStr = "{\n" +
            "        \"assignee_id\": 5,\n" +
            "        \"category_id\": 5,\n" +
            "        \"created\": \"2023-03-25T10:07:04+03:00\",\n" +
            "        \"creator\": {\n" +
            "            \"id\": \"1\",\n" +
            "            \"created\": \"2023-02-28T07:41:06+03:00\",\n" +
            "            \"updated\": \"2023-04-19T20:23:58+03:00\",\n" +
            "            \"firstname\": \"b31e382ca8445202e66b03aaf31508a3\",\n" +
            "            \"lastname\": \"admin\",\n" +
            "            \"name\": \"b31e382ca8445202e66b03aaf31508a3 admin\",\n" +
            "            \"email\": \"admin@bugify.stqa.ru\",\n" +
            "            \"username\": \"admin\",\n" +
            "            \"notifications\": {\n" +
            "                \"creator\": true,\n" +
            "                \"assignee\": true,\n" +
            "                \"following\": true,\n" +
            "                \"commented\": true,\n" +
            "                \"mychange\": true,\n" +
            "                \"mentioned\": true,\n" +
            "                \"allcreates\": true\n" +
            "            },\n" +
            "            \"groups\": [],\n" +
            "            \"settings\": {\n" +
            "                \"scm_usernames\": \"admin\",\n" +
            "                \"page_size\": 20,\n" +
            "                \"language\": \"en_US\",\n" +
            "                \"use_editor\": \"true\"\n" +
            "            },\n" +
            "            \"owner\": true,\n" +
            "            \"timezone\": \"Europe\\/Moscow\",\n" +
            "            \"state\": 1,\n" +
            "            \"api_key\": \"b31e382ca8445202e66b03aaf31508a3\"\n" +
            "        },\n" +
            "        \"creator_id\": 1,\n" +
            "        \"description\": \"test homework for course\",\n" +
            "        \"id\": 10,\n" +
            "        \"labels\": [],\n" +
            "        \"milestone_id\": 5,\n" +
            "        \"percentage\": 0,\n" +
            "        \"priority\": \"2\",\n" +
            "        \"priority_name\": \"High\",\n" +
            "        \"project_id\": 5,\n" +
            "        \"related_issue_ids\": [],\n" +
            "        \"resolved\": \"1970-01-01T03:00:00+03:00\",\n" +
            "        \"state\": \"1\",\n" +
            "        \"state_name\": \"In Progress\",\n" +
            "        \"subject\": \"homework\",\n" +
            "        \"updated\": \"2023-04-14T12:57:49+03:00\"\n" +
            "    }";

        Gson gson = new Gson();
        BugifyIssue bugifyIssue = gson.fromJson(jsonStr, BugifyIssue.class);
        System.out.println(bugifyIssue);
    }
}
