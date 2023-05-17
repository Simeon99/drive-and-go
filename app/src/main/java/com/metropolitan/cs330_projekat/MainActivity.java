package com.metropolitan.cs330_projekat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.metropolitan.cs330_projekat.db.DatabaseHendler;
import com.metropolitan.cs330_projekat.model.Korisnik;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextWatcher{

    DatabaseHendler db;

    private Button btn ;
    private EditText etIme,etPrezime, etEmial,etTelefon;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        db = new DatabaseHendler(getApplicationContext());

        btn = findViewById(R.id.btn_potvrda);

        etIme = findViewById(R.id.et_ime);
        etPrezime = findViewById(R.id.et_prezime);
        etEmial = findViewById(R.id.et_email);
        etTelefon = findViewById(R.id.et_telefon);

        etIme.addTextChangedListener( this);

        sp = getSharedPreferences("MyKorinikPrefs", Context.MODE_PRIVATE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });

    }



    public void onSubmit(){

        String ime = etIme.getText().toString();
        String prezime = etPrezime.getText().toString();
        String email = etEmial.getText().toString();
        int telefon = Integer.parseInt(etTelefon.getText().toString()) ;
        Korisnik korisnik = new Korisnik(ime,prezime,email,telefon);
        System.out.println("AAAAAAAAAAAAAAAAA"+ime+" "+ prezime+ " "+ email+" "+ telefon);

        int idKorisnika = db.addKorisnik(korisnik);

        korisnik.setId(idKorisnika);

        List<Korisnik> korisniks = new ArrayList<>();

        korisniks = db.getAllKorisnici();

        System.out.println("KKKKK"+korisniks);

        System.out.println("IDDD: "+idKorisnika);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("id", Integer.toString(idKorisnika));
        editor.putString("ime", ime);
        editor.putString("prezima", prezime);
        editor.putString("email", email);
        editor.putString("telefon", Integer.toString(telefon) );
        editor.commit();

        /*SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(korisnik);
        System.out.println("BBBBB"+json);
        prefsEditor.putString("korisnik", json);
        prefsEditor.commit();*/

        Intent i = new Intent(this,HomeActivity.class);
        i.putExtra("korinik",korisnik);
        startActivity(i);


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
       String ime= etIme.getText().toString();

    }
}