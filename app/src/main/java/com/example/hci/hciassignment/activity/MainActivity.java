package com.example.hci.hciassignment.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hci.hciassignment.R;
import com.example.hci.hciassignment.adapter.ContactsListViewAdapter;
import com.example.hci.hciassignment.common.Constants;
import com.example.hci.hciassignment.entity.Contact;
import com.example.hci.hciassignment.io.ContactsManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    final int SAVE_CONTACTS = 1;
    final int READ_CONTACTS = 2;

    ListView contactsListView;
    List<Contact> contactList;
    ContactsListViewAdapter adapter;
    FileOprationAsyncTask<List<Contact>> updateContactsTask;
    FileOprationAsyncTask<Boolean> readContactsTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsListView = (ListView) findViewById(R.id.listViewContacts);
        contactsListView.setOnItemClickListener(this);

        contactList = new ArrayList<>();

        adapter = new ContactsListViewAdapter(getLayoutInflater(), contactList);
        contactsListView.setAdapter(adapter);
        fetchContacts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_action) {
            // do something here
            Intent i = new Intent(this, ContactFormActivity.class);
            i.putExtra(Constants.CONTACT_OPERATION_INTENT_KEY, Constants.CONTACT_INTENT_ADD_OPERATION_VALUE);
            startActivityForResult(i, Constants.CONTACT_INTENT_ADD_OPERATION_VALUE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == Constants.CONTACT_INTENT_ADD_OPERATION_VALUE) {
            // Make sure the request was successful
            Contact contact = (Contact) data.getParcelableExtra(Constants.CONTACT_ENTITY_INTENT_KEY);
            if (resultCode == Constants.CONTACT_INTENT_ADD_OPERATION_VALUE) {
                contactList.add(contact);
                updateContacts();
            }
        }
        else if (requestCode == Constants.CONTACT_INTENT_MODIFY_OPERATION_VALUE) {
            // Make sure the request was successful
            Contact contact = data.getParcelableExtra(Constants.CONTACT_ENTITY_INTENT_KEY);
            int id = data.getIntExtra(Constants.CONTACT_INDEX_INTENT_KEY, 0);
            if (resultCode == Constants.CONTACT_INTENT_UPDATE_OPERATION_VALUE) {
                contactList.remove(id);
                contactList.add(id, contact);
                updateContacts();
            }
            else if(resultCode == Constants.CONTACT_INTENT_DELETE_OPERATION_VALUE) {
                contactList.remove(id);
                updateContacts();
            }
        }
    }

    private void updateContacts() {
        updateContactsTask = new FileOprationAsyncTask<>(this, SAVE_CONTACTS, "Updating Contacts.");
        updateContactsTask.execute((Void) null);
    }

    private void fetchContacts() {
        readContactsTask = new FileOprationAsyncTask<>(this, READ_CONTACTS, "Loading Contacts.");
        readContactsTask.execute((Void) null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, ContactFormActivity.class);
        i.putExtra(Constants.CONTACT_OPERATION_INTENT_KEY, Constants.CONTACT_INTENT_MODIFY_OPERATION_VALUE);
        i.putExtra(Constants.CONTACT_ENTITY_INTENT_KEY, contactList.get(position));
        i.putExtra(Constants.CONTACT_INDEX_INTENT_KEY, position);
        startActivityForResult(i, Constants.CONTACT_INTENT_MODIFY_OPERATION_VALUE);
    }

    class FileOprationAsyncTask<T> extends AsyncTask<Void, T, T> {

        private ProgressDialog pDialog;
        private Context mContext;
        private int operation;
        private ContactsManager manager;

        public FileOprationAsyncTask(Context context, int operation, String displayMessage) {
            this.mContext = context;
            this.operation = operation;

            pDialog = new ProgressDialog(mContext);
            pDialog.setMessage(displayMessage);
            pDialog.show();

            manager = new ContactsManager(context);
        }

        @Override
        protected T doInBackground(Void... params) {
            if(operation == READ_CONTACTS) {
                return (T)manager.getContacts();
            }
            else if(operation == SAVE_CONTACTS) {
                return (T)manager.addContacts(contactList);
            }
            return null;
        }

        @Override
        protected void onPostExecute(T object) {
            super.onPostExecute(object);
            if(pDialog != null)
                pDialog.cancel();
            if(operation == READ_CONTACTS) {
                contactList = (List<Contact>)object;
            }
            adapter.updateData(contactList);
        }
    }
}
