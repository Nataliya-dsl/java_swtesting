package ru.stqa.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private String browser;
    public WebDriver wd;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException  {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (Browser.CHROME.browserName().equals(browser)) {
            wd = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        } else if (Browser.SAFARI.browserName().equals(browser)) {
            wd = new SafariDriver();
        } else if (Browser.FIREFOX.browserName().equals(browser)){
            wd = new FirefoxDriver();
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
    }

    public void stop() {
        wd.quit();
    }

}
