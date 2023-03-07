package ru.stqa.adressbook;

import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
public class NewContactCreationTestTest {
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
    fillContactFullName(new GroupDataContact("Petr", "Pavlovich", "Petrov", "Pepe"));
    fillCompanyMainInform("TitleExample", "TestCompany", "Country1,City1, Street1, 1-1-1");
    fillPhoneNumber(new GroupDataPhones("+832098928", "+123456789", "+987654321", "+9078563412"));
    fillEmail("emailtest1@mail.ts", "emailtest2@mail.ts", "emailtest3@mail.ts");
    fillHomePage("www.homepagetest.ru");
    submitContactCreation();
    returnHomePage();
  }

  private void returnHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  private void submitContactCreation() {
    wd.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
  }

  private void fillHomePage(String homepage) {
    wd.findElement(By.name("homepage")).click();
    wd.findElement(By.name("homepage")).sendKeys(homepage);
  }

  private void fillEmail(String email1, String email2, String email3) {
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).sendKeys(email1);
    wd.findElement(By.name("email2")).click();
    wd.findElement(By.name("email2")).sendKeys(email2);
    wd.findElement(By.name("email3")).click();
    wd.findElement(By.name("email3")).sendKeys(email3);
  }

  private void fillPhoneNumber(GroupDataPhones groupDataPhones) {
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).sendKeys(groupDataPhones.getHome());
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).sendKeys(groupDataPhones.getMobile());
    wd.findElement(By.name("work")).click();
    wd.findElement(By.name("work")).sendKeys(groupDataPhones.getWork());
    wd.findElement(By.name("fax")).click();
    wd.findElement(By.name("fax")).sendKeys(groupDataPhones.getFax());
  }

  private void fillCompanyMainInform(String title, String company, String address) {
    wd.findElement(By.name("title")).click();
    wd.findElement(By.name("title")).sendKeys(title);
    wd.findElement(By.name("company")).click();
    wd.findElement(By.name("company")).sendKeys(company);
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).sendKeys(address);
  }

  private void fillContactFullName(GroupDataContact dataGroup) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).sendKeys(dataGroup.getFirstname());
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).sendKeys(dataGroup.getMiddlename());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).sendKeys(dataGroup.getLastname());
    wd.findElement(By.name("nickname")).click();
    wd.findElement(By.name("nickname")).sendKeys(dataGroup.getNickname());
  }

  private void addNewContact() {
    wd.findElement(By.linkText("add new")).click();
  }
}
