package com.andsofts.ridwane.arrangeit;

public class Users {
    String Address, Email, Name, Password, Phone;
    boolean Org;

    public Users(){}

    public Users(String address, String email, String name, boolean org, String password, String phone) {
        Address = address;
        Email = email;
        Name = name;
        Password = password;
        Phone = phone;
        Org = org;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public boolean isOrg() {
        return Org;
    }

    public void setOrg(boolean org) {
        Org = org;
    }
}
