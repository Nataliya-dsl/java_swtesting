package ru.stqa.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.adressbook.model.ContactDetails;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    public void fillContactDetails(ContactDetails contactDetails) {
        type(By.name("firstname"), contactDetails.getFirstname());
        type(By.name("middlename"), contactDetails.getMiddlename());
        type(By.name("lastname"), contactDetails.getLastname());
        type(By.name("nickname"), contactDetails.getNickname());
        type(By.name("company"), contactDetails.getCompany());
        type(By.name("address"), contactDetails.getAddress());
        type(By.name("mobile"), contactDetails.getMobile());
        type(By.name("work"), contactDetails.getWorkPhone());
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmationDeleteContact() {
        wd.switchTo().alert().accept();
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void updateContactData() {
        click(By.name("update"));
    }
}
