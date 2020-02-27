package com.example.sqlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactDetail extends AppCompatActivity {
    private EditText name, surname, number;
    private TextView fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_constant);
        setResult(2,null);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        number = findViewById(R.id.number);
        fullname = findViewById(R.id.fullName);
        String name2 = getIntent().getStringExtra("NAME");
        String surname2 = getIntent().getStringExtra("SURNAME");
        fullname.setText(getIntent().getStringExtra("FULLNAME"));
        name.setText(name2);
        surname.setText(surname2);
        int num = getIntent().getIntExtra("NUMBER",0);
        number.setText(String.valueOf(num));
    }

    public void editContact(View view) {
        Button b = (Button) view;
        if (name.isEnabled()) {
            b.setText("Edit contact");
            name.setEnabled(false);
            surname.setEnabled(false);
            number.setEnabled(false);
            Intent i = new Intent();
            i.putExtra("NAME",name.getText().toString());
            i.putExtra("SURNAME",surname.getText().toString());
            i.putExtra("NUMBER",Integer.parseInt(number.getText().toString()));
            setResult(0,i);
            finish();

        } else {
            b.setText("Save changes");
            name.setEnabled(true);
            surname.setEnabled(true);
            number.setEnabled(true);
        }
    }

    public void deleteContact(View view) {
        setResult(1, null);
        finish();
    }
}
