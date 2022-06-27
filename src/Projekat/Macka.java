package Projekat;

public class Macka extends Ljubimac {

    public Macka(String naziv, String pol, String starost, double cena) {
        super(naziv, pol, starost, cena);
    }

    @Override
    public void odazoviSe() {
        System.out.println("Mjau mjau");
    }

    @Override
    public String toString() {
        return "Macka: " + naziv + ", pol: " + pol + ", starost: " + starost + ", cena: " + cena;
    }
}
