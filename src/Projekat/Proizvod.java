package Projekat;

public class Proizvod {

    String naziv;
    double cena;

    public Proizvod(String naziv, double cena) {
        this.naziv = naziv;
        this.cena = cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return naziv + ", cena: " + cena + " RSD";
    }

}
