package com.android.example.wordlistsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WordListOpenHelper extends SQLiteOpenHelper {

    private static final String WORD_LIST_DB_Name = "WordListDB";
    private static final int DB_VERSION = 1;
    private static final String WORD_LIST_TABLE_NAME = "WordListTable";
    private static final String ID_COL_NAME = "_id";
    private static final String WORD_COL_NAME = "word";
    private static final String[] COLUMNS = {ID_COL_NAME, WORD_COL_NAME};
    private SQLiteDatabase readableDatabase;
    private SQLiteDatabase writableDatabase;


    public WordListOpenHelper(@Nullable Context context) {
        super(context, WORD_LIST_DB_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + WORD_LIST_TABLE_NAME + " (" +
                ID_COL_NAME + " INTEGER PRIMARY KEY," +
                WORD_COL_NAME + " TEXT);");
        fillDatabaseWithData(db);
    }

    public void fillDatabaseWithData(SQLiteDatabase db) {
        String[] words = {"Android", "Adapter", "ListView", "AsyncTask", "Android Studio",
                "SQLiteDatabase", "SQLOpenHelper", "Data model", "ViewHolder",
                "Android Performance", "OnClickListener"};

        // Create a container for the data.
        ContentValues values = new ContentValues();

        for (int i=0; i < words.length;i++) {
            values.put(WORD_COL_NAME, words[i]);
            db.insert(WORD_LIST_TABLE_NAME, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WORD_LIST_TABLE_NAME);
        onCreate(db);
    }

    public WordItem getWordAtPosition(int position) {
        String query = "SELECT  * FROM " + WORD_LIST_TABLE_NAME +
                " ORDER BY " + WORD_COL_NAME + " ASC " +
                "LIMIT " + position + ",1";
        WordItem result = new WordItem();
        if(readableDatabase == null) {
            readableDatabase = getReadableDatabase();
        }
        Cursor cursor = readableDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        int wordID = cursor.getInt(cursor.getColumnIndex(ID_COL_NAME));
        String wordText = cursor.getString(cursor.getColumnIndex(WORD_COL_NAME));
        result.setId(wordID);
        result.setWord(wordText);
        cursor.close();
        return result;
    }

    /**
     * Gets the number of rows in the word list table.
     *
     * @return The number of entries in WORD_LIST_TABLE.
     */
    public long count() {
        if (readableDatabase == null) {readableDatabase = getReadableDatabase();}
        return DatabaseUtils.queryNumEntries(readableDatabase, WORD_LIST_TABLE_NAME);
    }

    public long insert(String word) {
        ContentValues row = new ContentValues();
        row.put(WORD_COL_NAME, word);
        if(writableDatabase==null) {writableDatabase = getWritableDatabase();}
        return writableDatabase.insert(WORD_LIST_TABLE_NAME, null, row);
    }
}
