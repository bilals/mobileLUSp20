package com.bilalsaid.activitiesintents_2_1_fr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY = MainActivity.class.getName() + ".extra.MESSAGE_KEY";
    public static final int REPLY_REQUEST_CODE = 1;
    public static final String VISIBILITY_STATE_KEY = "VISIBILITY_STATE_KEY";
    public static final String REPLY_STATE_KEY = "REPLY_STATE_KEY";
    private TextView replyTextView;
    private TextView replyHeaderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Activity Lifecycle", "onCreate");
        replyTextView = findViewById(R.id.text_reply);
        replyHeaderTextView = findViewById(R.id.text_reply_header);

        if (savedInstanceState != null) { // First time launch => savedInstanceState == null
            // To restore the previous execution context
            int previousVisibility = savedInstanceState.getInt(VISIBILITY_STATE_KEY);
            replyTextView.setVisibility(previousVisibility);
            replyHeaderTextView.setVisibility(previousVisibility);
            replyTextView.setText(savedInstanceState.getString(REPLY_STATE_KEY));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(VISIBILITY_STATE_KEY, replyTextView.getVisibility());
        outState.putString(REPLY_STATE_KEY, replyTextView.getText().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Activity Lifecycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity Lifecycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity Lifecycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Activity Lifecycle", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Activity Lifecycle", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity Lifecycle", "onDestroy");
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        EditText messageToSendEditText = findViewById(R.id.editText_main);
        String messageToSend = messageToSendEditText.getText().toString();
        intent.putExtra(MESSAGE_KEY, messageToSend);
        // startActivity(intent);
        startActivityForResult(intent, REPLY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the requestCode == REPLY_REQUEST_CODE
        // check if the resultCode is RESULT_OK
        //      Then replyMessage = data.getExtra(REPLY_MESSAGE_KEY)
        //           text_reply.setText(replyMessage)

        if (requestCode == REPLY_REQUEST_CODE
                && resultCode == RESULT_OK) {
            String replyMessage = data.getStringExtra(SecondActivity.REPLY_MESSAGE_KEY);
            replyTextView.setText(replyMessage);
            replyHeaderTextView.setVisibility(View.VISIBLE);
            replyTextView.setVisibility(View.VISIBLE);
        }

    }
}
