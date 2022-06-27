package Projekat;

public class Pas extends Ljubimac implements OdazoviSe {

    public Pas(String naziv, String pol, String starost, double cena) {
        super(naziv, pol, starost, cena);
    }

    @Override
    public void odazoviSe() {
        System.out.println("Av Av");
    }

    @Override
    public String toString() {
        return "Pas: " + naziv + ", pol: " + pol + ", starost: " + starost + ", cena:" + cena;
    }

}
