package com.example.bibliotheque;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.DialogInterface;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.SearchView;

import android.widget.Toast;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {

    private ListView lv;
    DBHelper db;
    SearchView tv;
    ArrayList liv;
    ListeLivreAdapter <Books> livreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        tv = (SearchView) findViewById(R.id.btn_rech);
        tv.setIconifiedByDefault(false);
        db = new DBHelper(this);
        lv = (ListView) findViewById(R.id.lvv);
        updateView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                supprimer(view.getId());
            }

        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle(Html.fromHtml("<font color=\"black\">" + "Suppression d'un livre" + "</font>"));
        }
    }
    public void updateView(){
       liv = db.getAllLivres();
        livreAdapter = new ListeLivreAdapter<Books>(getBaseContext(), R.layout.resource_file_livre,liv);
       lv.setAdapter(livreAdapter);
        SearchManager sm = (SearchManager) getSystemService(SEARCH_SERVICE);
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

    }
    private void chercherLivre(String keyword){
        liv = db.RechercherBooks(keyword);
        if(liv != null){
            lv.setAdapter(new ListeLivreAdapter<Books>(getBaseContext(), R.layout.resource_file_livre,liv));
        }
    }

    public void supprimer (final int id){
        AlertDialog.Builder algertDialogBuilder = new AlertDialog.Builder(this);
        algertDialogBuilder.setMessage("Voulez vous supprimer ce livre ?");
        algertDialogBuilder.setCancelable(false);
        algertDialogBuilder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.deleteBook(id);
                Toast.makeText(getBaseContext(), "Livre supprimé.", Toast.LENGTH_LONG).show();
                if(tv.getQuery()!= null)
                    liv = db.RechercherBooks(tv.getQuery().toString());
                else
                    liv = db.getAllLivres();
                if(liv != null){
                    lv.setAdapter(new ListeLivreAdapter<Books>(getBaseContext(), R.layout.resource_file_livre,liv));
                }
            }
        });
        algertDialogBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        algertDialogBuilder.create();
        algertDialogBuilder.show();
    }
}
