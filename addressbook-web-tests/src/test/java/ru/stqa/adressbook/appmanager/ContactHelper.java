package ru.stqa.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.adressbook.model.NewContactDatas;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    public void fillContactData(NewContactDatas dataGroup) {
        type(By.name("firstname"), dataGroup.getFirstname());
        type(By.name("middlename"), dataGroup.getMiddlename());
        type(By.name("lastname"), dataGroup.getLastname());
        type(By.name("nickname"), dataGroup.getNickname());
        type(By.name("company"), dataGroup.getCompany());
        type(By.name("address"), dataGroup.getAddress());
        type(By.name("mobile"), dataGroup.getMobile());
        type(By.name("work"), dataGroup.getWork());
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
