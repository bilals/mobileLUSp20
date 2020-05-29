package com.bilalsaid.recyclerview_45_en;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView wordListRecyclerView;
    private ArrayList<String> wordsList;
    private WordListAdapter wordListAdapter;

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
        wordListAdapter = new WordListAdapter(this, wordsList);
        wordListRecyclerView.setAdapter(wordListAdapter);
    }

    private class WordListAdapter extends RecyclerView.Adapter<WordViewHolder> {
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
        public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View wordItemView = layoutInflater.inflate(R.layout.word_list_item, parent, false);
            return new WordViewHolder(wordItemView, this);
        }

        @Override
        public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
            holder.wordItemTextView.setText(wordsList.get(position));
        }

        @Override
        public int getItemCount() {
            return wordsList.size();
        }
    }

    private class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView wordItemTextView;
        private final WordListAdapter wordListAdapter;

        public WordViewHolder(View wordItemView, WordListAdapter wordListAdapter) {
            super(wordItemView);
            wordItemView.setOnClickListener(this);
            this.wordItemTextView = wordItemView.findViewById(R.id.wordItemTextView);
            this.wordListAdapter = wordListAdapter;
        }

        @Override
        public void onClick(View v) {
            int wordPosition = getLayoutPosition();
            String wordAtPosition = wordsList.get(wordPosition);
            wordsList.set(wordPosition, "Clicked! " + wordAtPosition);
            wordListAdapter.notifyDataSetChanged();
        }
    }

    public void addWord(View view) {
        int wordListSize = wordsList.size();
        wordsList.add("Wrod " + (wordListSize + 1));
        // Notify the adapter, that the data has changed.
        wordListAdapter.notifyItemInserted(wordListSize);
        // Scroll to the bottom.
        wordListRecyclerView.smoothScrollToPosition(wordListSize);
    }
}
