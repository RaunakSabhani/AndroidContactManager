package com.example.hci.hciassignment;

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

    @Override
    public String toString()
    {
        return this.firstName + "\t" + this.lastName + "\t" + this.phoneNo + "\t" + this.email + "\t" + this.birthDate;
    }
}
