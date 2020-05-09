package com.example.bibliotheque;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    EditText t,a,m,r;
    String ti,au,mo,re;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        db = new DBHelper(this);
        t = (EditText) findViewById(R.id.tit);
        a = (EditText) findViewById(R.id.aut);
        m = (EditText) findViewById(R.id.mcls);
        r = (EditText) findViewById(R.id.resm);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle(Html.fromHtml("<font color=\"black\">" + "Insertion d'un livre" + "</font>"));
        }
    }

    public void inserer(View view) {
        ti = t.getText().toString();
        au = a.getText().toString();
        mo = m.getText().toString();
        re = r.getText().toString();
        if((t.equals(""))||(au.equals(""))||(mo.equals("")||(re.equals("")))){
            //
        }
        else{
            db.insertBooks(ti,au,mo,re);
            Toast.makeText(getBaseContext(), "Livre inséré.", Toast.LENGTH_LONG).show();
            t.setText("");
            a.setText("");
            m.setText("");
            r.setText("");
        }
    }
}
