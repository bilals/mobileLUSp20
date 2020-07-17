package com.example.bilal.hadathfinal2015;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsList extends AppCompatActivity {

    private LinearLayout addNewContactLayout;
    private ArrayList<Contact> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        // Fill in what follows conveniently
        String contactListTitle = "Unable to know which list to display";
        int chosenContactsCategory = getIntent().getIntExtra(HomePage.CONTACT_CAT_EXTRA_KEY, -1);
        contactsList = new ArrayList<>();
        switch (chosenContactsCategory) {
            case HomePage.PREDEFINED:
                initializePredefinedContactList();
                contactListTitle = "Predefined Contacts";
                break;
            case HomePage.USER_DEFINED:
                ;
                break;
            case HomePage.FAVORITE:
                ;
                break;
            default: // do nothing
        }
        TextView contactListTitleTV = (TextView) findViewById(R.id.contacts_list_title);
        contactListTitleTV.setText(contactListTitle);
        final ContactListAdapter contactListAdapter = new ContactListAdapter(this, contactsList);
        RecyclerView contactsListView = findViewById(R.id.contacts_list);
        contactsListView.setAdapter(contactListAdapter);
        contactsListView.setLayoutManager(new LinearLayoutManager(this));
        //contactListAdapter.notifyDataSetChanged();

//        // A part dedicated to the "Enter New Contact" form
//        addNewContactLayout = (LinearLayout) findViewById(R.id.add_new_contact_layout);
//        // The following line causes the "Enter New Contact" form to be hidden by default
//        addNewContactLayout.setVisibility(View.GONE);
//        final EditText newContactNameET = (EditText) findViewById(R.id.new_contact_name);
//        final EditText newPhoneNumberET = (EditText) findViewById(R.id.new_phone_number);
//        Button addBtn = (Button) findViewById(R.id.addBtn);
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Fill in
//            }
//        });
//        Button doneBtn = (Button) findViewById(R.id.doneBtn);
//        doneBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Fill in
//            }
//        });
    }

    private void initializePredefinedContactList() {
        contactsList.add(new Contact("Red Cross", "140"));
        Contact fireDepartmentContact = new Contact("Fire Department", "175");
        fireDepartmentContact.setIsFavorite(true);
        contactsList.add(fireDepartmentContact);
        contactsList.add(new Contact("Internal Security", "112"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_new_contact) {
            addNewContactLayout.setVisibility(View.VISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
