package com.example.hci.hciassignment.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by rauna on 3/19/2017.
 */

public class Contact implements Parcelable, Comparator<Contact> {

    private static final String separator = "\t";
    private final String terminator = "\n";

    private String firstName;

    private String lastName;

    private String phoneNo;

    private String email;

    private String birthDate;

    public Contact() {
        this.firstName = "";
        this.lastName = "";
        this.phoneNo = "";
        this.email = "";
        this.birthDate = "";
    }

    public Contact(String firstName, String lastName, String phoneNo, String email, String birthDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.birthDate = birthDate;
    }

    protected Contact(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        phoneNo = in.readString();
        email = in.readString();
        birthDate = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public static Contact stringToContact(String strContact) {
        String[] attrs = strContact.split(separator);
        if(attrs.length == 5) {
            return new Contact(attrs[0], attrs[1], attrs[2], attrs[3], attrs[4]);
        }
        return null;
    }

    public String getName() {
        return new StringBuilder().append(firstName).append(" ").append(lastName).toString();
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
        return this.firstName + separator + this.lastName
                + separator + this.phoneNo + separator + this.email + separator + this.birthDate + terminator;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(phoneNo);
        dest.writeString(email);
        dest.writeString(birthDate);
    }

    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getFirstName().compareToIgnoreCase(o2.getName());
    }
}
