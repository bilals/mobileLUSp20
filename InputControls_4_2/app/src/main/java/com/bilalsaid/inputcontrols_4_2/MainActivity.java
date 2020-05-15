package com.bilalsaid.inputcontrols_4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView helloMsgTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloMsgTextView = findViewById(R.id.helloMsgTextView);

        Spinner nameChoiceSpinner = findViewById(R.id.nameChoiceSpinner);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.students_names,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        nameChoiceSpinner.setAdapter(adapter);

        nameChoiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                String chosenStudentName = adapter.getItemAtPosition(position).toString();
                helloMsgTextView.setText("Hello " + chosenStudentName + "!");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
                // handle later on...
            }
        });
    }
}
