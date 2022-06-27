package Projekat;

public class Admin extends Korisnik {

    public Admin(String ime, String prezime) {
        super(ime, prezime);
        this.isAdmin = true;
    }

    @Override
    public String getIme() {
        return ime;
    }

    @Override
    public String getPrezime() {
        return prezime;
    }

}
