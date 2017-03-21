package com.example.hci.hciassignment.entity;

/**
 * Created by rauna on 3/19/2017.
 */

public class Contact {

    private String firstName;

    private String lastName;

    private String phoneNo;

    private String email;

    private String birthDate;

    public Contact(String firstName, String lastName, String phoneNo, String email, String birthDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.birthDate = birthDate;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString()
    {
        return this.firstName + "\t" + this.lastName + "\t" + this.phoneNo + "\t" + this.email + "\t" + this.birthDate;
    }
}
