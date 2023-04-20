package ru.stqa.mantis.appmanager;


import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import ru.stqa.mantis.model.json.BugifyIssue;
import ru.stqa.mantis.model.json.BugifyIssue1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    private Gson gson = new Gson();

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpclient.execute(post);
        String body = getTextFrom(response);
        return body.contains(String.format(app.getProperty("loginhomepage"), username));
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }
    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        CloseableHttpResponse response = httpclient.execute(get);
        String body = getTextFrom(response);
        return body.contains(String.format(app.getProperty("loginhomepage"), username));

    }

    public boolean createBugifyIssue(BugifyIssue1 issue) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.bugify.url") + "/issues.json");
        post.addHeader(HttpHeaders.AUTHORIZATION, createAuthHeader());
        post.setEntity(
            new StringEntity(gson.toJson(issue), ContentType.APPLICATION_JSON)
        );
        CloseableHttpResponse response = httpclient.execute(post);
        return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
    }

    private String createAuthHeader(){
        final String auth = app.getProperty("web.bugify.api-key") + ":";
        final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        return  "Basic " + new String(encodedAuth);
    }



}
