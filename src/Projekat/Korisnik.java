package Projekat;

public class Korisnik {

    String ime;
    String prezime;
    boolean isAdmin;

    public Korisnik(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;

    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + ", Admin: " + isAdmin;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

}
