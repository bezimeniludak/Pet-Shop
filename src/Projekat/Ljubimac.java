package Projekat;

public class Ljubimac extends Proizvod implements OdazoviSe {

    String pol;
    String starost;

    public Ljubimac(String naziv, String pol, String starost, double cena) {
        super(naziv, cena);
        this.pol = pol;
        this.starost = starost;
    }

    @Override
    public void odazoviSe() {
        System.out.println("Ja sam zivotinja");
    }

}
