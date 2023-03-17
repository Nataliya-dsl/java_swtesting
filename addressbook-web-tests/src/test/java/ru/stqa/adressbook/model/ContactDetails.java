package ru.stqa.adressbook.model;

import java.util.Collections;
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

    public ContactDetails withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactDetails withWorkphone(String workphone) {
        this.workphone = workphone;
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


}
