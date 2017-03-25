package com.example.hci.hciassignment.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hci.hciassignment.entity.Contact;
import com.example.hci.hciassignment.R;

import java.io.File;
import java.io.FileOutputStream;

public class ContactForm extends AppCompatActivity {

    EditText firstNameControl;
    EditText lastNameControl;
    EditText phoneNoControl;
    EditText emailControl;
    EditText birthDateControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        firstNameControl = (EditText) findViewById(R.id.first_name);
        lastNameControl = (EditText) findViewById(R.id.last_name);
        phoneNoControl = (EditText) findViewById(R.id.phone_no);
        emailControl = (EditText) findViewById(R.id.email_address);
        birthDateControl = (EditText) findViewById(R.id.birth_date);

        Button button = (Button) findViewById(R.id.add_contact_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String result = new Contact(firstNameControl.getText().toString(), lastNameControl.getText().toString(), phoneNoControl.getText().toString(), emailControl.getText().toString(), birthDateControl.getText().toString()).toString();
                //System.out.println(result);
                writeToFile(result);
            }

            public void writeToFile(String result)
            {
                try {
                    String filename = "Contacts";
                    String path = getBaseContext().getFilesDir().getAbsolutePath()+"/"+ filename;
                    FileOutputStream outputStream;
                    File file = new File(path);
                    if (file.exists())
                    {
                        outputStream = openFileOutput(filename, Context.MODE_APPEND);
                    } else {
                        System.out.println("Nt Exists");
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    }
                    outputStream.write(result.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                readFromFile();
            }

            public void readFromFile()
            {
                /*String filename = "Contacts";
                FileInputStream fis = openFileInput(filename, Context.MODE_PRIVATE);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }*/
            }
        });

    }

}
