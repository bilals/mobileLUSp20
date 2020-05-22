package com.bilalsaid.recyclerview_45_en;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView wordListRecyclerView;
    private ArrayList<String> wordsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordListRecyclerView = findViewById(R.id.wordListRecyclerView);
        wordListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        wordsList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            wordsList.add("Word " + (i+1));
        }
        wordListRecyclerView.setAdapter(new WordListAdapter(this, wordsList));
    }

    private class WordListAdapter extends RecyclerView.Adapter {
        private final Context context;
        private final ArrayList<String> wordsList;
        private final LayoutInflater layoutInflater;

        public WordListAdapter(Context context, ArrayList<String> wordsList) {
            this.context = context;
            this.wordsList = wordsList;
            layoutInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null ;//new WordViewHolder(this, itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return wordsList.size();
        }
    }
}
