package ru.stqa.adressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactDetails {

    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String company;
    private String address;
    private String mobile;
    private String workphone;
    private String group;
    private String homephone;
    private String allPhones;
    private String email;
    private String email2;
    private String email3;
    private String allEmails;
    private String homesecondaryphone;
    private File photo;

    public File getPhoto() {
        return photo;
    }

    public ContactDetails withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactDetails withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }


    public String getAllEmails() {
        return allEmails;
    }


    public ContactDetails withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactDetails withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactDetails withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactDetails withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactDetails withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactDetails withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactDetails withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactDetails withHomePhone(String homephone) {
        this.homephone = homephone;
        return this;
    }
    public ContactDetails withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactDetails withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactDetails withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactDetails withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactDetails withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactDetails withHomesecondaryphone(String homesecondaryphone) {
        this.homesecondaryphone = homesecondaryphone;
        return this;
    }
    public ContactDetails withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWorkPhone() {
        return workphone;
    }

    public String getHomePhone() { return homephone; };
    public String getEmail() { return email; }
    public String getEmail2() { return email2; }

    public String getEmail3() { return email3; }

    public String getHomesecondaryphone() { return homesecondaryphone; }
    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactDetails{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDetails that = (ContactDetails) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    public ContactDetails withId(int id) {
        this.id = id;
        return this;
    }

}
