package com.example.hci.hciassignment.io;

import android.content.Context;

import com.example.hci.hciassignment.entity.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Parag Dakle on 21/3/17.
 */

public class ContactsManager {

    final String fileName = "Contacts.txt";
    String filePath;
    Context mContext;
    FileReader reader;
    FileWriter writer;

    public ContactsManager(Context context) {
        this.filePath = context.getFilesDir().getAbsolutePath()+ "/" + fileName;
        mContext = context;
        reader = new FileReader(filePath, fileName, context);
        writer = new FileWriter(filePath, fileName, context);
    }

    public boolean addContact(Contact contact) {
        if(writer.openFile()) {
            boolean success = writer.writeLine(contact.toString());
            writer.closeFile();
            return success;
        }
        return false;
    }

    public Boolean addContacts(List<Contact> contactList) {
        if(writer.openFile()) {
            for(Contact contact : contactList) {
                writer.writeLine(contact.toString());
            }
            writer.closeFile();
            return true;
        }
        return false;
    }

    public List<Contact> getContacts() {
        List<Contact> contactList = new ArrayList<>();
        if(reader.openFile()) {
            String line;
            Contact contact;
            while ((line = reader.readLine()) != null) {
                contact = Contact.stringToContact(line);
                contactList.add(contact);
            }
            reader.closeFile();
        }
        return contactList;
    }
}
