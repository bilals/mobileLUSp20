package com.bilalsaid.textviewscrollview_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView testTextView = findViewById(R.id.testTextView);
        int count = 13;
        //testTextView.setText("" + count);
    }
}
