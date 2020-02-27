package com.example.sqlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyDB mydb;
    private ListView lv;
    private static int max = 0;
    private Intent i;
    private String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selected = "";
        lv = findViewById(R.id.listView);
        mydb = new MyDB(this);
        MyDatabaseHelper mdh = new MyDatabaseHelper(this);
        mdh.onCreate(mydb.getDatabase());
        mydb.insertContacts();
        setAdapter();
        i = new Intent(this, ContactDetail.class);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = mydb.selectRecords();
                TextView tv = (TextView) view;
                do {
                    if (c.getString(4).equals(tv.getText())) {
                        selected = c.getString(4);
                        i.putExtra("NAME",c.getString(1));
                        i.putExtra("SURNAME",c.getString(2));
                        i.putExtra("NUMBER",c.getInt(3));
                        i.putExtra("FULLNAME",selected);
                    }
                } while (c.moveToNext());
                startActivityForResult(i, 0);
            }
        });
    }

    public void setAdapter() {
        Cursor c = mydb.selectRecords();
        max = c.getCount();
        String[] from = new String[]{"fullname"};
        int[] to = new int[]{android.R.id.text1};
        SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, c, from, to, 0);
        lv.setAdapter(sca);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            String name = data.getStringExtra("NAME");
            String surname = data.getStringExtra("SURNAME");
            int number = data.getIntExtra("NUMBER", 0);
            mydb.updateItem(name, surname, number);
        } else if (resultCode == 1) {
            mydb.deleteItem(selected);
        } else if (resultCode == 3) {
            String name = data.getStringExtra("NAME");
            String surname = data.getStringExtra("SURNAME");
            int number = data.getIntExtra("NUMBER", 0);
            mydb.createRecords(0, name, surname, number);
        }
        setAdapter();
    }

    public void addContact(View view) {
        Intent i = new Intent(this, NewContact.class);
        startActivityForResult(i, 0);
    }
}
