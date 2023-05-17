package com.metropolitan.cs330_projekat.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.metropolitan.cs330_projekat.model.Korisnik;
import com.metropolitan.cs330_projekat.model.Rezervacija;
import com.metropolitan.cs330_projekat.model.Voznja;

import java.util.ArrayList;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class DatabaseHendler extends SQLiteOpenHelper {


    public static String DATABASE_NAME = "cs330pz";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_KORISNICI = "korisnici";
    private static final String TABLE_VOZNJE = "voznje";
    private static final String TABLE_KORISNICI_VOZNJA = "korisnici_voznja";
    private static final String KEY_ID = "id_korisnika";
    private static final String KEY_IME = "ime";
    private static final String KEY_PREZIME = "prezime";
    private static final String KEY_TELEFON = "telefon";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ID_VOZNJE = "id_voznje";
    private static final String KEY_START = "start";
    private static final String KEY_CILJ = "cilj";
    private static final String KEY_AUTOMOBIL = "automobil";
    private static final String KEY_BROJSEDISTA = "brojSedista";
    private static final String KEY_GEOGRAFSKASIRINA = "geografskaSirina";
    private static final String KEY_GEOGRAFSKADUZINA = "geografskaDuzina";
    private static final String KEY_DATUM = "datum";
    private static final String KEY_VREME = "vreme";
    private static final String KEY_ID_KORISNIK_VOZNJA = "id";


    private static final String CREATE_TABLE_KORINSIK = "CREATE TABLE "
            + TABLE_KORISNICI + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_IME + " TEXT," + KEY_PREZIME + " TEXT,"+ KEY_TELEFON + " TEXT,"+ KEY_EMAIL+ " TEXT);";
    private static final String CREATE_TABLE_VOZNJE = "CREATE TABLE "
            + TABLE_VOZNJE + "("
            + KEY_ID_VOZNJE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_START + " TEXT,"
            + KEY_CILJ + " TEXT,"
            + KEY_AUTOMOBIL + " TEXT,"
            + KEY_BROJSEDISTA + " INTEGER,"
            + KEY_GEOGRAFSKASIRINA + " REAL,"
            + KEY_GEOGRAFSKADUZINA + " REAL,"
            + KEY_DATUM + " TEXT,"
            + KEY_VREME + " TEXT,"
            + KEY_ID + " INTEGER,"
            + "FOREIGN KEY ("+KEY_ID+") REFERENCES "+TABLE_KORISNICI+"("+KEY_ID+")"+")";

    private static final String CREATE_TABLE_KORINSIK_VOZNJA = "CREATE TABLE "
            + TABLE_KORISNICI_VOZNJA + "(" + KEY_ID_KORISNIK_VOZNJA
            + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_ID + " INTEGER," +
            KEY_ID_VOZNJE + " INTEGER," +
            "FOREIGN KEY ("+KEY_ID+") REFERENCES "+TABLE_KORISNICI+"("+KEY_ID+") ,"+
            "FOREIGN KEY ("+KEY_ID_VOZNJE+") REFERENCES "+TABLE_VOZNJE+"("+KEY_ID_VOZNJE+")"+");";



    public DatabaseHendler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KORINSIK);
        db.execSQL(CREATE_TABLE_VOZNJE);
        db.execSQL(CREATE_TABLE_KORINSIK_VOZNJA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_KORISNICI);
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_VOZNJE);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_KORISNICI_VOZNJA);

        onCreate(db);

    }

    public int addRezervacija(Korisnik korisnik, Voznja voznja){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        System.out.println("IDDDD KOrinsika: "+korisnik.getId());
        System.out.println("IDDDD VOZNJE: "+voznja.getId());
        contentValues.put(KEY_ID,korisnik.getId());
        contentValues.put(KEY_ID_VOZNJE,voznja.getId());
        System.out.println("Proslo");

        int id_rezervacije = (int) db.insert(TABLE_KORISNICI_VOZNJA, null, contentValues);

        return id_rezervacije;
    }


    public List<Voznja>getVoznjaByKorisnik(Korisnik korisnik){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Voznja> voznje = new ArrayList<>();
        List<Rezervacija> korisnici_voznja = new ArrayList<>();


        Cursor c;
        c = db.rawQuery("SELECT * FROM korisnici_voznja",null);
//                +
//                " JOIN "+TABLE_KORISNICI_VOZNJA+
//                " ON "+TABLE_KORISNICI_VOZNJA+"."+KEY_ID_VOZNJE+
//                "="+TABLE_VOZNJE+"."+KEY_ID_VOZNJE+
//                " WHERE "+TABLE_KORISNICI_VOZNJA+"."+KEY_ID+" = "+korisnik.getId()+"";
//
//        Log.e("DatabaseHendler", selectQuery);
//
//        Cursor c = db.rawQuery(selectQuery, null);



        System.out.println("AAAAAAAAAAAAAAAAA UUUUSSSSSLLLLLOOOOO!!!");
        System.out.println(c);
        if (c.moveToFirst()) {
            do {

                Rezervacija r = new Rezervacija(c.getInt(c.getColumnIndex(KEY_ID_KORISNIK_VOZNJA)),
                        c.getInt(c.getColumnIndex(KEY_ID)),
                        c.getInt(c.getColumnIndex(KEY_ID_VOZNJE)));

                korisnici_voznja.add(r);

                Voznja voznja = new Voznja();

                if(r.getIdKorisnika()==korisnik.getId()){
                    voznje.add(getVoznjaById(r.getIdVoznje()));
                }

//                System.out.println("UUUUSSSSSLLLLLOOOOO IDDDD!!!"+c.getColumnIndex(KEY_ID_KORISNIK_VOZNJA));
//                System.out.println("UUUUSSSSSLLLLLOOOOO IDDD KORRR!!!"+c.getColumnIndex(KEY_ID));
//                Korisnik k = getKorisnikById(c.getInt(c.getColumnIndex(KEY_ID)));
//
//                Voznja v = new Voznja(c.getString(c.getColumnIndex(KEY_START)),
//                        c.getString(c.getColumnIndex(KEY_CILJ)),
//                        c.getString(c.getColumnIndex(KEY_AUTOMOBIL)),
//                        c.getInt(c.getColumnIndex(KEY_BROJSEDISTA)),
//                        c.getDouble(c.getColumnIndex(KEY_GEOGRAFSKASIRINA)),
//                        c.getDouble(c.getColumnIndex(KEY_GEOGRAFSKADUZINA)),
//                        c.getString(c.getColumnIndex(KEY_DATUM)),
//                        c.getString(c.getColumnIndex(KEY_VREME)),
//                        k);
//                voznje.add(v);
            } while (c.moveToNext());
        }

        return voznje;

    }

    public int addKorisnik(Korisnik korisnik){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_IME,korisnik.getIme());
        contentValues.put(KEY_PREZIME,korisnik.getPrezime());
        contentValues.put(KEY_EMAIL,korisnik.getEmail());
        contentValues.put(KEY_TELEFON,korisnik.getTelefon());
        System.out.println("Proslo");

        int korisnik_id = (int) db.insert(TABLE_KORISNICI, null, contentValues);

        return korisnik_id;

    }

    public int addVoznja(Voznja voznja){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_START,voznja.getStart());
        contentValues.put(KEY_CILJ,voznja.getCilj());
        contentValues.put(KEY_AUTOMOBIL,voznja.getAutomobil());
        contentValues.put(KEY_BROJSEDISTA,voznja.getBrojSedista());
        contentValues.put(KEY_GEOGRAFSKASIRINA,voznja.getGeografskaSirina());
        contentValues.put(KEY_GEOGRAFSKADUZINA,voznja.getGeografskaDuzina());
        contentValues.put(KEY_DATUM,voznja.getDatum());
        contentValues.put(KEY_VREME,voznja.getVreme());
        contentValues.put(KEY_ID,voznja.getK().getId());

        int voznja_id = (int) db.insert(TABLE_VOZNJE, null, contentValues);

        System.out.println("Proslo");
        return voznja_id;

    }



    public List<Korisnik> getAllKorisnici(){
        List<Korisnik> korisnici = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c;
        c = db.rawQuery("SELECT * FROM korisnici",null);
        if(c == null) return null;
        c.moveToFirst();

        do {
            Korisnik k = new Korisnik(c.getString(c.getColumnIndex(KEY_IME)),
                    c.getString(c.getColumnIndex(KEY_PREZIME)),
                    c.getString(c.getColumnIndex(KEY_EMAIL)),
                    c.getInt(c.getColumnIndex(KEY_TELEFON)));
            korisnici.add(k);
        }while (c.moveToNext());

        c.close();
        db.close();

        return korisnici;
    }

    public Korisnik getKorisnikById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_KORISNICI + " WHERE "
                + KEY_ID + " = " + id;
        Log.e("DatabaseHendler", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c!=null)
            c.moveToFirst();

        Korisnik k = new Korisnik();
        k.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        k.setIme(c.getString(c.getColumnIndex(KEY_IME)));
        k.setPrezime(c.getString(c.getColumnIndex(KEY_PREZIME)));
        k.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
        k.setTelefon(c.getInt(c.getColumnIndex(KEY_TELEFON)));

        return k;

    }

    public Voznja getVoznjaById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_VOZNJE + " WHERE "
                + KEY_ID_VOZNJE + " = " + id;
        Log.e("DatabaseHendler", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c!=null)
            c.moveToFirst();

        Korisnik k = getKorisnikById(c.getInt(c.getColumnIndex(KEY_ID)));

        Voznja v = new Voznja(c.getString(c.getColumnIndex(KEY_START)),
                c.getString(c.getColumnIndex(KEY_CILJ)),
                c.getString(c.getColumnIndex(KEY_AUTOMOBIL)),
                c.getInt(c.getColumnIndex(KEY_BROJSEDISTA)),
                c.getDouble(c.getColumnIndex(KEY_GEOGRAFSKASIRINA)),
                c.getDouble(c.getColumnIndex(KEY_GEOGRAFSKADUZINA)),
                c.getString(c.getColumnIndex(KEY_DATUM)),
                c.getString(c.getColumnIndex(KEY_VREME)),
                k);


        return v;

    }

    public List<Voznja> getAllVoznja(){
        List<Voznja> voznje = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c;
        c = db.rawQuery("SELECT * FROM voznje",null);
        if(c == null) return null;
        c.moveToFirst();

        do {

           Korisnik k = getKorisnikById(c.getInt(c.getColumnIndex(KEY_ID)));

            Voznja v = new Voznja(c.getString(c.getColumnIndex(KEY_START)),
                    c.getString(c.getColumnIndex(KEY_CILJ)),
                    c.getString(c.getColumnIndex(KEY_AUTOMOBIL)),
                    c.getInt(c.getColumnIndex(KEY_BROJSEDISTA)),
                    c.getDouble(c.getColumnIndex(KEY_GEOGRAFSKASIRINA)),
                    c.getDouble(c.getColumnIndex(KEY_GEOGRAFSKADUZINA)),
                    c.getString(c.getColumnIndex(KEY_DATUM)),
                    c.getString(c.getColumnIndex(KEY_VREME)),
                    k);
            voznje.add(v);
            v.setId(c.getInt(c.getColumnIndex(KEY_ID_VOZNJE)));
        }while (c.moveToNext());

        c.close();
        db.close();

        return voznje;
    }

}
