package com.metropolitan.cs330_projekat.model;

import java.io.Serializable;

public class Voznja implements Serializable {

    private int id;
    private String start;
    private String cilj;
    private String automobil;
    private int brojSedista;
    private double geografskaSirina;
    private double geografskaDuzina;
    private String datum;
    private String vreme;
    private Korisnik k;

    public Voznja() {
    }

    public Voznja(String start,
                  String cilj,
                  String automobil,
                  int brojSedista,
                  double geografskaSirina,
                  double geografskaDuzina,
                  String datum,
                  String vreme,
                  Korisnik k) {

        this.start = start;
        this.cilj = cilj;
        this.automobil = automobil;
        this.brojSedista = brojSedista;
        this.geografskaSirina = geografskaSirina;
        this.geografskaDuzina = geografskaDuzina;
        this.datum = datum;
        this.vreme = vreme;
        this.k = k;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getCilj() {
        return cilj;
    }

    public void setCilj(String cilj) {
        this.cilj = cilj;
    }

    public String getAutomobil() {
        return automobil;
    }

    public void setAutomobil(String automobil) {
        this.automobil = automobil;
    }

    public int getBrojSedista() {
        return brojSedista;
    }

    public void setBrojSedista(int brojSedista) {
        this.brojSedista = brojSedista;
    }

    public double getGeografskaSirina() {
        return geografskaSirina;
    }

    public void setGeografskaSirina(double geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public double getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(double geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public Korisnik getK() {
        return k;
    }

    public void setK(Korisnik k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return "Voznja{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", cilj='" + cilj + '\'' +
                ", automobil='" + automobil + '\'' +
                ", brojSedista=" + brojSedista +
                ", geografskaSirina=" + geografskaSirina +
                ", geografskaDuzina=" + geografskaDuzina +
                ", datum='" + datum + '\'' +
                ", vreme='" + vreme + '\'' +
                ", k=" + k +
                '}';
    }
}
