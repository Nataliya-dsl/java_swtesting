package ru.stqa.adressbook;

import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
public class NewContactCreationTest {
  private WebDriver wd;
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

  @AfterMethod
  public void tearDown() {
    wd.quit();
  }
  @Test
  public void testCreationNewContact() {

    addNewContact();
    fillContactData(new NewContactDatas("Petr", "Pavlovich", "Smirnov", "testuser",
            "TestCompany", "Country1,City1, Street1, 1-1-1",
            "+45123456789", "+987654321"));
    submitContactCreation();
    returnHomePage();
  }

  private void returnHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  private void submitContactCreation() {
    wd.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
  }

  private void fillContactData(NewContactDatas dataGroup) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).sendKeys(dataGroup.getFirstname());
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).sendKeys(dataGroup.getMiddlename());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).sendKeys(dataGroup.getLastname());
    wd.findElement(By.name("nickname")).click();
    wd.findElement(By.name("nickname")).sendKeys(dataGroup.getNickname());
    wd.findElement(By.name("company")).click();
    wd.findElement(By.name("company")).sendKeys(dataGroup.getCompany());
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).sendKeys(dataGroup.getAddress());
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).sendKeys(dataGroup.getMobile());
    wd.findElement(By.name("work")).click();
    wd.findElement(By.name("work")).sendKeys(dataGroup.getWork());
  }

  private void addNewContact() {
    wd.findElement(By.linkText("add new")).click();
  }
}
