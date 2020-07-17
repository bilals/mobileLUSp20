package com.example.bilal.hadathfinal2015;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    // Think of using the following constants to designate
    // which type/list of contacts is requested to be displayed in the other activity
    public static final int PREDEFINED = 0;
    public static final int USER_DEFINED = 1;
    public static final int FAVORITE = 2;
    public static final String CONTACT_CAT_EXTRA_KEY = "CONTACT_CAT_EXTRA_KEY";
    public static ArrayList<Contact> predefinedContactsList = new ArrayList<>();
    public static ArrayList<Contact> userDefinedContactsList = new ArrayList<>();
    private Button predefinedCatBtn;
    private Button userDefinedCatBtn;
    private Button favoriteCatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initializePredefinedContactList();
        predefinedCatBtn = (Button) findViewById(R.id.predefinedCat);
        userDefinedCatBtn = (Button) findViewById(R.id.userDefinedCat);
        favoriteCatBtn = (Button) findViewById(R.id.favoriteCat);
        predefinedCatBtn.setOnClickListener(this);
        userDefinedCatBtn.setOnClickListener(this);
        favoriteCatBtn.setOnClickListener(this);
    }

    private void initializePredefinedContactList() {
        predefinedContactsList.add(new Contact("Red Cross", "140"));
        Contact fireDepartmentContact = new Contact("Fire Department", "175");
        fireDepartmentContact.setIsFavorite(true);
        predefinedContactsList.add(fireDepartmentContact);
        predefinedContactsList.add(new Contact("Internal Security", "112"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save_contacts) {
            // Save the user-defined contacts here to a file.
            // Do not forget to load them on startup to check if it is working fine!
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // you can check which button was clicked as follows:
        if (v == predefinedCatBtn)
            Log.d("Home page event:", "Predefined button was clicked!");
        // Complete this method conveniently
        Intent intent = new Intent(this, ContactsList.class);
        // Fill in the intent the needed data to tell the activity which title
        // and which contacts list it should display
        intent.putExtra(CONTACT_CAT_EXTRA_KEY, PREDEFINED);
        startActivity(intent);
    }
}
