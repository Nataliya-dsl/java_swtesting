package ru.stqa.adressbook;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;


public class GroupCreationTestsTest {
  private WebDriver wd;

  @BeforeMethod
  public void setUp() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofDays((20)));
    wd.get("http://localhost/addressbook/index.php");
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.xpath("//input[@value=\'Login\']")).click();

  }

  @Test
  public void testGroupCreation() {
    wd.findElement(By.linkText("groups")).click();
    wd.findElement(By.name("new")).click();
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).sendKeys("test1");
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).sendKeys("test2");
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).sendKeys("test3");
    wd.findElement(By.name("submit")).click();
    wd.findElement(By.linkText("group page")).click();
    wd.findElement(By.linkText("Logout")).click();
  }

  @AfterMethod
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by){
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent(By by){
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
