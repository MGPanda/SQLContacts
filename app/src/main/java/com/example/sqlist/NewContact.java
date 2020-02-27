package com.example.sqlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewContact extends AppCompatActivity {
    private EditText name, surname, number;
    private TextView fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        setResult(2,null);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        number = findViewById(R.id.number);
        fullname = findViewById(R.id.fullName);
    }
    public void saveContact(View view) {
        Intent i = new Intent();
        i.putExtra("NAME",name.getText().toString());
        i.putExtra("SURNAME",surname.getText().toString());
        i.putExtra("NUMBER",Integer.parseInt(number.getText().toString()));
        setResult(3,i);
        finish();
    }
}
