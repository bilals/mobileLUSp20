package com.bilalsaid.activitiesintents_2_1_en;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String REPLY_KEY = SecondActivity.class.getCanonicalName() + ".extra.REPLY_KEY";
    private EditText replyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        replyEditText = findViewById(R.id.editText_reply);

        String myMessage = getIntent().getStringExtra(MainActivity.MESSAGE_KEY);
        TextView messageTextView = findViewById(R.id.text_message);
        messageTextView.setText(myMessage);
    }

    public void returnReply(View view) {
        Intent data = new Intent();
        String replyMessage = replyEditText.getText().toString();
        data.putExtra(REPLY_KEY, replyMessage);
        setResult(RESULT_OK, data);
        finish();
    }
}
