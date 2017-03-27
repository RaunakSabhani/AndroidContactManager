package com.example.hci.hciassignment.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.hci.hciassignment.R;
import com.example.hci.hciassignment.entity.Contact;
import com.example.hci.hciassignment.io.ContactsManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final int SAVE_CONTACTS = 1;
    final int READ_CONTACTS = 2;

    ListView contactsListView;
    List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsListView = (ListView) findViewById(R.id.listViewContacts);
        contactsListView.setEmptyView(findViewById(R.id.txtNoContactsFound));

        contactList = new ArrayList<>();
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
            Intent i = new Intent(this, ContactForm.class);
            i.putExtra("action", "add");
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
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
        }
    }
}
