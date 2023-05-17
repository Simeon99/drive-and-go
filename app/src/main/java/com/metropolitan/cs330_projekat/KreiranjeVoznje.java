package com.metropolitan.cs330_projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.metropolitan.cs330_projekat.db.DatabaseHendler;
import com.metropolitan.cs330_projekat.model.Korisnik;
import com.metropolitan.cs330_projekat.model.Voznja;

import java.util.Calendar;

public class KreiranjeVoznje extends AppCompatActivity {
    DatabaseHendler db;
    DatePickerDialog  picker;
    TimePickerDialog timePickerDialog;
    EditText etStart, etCilj, etAutomobil, etBrojSedista, etDatum, etVreme,etGeoSirina, etGeoDuzina;
    Calendar cldr,calendar;
    Button btnSubmit;
    int day ;
    int month ;
    int year ;
    int hour;
    int min ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kreiranje_voznje);

        db = new DatabaseHendler(getApplicationContext());

        etStart = findViewById(R.id.et_start);
        etCilj = findViewById(R.id.et_cilj);
        etAutomobil = findViewById(R.id.et_automobil);
        etBrojSedista = findViewById(R.id.et_brojSedista);
        etDatum = findViewById(R.id.et_datum);
        etVreme = findViewById(R.id.et_Vreme);
        etGeoSirina = findViewById(R.id.et_geoSirina);
        etGeoDuzina = findViewById(R.id.et_geoDuzina);
        btnSubmit = findViewById(R.id.btn_submit_create_voznja);

        etDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cldr = Calendar.getInstance();
                day = cldr.get(Calendar.DAY_OF_MONTH);
                month = cldr.get(Calendar.MONTH);
                year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(KreiranjeVoznje.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etDatum.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month+1, day);
                picker.show();
            }
        });

        etVreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                min = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(KreiranjeVoznje.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etVreme.setText(hourOfDay+" : "+minute);
                    }
                },hour,min,true);
                timePickerDialog.show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datum = day+"/"+month+"/"+year;
                String vreme = hour+":"+min;
                System.out.println("AAAAAAAAAAA");
                System.out.println();

                SharedPreferences sp = getApplicationContext().getSharedPreferences("MyKorinikPrefs", Context.MODE_PRIVATE);

                int id = Integer.parseInt(sp.getString("id",""));
                String ime = sp.getString("ime","");
                String prezime = sp.getString("prezima","");
                String email = sp.getString("email","");
                int telefon = Integer.parseInt(sp.getString("telefon","")) ;

                System.out.println("Iddddd "+id);
                System.out.println("Imeeeeee"+ime);
                System.out.println("Preime"+prezime);
                System.out.println("Imeeeeee"+email);
                System.out.println("Imeeeeee"+telefon);

                Korisnik k = new Korisnik(ime,prezime,email,telefon);
                k.setId(id);
                System.out.println("KOOORRIISNIKK"+k);

                Voznja voznja = new Voznja(etStart.getText().toString(),
                        etCilj.getText().toString(),
                        etAutomobil.getText().toString(),
                        Integer.parseInt(etBrojSedista.getText().toString()),
                        Double.parseDouble(etGeoSirina.getText().toString()),
                        Double.parseDouble(etGeoDuzina.getText().toString()),
                        datum,vreme,k);
                System.out.println("Podaci: "+voznja.toString());

               int idVoznje= db.addVoznja(voznja);
                System.out.println("IIIIIDDDDD: "+idVoznje);
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);


            }
        });

    }
}