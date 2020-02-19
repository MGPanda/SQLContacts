package com.example.sqlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDB {

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase database;

    public final static String EMP_TABLE = "constants"; // name of table

    public final static String EMP_ID = "_id"; // id value for employee
    public final static String EMP_NAME = "value";  // name of employee

    private static final String[] items = {"Gravity: Death Star I - 3.53036e-07", "Gravity: Earth - 9.80685", "Gravity: Jupiter - 23.12",
            "Gravity: Mars - 3.71", "Gravity: Mercury - 3.7",
            "Gravity: Moon - 1.6", "Gravity: Neptune - 11", "Gravity: Pluto - 0.6", "Gravity: Saturn - 8.96", "Gravity: Sun - 275",
            "Gravity: The Island - 4.81516", "Gravity: Uranus - 8.69", "Gravity: Venus - 8.87"};

    /**
     * @param context
     */
    public MyDB(Context context) {
        dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public long createRecords(int id, String name) {
        ContentValues values = new ContentValues();
        values.put(EMP_ID, id);
        values.put(EMP_NAME, name);
        return database.insert(EMP_TABLE, null, values);
    }

    public void insertItems() {
        for (int i = 0; i < items.length; i++) {
            createRecords(i, items[i]);
        }
    }

    public Cursor selectRecords() {
        String[] cols = new String[]{EMP_ID, EMP_NAME};
        Cursor mCursor = database.query(true, EMP_TABLE, cols, null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}