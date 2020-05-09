package com.example.bibliotheque;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;

public class Main2Activity extends AppCompatActivity {

    private ListView lv;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new DBHelper(this);
        ArrayList liv = db.getAllLivres();
        lv = (ListView) findViewById(R.id.list_view_livre);
        ListeLivreAdapter<Books> livreAdapter = new ListeLivreAdapter<Books>(getBaseContext(), R.layout.resource_file_livre,liv);
        lv.setAdapter(livreAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle(Html.fromHtml("<font color=\"black\">" + "Liste des livres" + "</font>"));
        }
    }
}
