package com.example.hci.hciassignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.hci.hciassignment.R;

public class MainActivity extends AppCompatActivity {

    ListView contactsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsListView = (ListView) findViewById(R.id.listViewContacts);
        contactsListView.setEmptyView(findViewById(R.id.txtNoContactsFound));
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
}
