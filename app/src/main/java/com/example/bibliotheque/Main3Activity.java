package com.example.bibliotheque;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class Main3Activity extends AppCompatActivity {

    private ListView lv;
    DBHelper db;
    SearchView tv;
    ListeLivreAdapter<Books> livreAdapter;
    ArrayList liv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv = (SearchView) findViewById(R.id.search_livre);
        db = new DBHelper(this);
        liv = db.getAllLivres();
        lv = (ListView) findViewById(R.id.rech_mot_cles);
        livreAdapter = new ListeLivreAdapter<Books>(
                getBaseContext(), R.layout.resource_file_livre,liv
        );
        lv.setAdapter(livreAdapter);
        SearchManager sm = (SearchManager) getSystemService(SEARCH_SERVICE);
        tv.setIconifiedByDefault(false);
        tv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
        tv.setSubmitButtonEnabled(true);
        tv.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            public boolean onQueryTextSubmit(String query){
                chercherLivre(query);
                return false;
            }
            public boolean onQueryTextChange(String newText){
                chercherLivre(newText);
                return false;
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle(Html.fromHtml("<font color=\"black\">" + "Recherche d'un livre par titre ou par mots cles" + "</font>"));
        }
    }

    private void chercherLivre(String keyword){
        DBHelper bdd = new DBHelper(getApplicationContext());
        ArrayList<Books> book = bdd.RechercherBooks(keyword);
        if(book != null){
            lv.setAdapter(new ListeLivreAdapter<Books>(getBaseContext(), R.layout.resource_file_livre,book));
        }
    }
}
