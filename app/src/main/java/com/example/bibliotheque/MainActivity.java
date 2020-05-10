package com.example.bibliotheque;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db = new DBHelper(this);
        db.insertBooks("tit1","aut1","mot1","resume resume resume 11111");
        db.insertBooks("tit2","aut2","mot2","resume resume resume 22222");
        db.insertBooks("tit3","aut3","mot3","resume resume resume 33333");
        db.insertBooks("tit4","aut4","mot4","resume resume resume 44444");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle(Html.fromHtml("<font color=\"black\">" + "Bibliothèque" + "</font>"));
        }
    }

    public void ecran1(View view) {
        Intent myIntent = new Intent(getBaseContext(), Main2Activity.class);
        startActivityForResult(myIntent, 0);
    }

    public void ecran2(View view) {
        Intent myIntent = new Intent(getBaseContext(), Main3Activity.class);
        startActivityForResult(myIntent, 0);
    }

    public void ecran3(View view) {
        Intent myIntent = new Intent(getBaseContext(), Main4Activity.class);
        startActivityForResult(myIntent, 0);
    }

    public void ecran4(View view) {
        Intent myIntent = new Intent(getBaseContext(), Main5Activity.class);
        startActivityForResult(myIntent, 0);
    }
}