package ru.stqa.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.adressbook.model.ContactDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    public void fillContactDetails(ContactDetails contactDetails, boolean creation) {
        type(By.name("firstname"), contactDetails.getFirstname());
        type(By.name("middlename"), contactDetails.getMiddlename());
        type(By.name("lastname"), contactDetails.getLastname());
        type(By.name("nickname"), contactDetails.getNickname());
        type(By.name("company"), contactDetails.getCompany());
        type(By.name("address"), contactDetails.getAddress());
        type(By.name("mobile"), contactDetails.getMobile());
        type(By.name("work"), contactDetails.getWorkPhone());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDetails.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }


    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }
    public void selectContactById(int id) {

        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public void editContact(int index) {
        wd.findElements(By.cssSelector("img[alt='Edit']")).get(index).click();
    }
    public void editContactById(int id) {

        wd.findElements(By.xpath("//input[@value='" + id + "']/..//td/a/img[@alt='Edit']"));
        //wd.findElements(By.cssSelector("img[alt='Edit']")).get(index).click();
    }
    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactDetails contact) {
        addNewContact();
        fillContactDetails(contact, true);
        submitContactCreation();
        returnHomePage();
    }

    public void modify(ContactDetails contact) {
        editContactById(contact.getId());
        fillContactDetails(contact,false);
        submitContactModification();
        returnHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteContact();
        confirmationDeleteContact();
    }

    public void delete(ContactDetails contact) {
        selectContactById(contact.getId());
        deleteContact();
        confirmationDeleteContact();
    }
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactDetails> list() {
        List<ContactDetails> contacts = new ArrayList<ContactDetails>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> td = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = td.get(2).getText();
            String lastname = td.get(1).getText();
            ContactDetails contact = new ContactDetails().withId(id).withFirstname(firstname).withMiddlename(null).withLastname(lastname)
                    .withNickname(null).withCompany(null).withAddress(null).withMobile(null)
                    .withWorkphone(null).withGroup(null);
            contacts.add(contact);
        }
        return contacts;
    }

    public Set<ContactDetails> all() {
        Set<ContactDetails> contacts = new HashSet<ContactDetails>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> td = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = td.get(2).getText();
            String lastname = td.get(1).getText();
            ContactDetails contact = new ContactDetails().withId(id).withFirstname(firstname).withMiddlename(null).withLastname(lastname)
                    .withNickname(null).withCompany(null).withAddress(null).withMobile(null)
                    .withWorkphone(null).withGroup(null);
            contacts.add(contact);
        }
        return contacts;
    }


}
