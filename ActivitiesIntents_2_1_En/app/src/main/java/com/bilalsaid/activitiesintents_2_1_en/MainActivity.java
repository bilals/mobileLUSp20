package com.bilalsaid.activitiesintents_2_1_en;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY = MainActivity.class.getCanonicalName() + ".extra.My_Message_Key";
    // instead of "com.bilalsaid.activitiesintents_2_1_en.extra.My_Message_Key";
    public static final int GET_REPLY_REQUEST_CODE = 0;
    public static final String REPLY_VISIBILITY_STATE_KEY = "VISIBILITY_STATE";
    public static final String REPLY_TEXT_STATE_KEY = "REPLY_TEXT_STATE";
    private TextView replyTextView;
    private TextView replyHeaderTextView;
    private EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replyTextView = findViewById(R.id.text_reply);
        replyHeaderTextView = findViewById(R.id.text_header_reply);
        messageEditText = findViewById(R.id.editText_message);

        if(savedInstanceState != null)
        {
            int previousVisibility = savedInstanceState.getInt(REPLY_VISIBILITY_STATE_KEY);
            replyTextView.setVisibility(previousVisibility);
            replyHeaderTextView.setVisibility(previousVisibility);
            String previousReplyText = savedInstanceState.getString(REPLY_TEXT_STATE_KEY);
            replyTextView.setText(previousReplyText);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(REPLY_VISIBILITY_STATE_KEY, replyTextView.getVisibility());
        outState.putString(REPLY_TEXT_STATE_KEY, replyTextView.getText().toString());
    }


    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String messageToSend = messageEditText.getText().toString();
        intent.putExtra(MESSAGE_KEY, messageToSend);
        //startActivity(intent);
        startActivityForResult(intent, GET_REPLY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the requestCode == GET_REPLY_REQUEST_CODE
        // and check if resultCode is RESULT_OK
        //      then get replyMessage from data,
        //      and then assign it to the text view
        if (requestCode == GET_REPLY_REQUEST_CODE
                && resultCode == RESULT_OK) {
            String replyMessage = data.getStringExtra(SecondActivity.REPLY_KEY);
            replyTextView.setText(replyMessage);
            replyTextView.setVisibility(View.VISIBLE);
            replyHeaderTextView.setVisibility(View.VISIBLE);
        }
        messageEditText.setText("");
    }
}
