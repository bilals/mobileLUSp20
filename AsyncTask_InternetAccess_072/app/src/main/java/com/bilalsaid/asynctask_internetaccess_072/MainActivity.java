package com.bilalsaid.asynctask_internetaccess_072;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = (EditText) findViewById(R.id.bookInput);
        mTitleText = (TextView) findViewById(R.id.titleText);
        mAuthorText = (TextView) findViewById(R.id.authorText);
    }

    public void searchBooks(View view) {
        // Get the search string from the input field.
        String queryString = mBookInput.getText().toString();
        new FetchBook().execute(new String[]{queryString});
    }

    private class FetchBook extends AsyncTask<String, Void, String> {

        // Base URL for Books API.
        private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
        // Parameter for the search string.
        private static final String QUERY_PARAM = "q";
        // Parameter that limits search results.
        private static final String MAX_RESULTS = "maxResults";
        // Parameter to filter by print type.
        private static final String PRINT_TYPE = "printType";

        @Override
        protected String doInBackground(String... queryString) {
            // Create a URI with the given queryString
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString[0])
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            HttpURLConnection connection = null;
            // Open a HttpURLConnection
            try {
                URL requestURL = new URL(builtURI.toString());
                connection =
                        (HttpURLConnection) requestURL.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                StringBuilder result = new StringBuilder();
                // Get the input stream of the open connection
                Scanner inFromServer = new Scanner(connection.getInputStream());
                // Read the input stream using a Scanner, DataInputStream, BufferReader...
                while (inFromServer.hasNext()) {
                    result.append(inFromServer.nextLine()).append("\n");
                }
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return "No Response";
        }

        @Override
        protected void onPostExecute(String queryResult) {
            super.onPostExecute(queryResult);
            // parse the JSON string
            String title = "Unkown Title";
            String authors = "Unkonw Authors";
            try {
                JSONObject results = new JSONObject(queryResult);
                JSONArray items = results.getJSONArray("items");
                JSONObject firstBook = items.getJSONObject(0);
                JSONObject firstBookVolumeInfo = firstBook.getJSONObject("volumeInfo");
                title = firstBookVolumeInfo.getString("title");
                authors = firstBookVolumeInfo.getString("authors");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // display the title & authors in the corresponding TextViews
            mTitleText.setText(title);
            mAuthorText.setText(authors);
        }
    }
}