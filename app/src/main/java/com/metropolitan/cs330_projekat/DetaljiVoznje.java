package com.metropolitan.cs330_projekat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.metropolitan.cs330_projekat.db.DatabaseHendler;
import com.metropolitan.cs330_projekat.model.Korisnik;
import com.metropolitan.cs330_projekat.model.Rezervacija;
import com.metropolitan.cs330_projekat.model.Voznja;

import java.util.List;

public class DetaljiVoznje extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    Voznja voznja;

    TextView tvStart, tvCilj,tvAutomobil,tevBrojSedista, tvDatum, tvVreme, tvImeKorisnika, tvPrezimeKorisnika, tvEmail, tvTelefon;

    DatabaseHendler databaseHendler;

    Button btnRezervisi;
    ImageButton btnSendEmial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_voznje);

        databaseHendler = new DatabaseHendler(getApplicationContext());

        btnRezervisi = findViewById(R.id.btn_rezervisi);
        btnSendEmial = findViewById(R.id.btn_email);

        tvStart=  findViewById(R.id.txStart);
        tvCilj=  findViewById(R.id.txCilj);
        tvAutomobil=  findViewById(R.id.txAutomobil);
        tevBrojSedista= findViewById(R.id.txBrSedista);
        tvDatum= findViewById(R.id.txDatum);
        tvVreme = findViewById(R.id.txVreme);
        tvImeKorisnika = findViewById(R.id.txIme);
        tvPrezimeKorisnika = findViewById(R.id.txPrezime);
        tvEmail = findViewById(R.id.txEmail);
        tvTelefon = findViewById(R.id.txTelefon);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent i = getIntent();

        voznja = (Voznja) i.getSerializableExtra("voznja");
        System.out.println("AAAAAAAAA Voznja:   "+voznja);

        tvStart.setText("Od: "+voznja.getStart());
        tvCilj.setText("Do: "+voznja.getCilj());
        tvAutomobil.setText("Automobil: "+voznja.getAutomobil());
        tevBrojSedista.setText("Broj sedišta: "+voznja.getBrojSedista());
        tvDatum.setText("Datum: "+voznja.getDatum());
        tvVreme.setText("Vreme: "+voznja.getVreme());
        tvImeKorisnika.setText(voznja.getK().getIme());
        tvPrezimeKorisnika.setText(voznja.getK().getPrezime());
        tvEmail.setText(voznja.getK().getEmail());
        tvTelefon.setText("0"+voznja.getK().getTelefon());

          btnRezervisi.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  System.out.println("AAAAAAAAAAAAA");
                  System.out.println("Voznja: "+voznja);
                  Korisnik korisnik = getKorisnik();
                  System.out.println("Korisnik: "+korisnik);

                 int rezervacija =  databaseHendler.addRezervacija(korisnik,voznja);
                  System.out.println("KKKOOORRIISNIk: "+ korisnik.getId()+korisnik);
                  System.out.println("RRRRRIDDDD: "+rezervacija);
                  System.out.println("RRREEZZEERVACIJE : "+databaseHendler.getVoznjaByKorisnik(korisnik));
                  List<Voznja> rezervacijas = databaseHendler.getVoznjaByKorisnik(korisnik);
                  System.out.println("VVVVVVVVVVVVVVV ::: "+rezervacijas);

                  Toast.makeText(getApplicationContext(),"Uspešno ste rezervisali svoju vožnju.",Toast.LENGTH_LONG).show();

                  Intent i = new Intent(getApplicationContext(),RezervisaneVoznje.class);
                  startActivity(i);

              }
          });

          btnSendEmial.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                sendEmail();
              }
          });


    }

    public Korisnik getKorisnik(){
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

        return k;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        double sirina = voznja.getGeografskaSirina();
        double duzina = voznja.getGeografskaDuzina();

        LatLng lokacija = new LatLng(sirina, duzina);

        map.getUiSettings().setZoomGesturesEnabled(true);

        map.addMarker(new MarkerOptions().position(lokacija).title(voznja.getStart()));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lokacija, 15f));

        map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                map.getUiSettings().setZoomGesturesEnabled(true);

                return false;
            }
        });
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        map.setMyLocationEnabled(true);


        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setCompassEnabled(true);

    }

   public void sendEmail(){
        String[] TO_MAILS = {voznja.getK().getEmail()};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
       intent.setData(Uri.parse("mailto:"));
       intent.putExtra(Intent.EXTRA_EMAIL, TO_MAILS);

       intent.putExtra(Intent.EXTRA_SUBJECT, "Upit za voznju od: "+voznja.getStart()+" do: "+voznja.getCilj());
       intent.putExtra(Intent.EXTRA_TEXT, "Vaš upit...");
       startActivity(Intent.createChooser(intent, "CS330-DZ08"));
   }

}