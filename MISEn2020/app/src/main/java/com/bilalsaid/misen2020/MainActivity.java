package com.bilalsaid.misen2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // MainActivity.class.getSimpleName()
        Log.d("Greeting", "Hi again M2 students!");

        Button backButton = this.findViewById(R.id.back_button);
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Back was clicked!", Toast.LENGTH_LONG).show();
            }
        };
        backButton.setOnClickListener(myListener);
    }

    public void handleDonutImageClick(View view) {
        Toast.makeText(MainActivity.this, "Donut Image was clicked!", Toast.LENGTH_LONG).show();
    }

}
