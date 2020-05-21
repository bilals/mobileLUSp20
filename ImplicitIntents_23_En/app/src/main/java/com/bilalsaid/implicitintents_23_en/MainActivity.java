package com.bilalsaid.implicitintents_23_en;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openWebsite(View view) {
        EditText websiteEditText = findViewById(R.id.website_edittext);
        String uriString = websiteEditText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(this,"Oops! Seems you don't have a browser", Toast.LENGTH_LONG);
    }

    public void openLocation(View view) {
    }

    public void shareText(View view) {
    }
}
