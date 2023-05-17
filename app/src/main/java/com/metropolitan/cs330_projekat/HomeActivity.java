package com.metropolitan.cs330_projekat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.metropolitan.cs330_projekat.model.Korisnik;

public class HomeActivity extends AppCompatActivity {

    private NadjiVoznjuFragment njidiVoziloFragment;
    private DodajVoznjuFragment dodajVoznjuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();

        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyKorinikPrefs",Context.MODE_PRIVATE);

        String ime = sp.getString("ime","");
        String prezime = sp.getString("prezima","");
        String email = sp.getString("email","");
        int telefon = Integer.parseInt(sp.getString("telefon","")) ;

        System.out.println("Imeeeeee"+ime);
        System.out.println("Preime"+prezime);
        System.out.println("Imeeeeee"+email);
        System.out.println("Imeeeeee"+telefon);


        njidiVoziloFragment = new NadjiVoznjuFragment();
        dodajVoznjuFragment = new DodajVoznjuFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fr_nadjiVoznju,njidiVoziloFragment)
                                                       .replace(R.id.fr_dodajVoznju,dodajVoznjuFragment).commit();

        Intent i = getIntent();

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
        MenuItem mi1 = menu.add(0,0,0, "Moje rezervacije");
        mi1.setIcon(R.drawable.ic_list_foreground);
        mi1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        mi1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Toast.makeText(getApplicationContext(),"Moje rezervacije.",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),RezervisaneVoznje.class);
                startActivity(i);
                return false;
            }
        });

        MenuItem mi2 = menu.add(0,0,0, "O nama");
        mi2.setIcon(R.drawable.ic_info_foreground);
        mi2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        mi2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(),"O nama.",Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(),ONama.class);
                startActivity(i);
                return false;
            }
        });


    }

}