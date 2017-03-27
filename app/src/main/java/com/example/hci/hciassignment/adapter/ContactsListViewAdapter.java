package com.example.hci.hciassignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hci.hciassignment.entity.Contact;

import java.util.Collections;
import java.util.List;

/**
 * Created by root on 27/3/17.
 */

public class ContactsListViewAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Contact> contactList;

    public ContactsListViewAdapter(LayoutInflater inflater, List<Contact> contacts) {
        this.mInflater = inflater;
        this.contactList = contacts;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists
        // if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(android.R.layout.simple_list_item_2, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.textViewContactName = (TextView) convertView.findViewById(android.R.id.text1);
            holder.textViewContactNumber = (TextView) convertView.findViewById(android.R.id.text2);

            // hang onto this holder for future recyclage
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the current book's data in JSON form
        Contact contact = (Contact) getItem(position);

        holder.textViewContactName.setText(contact.getName());
        holder.textViewContactNumber.setText(contact.getPhoneNo());

        return convertView;
    }

    private static class ViewHolder {
        private TextView textViewContactName;
        private TextView textViewContactNumber;
    }

    public void updateData(List<Contact> contacts) {
        // update the adapter's dataset
        contactList = contacts;
        Collections.sort(contactList, new Contact());
        notifyDataSetChanged();
    }
}
