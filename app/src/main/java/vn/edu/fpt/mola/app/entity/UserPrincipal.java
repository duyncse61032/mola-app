package vn.edu.fpt.mola.app.entity;

import java.util.Date;

import vn.edu.fpt.mola.app.entity.enumerate.Gender;

/**
 * Created by phuctran93 on 9/20/2016.
 */
public class UserPrincipal {

    private long id;
    private String username;
    //private byte[] password;

    private String title;
    private String firstName;
    private String lastName;
    private String nameSuffix;
    private String displayName;
    private Gender gender;
    private Date birthday;
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //public byte[] getPassword() {
        //return password;
    //}

    //public void setPassword(byte[] password) {
        //this.password = password;
    //}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
