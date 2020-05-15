package com.bilalsaid.activitiesintents_2_1_fr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY = MainActivity.class.getName() + ".extra.MESSAGE_KEY";
    public static final int REPLY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                && resultCode == RESULT_OK)
        {
            String replyMessage = data.getStringExtra(SecondActivity.REPLY_MESSAGE_KEY);
            TextView replyTextView = findViewById(R.id.text_reply);
            replyTextView.setText(replyMessage);
            TextView replyHeaderTextView = findViewById(R.id.text_reply_header);
            replyHeaderTextView.setVisibility(View.VISIBLE);
            replyTextView.setVisibility(View.VISIBLE);
        }

    }
}
