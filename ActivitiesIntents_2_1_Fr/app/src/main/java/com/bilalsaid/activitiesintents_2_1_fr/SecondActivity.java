package com.bilalsaid.activitiesintents_2_1_fr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String REPLY_MESSAGE_KEY = SecondActivity.class.getCanonicalName() + ".extra.REPLY_MESSAGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // extraire le message envoye par 1ere activite...
        String message = getIntent().getStringExtra(MainActivity.MESSAGE_KEY);
        TextView messageTextView = findViewById(R.id.text_reply);
        messageTextView.setText(message);
    }

    public void replyToFirstActivity(View view) {
        // on Thursday

        // 0- replyMessage = editTextReply.getText()
        // 1- dataIntent.putExtra(..replyMessage..)
        // 2- setResult(RESULT_OK, dataIntent)
        // 3- finish()
        TextView editTextReply = findViewById(R.id.editText_Reply);
        String replyMessage = editTextReply.getText().toString();
        Intent data = new Intent();
        data.putExtra(REPLY_MESSAGE_KEY, replyMessage);
        setResult(RESULT_OK, data);
        finish();
    }
}
