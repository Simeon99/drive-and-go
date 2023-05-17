package com.metropolitan.cs330_projekat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.metropolitan.cs330_projekat.db.DatabaseHendler;
import com.metropolitan.cs330_projekat.model.Korisnik;
import com.metropolitan.cs330_projekat.model.Voznja;

import java.util.ArrayList;
import java.util.List;

public class RezervisaneVoznje extends AppCompatActivity {
    List<Voznja> listaVoznji = new ArrayList<Voznja>();
    DatabaseHendler databaseHendler;
    LinearLayout context;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretraga_voznje);

        ActionBar actionBar = getSupportActionBar();


        databaseHendler = new DatabaseHendler(getApplicationContext());
        listView = findViewById(R.id.listaVoznje);
        Korisnik korisnik = getKorisnik();
        listaVoznji = databaseHendler.getVoznjaByKorisnik(korisnik);

        System.out.println("VVVOOOZZZNNNJJEEE:: "+listaVoznji);

        MyAdapter adapter = new MyAdapter(getApplicationContext(),R.layout.red_voznja,listaVoznji);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

    public Korisnik getKorisnik(){
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyKorinikPrefs", Context.MODE_PRIVATE);

        int id = Integer.parseInt(sp.getString("id",""));
        String ime = sp.getString("ime","");
        String prezime = sp.getString("prezima","");
        String email = sp.getString("email","");
        int telefon = Integer.parseInt(sp.getString("telefon","")) ;

//        System.out.println("Iddddd "+id);
//        System.out.println("Imeeeeee"+ime);
//        System.out.println("Preime"+prezime);
//        System.out.println("Imeeeeee"+email);
//        System.out.println("Imeeeeee"+telefon);

        Korisnik k = new Korisnik(ime,prezime,email,telefon);
        k.setId(id);

        return k;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        CreateMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    private void  CreateMenu(Menu menu){
        MenuItem mi1 = menu.add(0,0,0, "Početna");

        mi1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        mi1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                return false;
            }
        });




    }

}
