package com.bilalsaid.implicitintents_23_fr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText locationEditText;
    private EditText shareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        locationEditText = findViewById(R.id.location_edittext);
        shareEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        String uriString = mWebsiteEditText.getText().toString();
        Uri websiteURI = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, websiteURI);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(this,"It seems you don't have web browser!", Toast.LENGTH_LONG).show();
    }

    public void openLocation(View view) {
        String uriString = locationEditText.getText().toString();
        Uri locationURI = Uri.parse("geo:0,0?q=" + uriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, locationURI);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(this,"It seems you don't have a map viewer!", Toast.LENGTH_LONG).show();
    }

    public void shareText(View view) {
        String mimeType = "text/plain";
        String textToShare = shareEditText.getText().toString();
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(textToShare)
                .startChooser();
    }
}
