package com.example.www.mughalai_kitchan_app;

/**
 * Created by Welcome on 20/3/2018.
 */

public class UserInfo {
    public String address;
    public String email;
    public String phone;
    public String firstName;
    public String lastname;
    public String refID;

    public UserInfo() {
    }

    public UserInfo(String address, String email, String phone, String firstName, String lastname, String id) {
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastname = lastname;
        this.refID = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId() {
        return refID;
    }

    public void setId(String id) {
        this.refID = id;
    }
}
