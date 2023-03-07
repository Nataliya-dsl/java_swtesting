package ru.stqa.adressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    protected WebDriver wd;

    @BeforeMethod
    public void setUp() {
      wd = new ChromeDriver();
      wd.manage().timeouts().implicitlyWait(Duration.ofDays((20)));
      wd.get("http://localhost/addressbook/index.php");
      login("admin", "secret");

    }

    private void login(String username, String password) {
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }

    protected void returnToGroupPage() {
      wd.findElement(By.linkText("group page")).click();
    }

    protected void submitGroupCreation() {
      wd.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wd.findElement(By.name("group_header")).click();
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wd.findElement(By.name("group_footer")).click();
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initGroupCreation() {
      wd.findElement(By.name("new")).click();
    }

    protected void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    @AfterMethod
    public void tearDown() throws Exception {
      wd.quit();
    }


    private boolean isAlertPresent(By by){
      try {
        wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    protected void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }

    protected void deleteSelectedGroup() {
      wd.findElement(By.name("delete")).click();
    }
}
