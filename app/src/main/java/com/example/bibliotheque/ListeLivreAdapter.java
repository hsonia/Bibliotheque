package com.example.bibliotheque;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListeLivreAdapter<B> extends ArrayAdapter<Books> {

    public ListeLivreAdapter(Context context, int resource, ArrayList<Books> objects){
        super(context,resource,objects);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.resource_file_livre,null);
        }
        Books book = getItem(position);
        if(book != null){
            TextView tv1 = (TextView) v.findViewById(R.id.titrelivre);
            TextView tv2 = (TextView) v.findViewById(R.id.auteurlivre);
            TextView tv3 = (TextView) v.findViewById(R.id.motCleslivre);
            TextView tv4 = (TextView) v.findViewById(R.id.resumelivre) ;

            tv1.setText(book.getTitre());
            tv2.setText("auteur: "+book.getAuteur());
            tv3.setText("Mots cles: "+book.getMotCles());
            tv4.setText("resume: "+book.getResume());
            v.setId(book.getId());
        }
        return v;
    }
}
