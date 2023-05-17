package com.metropolitan.cs330_projekat.model;

import java.io.Serializable;

public class Korisnik implements Serializable {

    private int id;
    private String ime ;
    private String prezime ;
    private String email;
    private int telefon;

    public Korisnik(String ime, String prezime, String email, int telefon) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
    }

    public Korisnik() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", email='" + email + '\'' +
                ", telefon=" + telefon +
                '}';
    }
}
