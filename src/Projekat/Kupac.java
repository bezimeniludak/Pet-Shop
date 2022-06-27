package Projekat;

import java.util.ArrayList;

public class Kupac extends Korisnik {

    String tel;
    ArrayList<String> korpa;
    double stanje;

    public Kupac(String ime, String prezime, String tel, ArrayList<String> korpa, double stanje) {
        super(ime, prezime);
        this.tel = tel;
        this.korpa = korpa;
        this.stanje = stanje;
        this.isAdmin = false;

    }

    public Kupac(String ime, String prezime, String tel, double stanje) {
        super(ime, prezime);
        this.tel = tel;
        this.korpa = new ArrayList<>();
        this.korpa.add("prazno");
        this.stanje = stanje;
        this.isAdmin = false;

    }

    public ArrayList<String> getKorpa() {
        return korpa;
    }

    public String getKorpaString() {
        String string = "";
        for (String s : korpa) {
            string += s + ",";
        }
        return string;
    }

    @Override
    public String getIme() {
        return ime;
    }

    @Override
    public String getPrezime() {
        return prezime;
    }

    public String getTel() {
        return tel;
    }

    public double getStanje() {
        return stanje;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + ", Admin: " + isAdmin + ", tel: " + tel + ", korpa: " + korpa + ", stanje=" + stanje;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setStanje(double stanje) {
        this.stanje = stanje;
    }

}
