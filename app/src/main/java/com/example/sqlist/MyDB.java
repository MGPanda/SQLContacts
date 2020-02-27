package com.example.sqlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDB {

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase database;

    public final static String EMP_TABLE = "contacts"; // name of table

    public final static String CON_ID = "_id"; // id value for employee
    public final static String CON_NAME = "name";  // name of employee
    public final static String CON_SURNAME = "surname";
    public final static String CON_NUMBER = "number";
    public final static String CON_FULLNAME = "fullname";

    /**
     * @param context
     */
    public MyDB(Context context) {
        dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public long createRecords(int id, String name, String surname, int number) {
        if (id == 0) {
            Cursor c = selectRecords();
            c.moveToLast();
            id = c.getInt(0)+1;
        }
        ContentValues values = new ContentValues();
        values.put(CON_ID, id);
        values.put(CON_NAME, name);
        values.put(CON_SURNAME, surname);
        values.put(CON_NUMBER, number);
        values.put(CON_FULLNAME, name + " " + surname);
        return database.insert(EMP_TABLE, null, values);
    }

    public void insertContacts() {
        createRecords(1, "John", "J. Carmack", 42069);
        createRecords(2, "Tomeu", "Penya", 12345);
    }

    public Cursor selectRecords() {
        String[] cols = new String[]{CON_ID, CON_NAME, CON_SURNAME, CON_NUMBER, CON_FULLNAME};
        Cursor mCursor = database.query(true, EMP_TABLE, cols, null
                , null, null, null, "name asc", null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public void deleteItem(String s) {
        database.delete(EMP_TABLE, "fullname = '" + s + "'", null);
    }

    public long updateItem(String name, String surname, int number) {
        ContentValues values = new ContentValues();
        values.put(CON_NAME, name);
        values.put(CON_SURNAME, surname);
        values.put(CON_NUMBER, number);
        values.put(CON_FULLNAME, name + " " + surname);
        return database.update(EMP_TABLE, values, "number = " + number, null);
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}