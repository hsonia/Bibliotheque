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