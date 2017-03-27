package com.example.hci.hciassignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hci.hciassignment.R;
import com.example.hci.hciassignment.common.Constants;
import com.example.hci.hciassignment.entity.Contact;

public class ContactFormActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextFirstName, editTextLastName, editTextPhoneNumber, editTextEmailAddress, editTextBirthDate;
    Button btnOperation;
    private int operation;
    private int returnStatus;
    private int id;
    Contact contact;
    Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        editTextBirthDate = (EditText) findViewById(R.id.editTextBirthDate);
        btnOperation = (Button) findViewById(R.id.btnOperation);

        returnStatus = Constants.CONTACT_INTENT_FAILED_OPERATION_VALUE;

        data = new Intent();

        if(getIntent() != null && getIntent().hasExtra(Constants.CONTACT_OPERATION_INTENT_KEY)) {
            operation = getIntent().getIntExtra(Constants.CONTACT_OPERATION_INTENT_KEY, Constants.CONTACT_INTENT_DEFAULT_OPERATION_VALUE);
            if(operation == Constants.CONTACT_INTENT_ADD_OPERATION_VALUE) {
                btnOperation.setText(R.string.contact_operation_add);
                contact = new Contact();
                setTitle(R.string.title_activity_contact_form_add);
            }
            else if(operation == Constants.CONTACT_INTENT_MODIFY_OPERATION_VALUE) {
                btnOperation.setText(R.string.contact_operation_update);
                setTitle(R.string.title_activity_contact_form_details);
                if(getIntent().hasExtra(Constants.CONTACT_ENTITY_INTENT_KEY)) {
                    contact = getIntent().getParcelableExtra(Constants.CONTACT_ENTITY_INTENT_KEY);
                }
                if(getIntent().hasExtra(Constants.CONTACT_INDEX_INTENT_KEY)) {
                    id = getIntent().getIntExtra(Constants.CONTACT_INDEX_INTENT_KEY, 0);
                }
            }
        }
        editTextFirstName.setText(contact.getFirstName());
        editTextLastName.setText(contact.getLastName());
        editTextPhoneNumber.setText(contact.getPhoneNo());
        editTextEmailAddress.setText(contact.getEmail());
        editTextBirthDate.setText(contact.getBirthDate());
        btnOperation.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        populateReturnIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(operation == Constants.CONTACT_INTENT_MODIFY_OPERATION_VALUE) {
            getMenuInflater().inflate(R.menu.contact_view_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete_action) {
            returnStatus = Constants.CONTACT_INTENT_DELETE_OPERATION_VALUE;
            populateReturnIntent();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateContact() {
        contact.setFirstName(editTextFirstName.getText().toString());
        contact.setLastName(editTextLastName.getText().toString());
        contact.setPhoneNo(editTextPhoneNumber.getText().toString());
        contact.setEmail(editTextEmailAddress.getText().toString());
        contact.setBirthDate(editTextBirthDate.getText().toString());
    }

    private void populateReturnIntent() {
        populateContact();
        data.putExtra(Constants.CONTACT_ENTITY_INTENT_KEY, contact);
        data.putExtra(Constants.CONTACT_INDEX_INTENT_KEY, id);
        if (getParent() == null) {
            setResult(returnStatus, data);
        }
        else {
            getParent().setResult(returnStatus, data);
        }
    }

    private boolean validate() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOperation:
                if(validate()) {
                    if (operation == Constants.CONTACT_INTENT_ADD_OPERATION_VALUE) {
                        returnStatus = Constants.CONTACT_INTENT_ADD_OPERATION_VALUE;
                    } else if (operation == Constants.CONTACT_INTENT_MODIFY_OPERATION_VALUE) {
                        returnStatus = Constants.CONTACT_INTENT_UPDATE_OPERATION_VALUE;
                    }
                    populateReturnIntent();
                    finish();
                }
                break;
        }
    }
}
