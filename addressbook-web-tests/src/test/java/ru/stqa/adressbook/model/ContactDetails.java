package ru.stqa.adressbook.model;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactDetails {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "middlename")
    private String middlename;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "company")
    private String company;
    @Expose
    @Column(name = "address")
    private String address;
    @Expose
    @Column(name = "mobile")
    private String mobile;
    @Expose
    @Column(name = "work")
    private String workphone;
    @Column(name = "home")
    private String homephone;
    @Transient
    private String allPhones;
    @Column(name = "email")
    private String email;
    @Column(name = "email2")
    private String email2;
    @Column(name = "email3")
    private String email3;
    @Transient
    private String allEmails;
    @Column(name = "phone2")
    private String homesecondaryphone;
    @Column(name = "photo")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    private LocalDateTime deprecated;

    public LocalDateTime getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(LocalDateTime deprecated) {
        this.deprecated = deprecated;
    }

    public File getPhoto() {
        return (photo != null) ? new File(photo): null;
    }

    public ContactDetails withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactDetails{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", company='" + company + '\'' +
            ", address='" + address + '\'' +
            ", mobile='" + mobile + '\'' +
            ", workphone='" + workphone + '\'' +
            ", homephone='" + homephone + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", homesecondaryphone='" + homesecondaryphone + '\'' +
            ", photo='" + photo + '\'' +
            '}';
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

    public ContactDetails withGroup(String group) {
        GroupData groupData = new GroupData().withName(group);
        this.groups.add(groupData);
        return this;
    }



    public ContactDetails withHomesecondaryphone(String homesecondaryphone) {
        this.homesecondaryphone = homesecondaryphone;
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

    public Groups getGroups() {
        return new Groups(groups);
    }

    public int getId() {
        return id;
    }

    public ContactDetails withId(int id) {
        this.id = id;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDetails that = (ContactDetails) o;
        return id == that.id &&
            Objects.equals(nullify(firstname), nullify(that.firstname)) &&
            Objects.equals(nullify(middlename), nullify(that.middlename)) &&
            Objects.equals(nullify(lastname), nullify(that.lastname)) &&
            Objects.equals(nullify(nickname), nullify(that.nickname)) &&
            Objects.equals(nullify(company), nullify(that.company)) &&
            Objects.equals(nullify(address), nullify(that.address)) &&
            Objects.equals(nullify(mobile), nullify(that.mobile)) &&
            Objects.equals(nullify(workphone), nullify(that.workphone)) &&
            Objects.equals(nullify(homephone), nullify(that.homephone)) &&
            Objects.equals(nullify(email), nullify(that.email)) &&
            Objects.equals(nullify(email2), nullify(that.email2)) &&
            Objects.equals(nullify(email3), nullify(that.email3)) &&
            Objects.equals(nullify(homesecondaryphone), nullify(that.homesecondaryphone));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, middlename, lastname, nickname, company, address, mobile, workphone, homephone, email, email2, email3, homesecondaryphone);
    }

    public String nullify(String value) {
        return (Objects.equals(value, "null") || "".equals(value)) ? null : value;
    }

}
