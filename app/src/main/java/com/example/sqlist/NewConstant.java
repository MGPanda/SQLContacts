package com.example.sqlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewConstant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_constant);
    }
    public void returnToMain(View view) {
        EditText name = findViewById(R.id.name);
        EditText gravity = findViewById(R.id.gravity);
        String constant = name.getText().toString() + " - " + gravity.getText().toString();
        Intent i = new Intent();
        i.putExtra("CONSTANT",constant);
        setResult(0,i);
        finish();
    }
}
