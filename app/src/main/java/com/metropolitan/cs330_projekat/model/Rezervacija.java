package com.metropolitan.cs330_projekat.model;

import java.io.Serializable;

public class Rezervacija implements Serializable {

    private int id;
    private int idKorisnika;
    private int idVoznje;

    public Rezervacija(int id, int idKorisnika, int idVoznje) {
        this.id = id;
        this.idKorisnika = idKorisnika;
        this.idVoznje = idVoznje;
    }

    public Rezervacija() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public int getIdVoznje() {
        return idVoznje;
    }

    public void setIdVoznje(int idVoznje) {
        this.idVoznje = idVoznje;
    }

    @Override
    public String toString() {
        return "Rezervacija{" +
                "id=" + id +
                ", idKorisnika=" + idKorisnika +
                ", idVoznje=" + idVoznje +
                '}';
    }
}
