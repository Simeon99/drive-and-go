package com.metropolitan.cs330_projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.metropolitan.cs330_projekat.db.DatabaseHendler;
import com.metropolitan.cs330_projekat.model.Voznja;

import java.util.ArrayList;
import java.util.List;

public class PretragaVoznje extends AppCompatActivity {

    List<Voznja> listaVoznji = new ArrayList<Voznja>();
    DatabaseHendler databaseHendler;
    LinearLayout context;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretraga_voznje);
        databaseHendler = new DatabaseHendler(getApplicationContext());
        listView = findViewById(R.id.listaVoznje);

        listaVoznji = databaseHendler.getAllVoznja();

        System.out.println("VVVOOOZZZNNNJJEEE:: "+listaVoznji);

        MyAdapter adapter = new MyAdapter(getApplicationContext(),R.layout.red_voznja,listaVoznji);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
}