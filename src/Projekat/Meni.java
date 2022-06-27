package Projekat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Meni {

    static File f = new File("Osobe.txt");
    static PrintWriter pw;
    static Scanner sc;
    static Admin a;
    static Kupac k;
    static ArrayList<Korisnik> korisnici = new ArrayList<>();
    static ArrayList<Proizvod> proizvodi = new ArrayList<>();
    static ArrayList<Proizvod> korpa = new ArrayList<>();
    static ArrayList<Ljubimac> ljubimci = new ArrayList<>();

    public static void ucitajKorisnike() throws FileNotFoundException, IOException {
        sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String osoba = sc.nextLine();
            if (osoba.split(" ").length > 2) {
                ArrayList<String> korpa = new ArrayList<>();
                for (int i = 0; i < osoba.split(" ")[3].split(",").length; i++) {
                    korpa.add(osoba.split(" ")[3].split(",")[i]);
                }
                korisnici.add(new Kupac(osoba.split(" ")[0], osoba.split(" ")[1], osoba.split(" ")[2], korpa, Double.parseDouble(osoba.split(" ")[4])));
            } else {
                korisnici.add(new Admin(osoba.split(" ")[0], osoba.split(" ")[1]));
            }
        }
    }

    public static void ucitajLjubimce() {
        ljubimci.add(new Macka("Tosa", "muski", "5 godina", 5000));
        ljubimci.add(new Macka("Cica", "zenski", "2 godine", 7000));
        ljubimci.add(new Pas("Bleki", "muski", "8 meseci", 15000));
        ljubimci.add(new Pas("Zuca", "muski", "3 godine", 10000));
    }

    public static void ucitajProizvode() {
        proizvodi.add(new Proizvod("hrana(macke)0.4kg", 420));
        proizvodi.add(new Proizvod("hrana(macke)2kg", 1820));
        proizvodi.add(new Proizvod("hrana(macke)10kg", 8690));
        proizvodi.add(new Proizvod("hrana(psi)2.5kg", 1380));
        proizvodi.add(new Proizvod("hrana(psi)12kg", 4250));
        proizvodi.add(new Proizvod("Loptica", 300));
    }

    public static void login() throws IOException {
        sc = new Scanner(System.in);
        int login = 0;
        System.out.println("----- Dobro dosli u VISER Pet Shop -----\n"
                + "========================================\n"
                + "Prijavite se:\n");
        while (login == 0) {
            String ime = "";
            String prezime = "";
            System.out.println("Ime:");
            if (sc.hasNextLine()) {
                ime += sc.nextLine().trim();
            }
            System.out.println("Prezime: ");
            if (sc.hasNextLine()) {
                prezime += sc.nextLine().trim();
            }
            for (int i = 0; i < korisnici.size(); i++) {
                if (ime.equalsIgnoreCase(korisnici.get(i).ime) && prezime.equalsIgnoreCase(korisnici.get(i).prezime)) {
                    System.out.println("Uspesno ste se se ulogovali");
                    login = 1;

                    if (korisnici.get(i).isAdmin == true) {
                        a = (Admin) korisnici.get(i);
                        adminMeni();
                    } else {
                        k = (Kupac) korisnici.get(i);
                        ucitajKorpu();
                        kupacMeni();
                    }
                } else if (i + 1 == korisnici.size()) {
                    System.out.println("========================================"
                            + "\nNeuspesna prijava. Pokusajte ponovo");
                }
            }
        }
    }

    public static void adminMeni() throws IOException {
        System.out.println("Dobro dosli, " + a.ime);
        int broj;
        sc = new Scanner(System.in);

        System.out.println("========================================\n"
                + "Izaberite opciju:\n"
                + "1. Izlistaj korisnike\n"
                + "2. Izmeni podatke o korisniku\n"
                + "3. Dodaj korisnika\n"
                + "4. Izbrisi korisnika\n"
                + "5. Odjava\n"
                + "6. Izlaz");
        broj = 0;
        do {
            if (sc.hasNextInt()) {
                broj = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("========================================"
                        + "\nMorate uneti ceo broj");
            }
            if (broj < 1 || broj > 6) {
                System.out.println("========================================"
                        + "\nUneli ste broj izvan opsega");
            }
        } while (broj < 1 || broj > 6);
        switch (broj) {
            case 1:
                for (Korisnik korisnik : korisnici) {
                    System.out.println(korisnik.toString() + "\n");
                }
                adminMeni();
                break;
            case 2:
                System.out.println("========================================"
                        + "\nIzaberite korisnika: ");
                for (int i = 0; i < korisnici.size(); i++) {
                    System.out.println(i + 1 + ". " + korisnici.get(i).toString());
                }
                int broj2;
                broj2 = 0;
                do {
                    if (sc.hasNextInt()) {
                        broj2 = sc.nextInt();
                        sc.nextLine();
                    } else {
                        System.out.println("========================================"
                                + "\nMorate uneti ceo broj");
                    }
                    if (broj2 < 1 || broj2 - 1 > korisnici.size()) {
                        System.out.println("========================================"
                                + "\nUneli ste broj izvan opsega");
                    }
                } while (broj2 < 1 || broj2 - 1 > korisnici.size());
                System.out.println(korisnici.get(broj2 - 1));
                System.out.println("========================================"
                        + "\nUnesite novo ime:");
                String ime;
                do {
                    ime = "";
                    if (sc.hasNextLine()) {
                        ime += sc.nextLine().trim();
                    }
                } while (ime.isEmpty());
                korisnici.get(broj2 - 1).setIme(ime);
                System.out.println("========================================"
                        + "\nUnesite novo prezime:");
                String prezime;
                do {
                    prezime = "";
                    if (sc.hasNextLine()) {
                        prezime += sc.nextLine().trim();
                    }
                } while (prezime.isEmpty());
                korisnici.get(broj2 - 1).setPrezime(prezime);
                adminMeni();
                break;
            case 3:
                System.out.println("========================================"
                        + "\nDa li je novi korisnik admin?");
                String unos;
                do {
                    unos = "";
                    if (sc.hasNextLine()) {
                        unos += sc.nextLine().trim();
                    }
                    if (!unos.equalsIgnoreCase("da") && !unos.equalsIgnoreCase("ne")) {
                        System.out.println("========================================"
                                + "\nMorate uneti da ili ne");
                    }
                } while (!unos.equalsIgnoreCase("da") && !unos.equalsIgnoreCase("ne"));
                if (unos.equalsIgnoreCase("da")) {
                    System.out.println("========================================"
                            + "\nUnesite ime novog admina:");
                    do {
                        ime = "";
                        if (sc.hasNextLine()) {
                            ime += sc.nextLine().trim();
                        }
                    } while (ime.isEmpty());
                    System.out.println("========================================"
                            + "\nUnesite prezime novog admina:");
                    do {
                        prezime = "";
                        if (sc.hasNextLine()) {
                            prezime += sc.nextLine().trim();
                        }
                    } while (prezime.isEmpty());
                    Admin noviadmin = new Admin(ime, prezime);
                    korisnici.add(noviadmin);
                    pw = new PrintWriter(f);
                    pw.append(noviadmin.getIme() + " " + noviadmin.getPrezime());
                    pw.close();
                    System.out.println("========================================"
                            + "\nAdmin uspesno dodat.");
                    adminMeni();
                } else {
                    System.out.println("========================================"
                            + "\nUnesite ime novog kupca:");
                    do {
                        ime = "";
                        if (sc.hasNextLine()) {
                            ime += sc.nextLine().trim();
                        }
                    } while (ime.isEmpty());
                    System.out.println("========================================"
                            + "\nUnesite prezime novog kupca:");
                    do {
                        prezime = "";
                        if (sc.hasNextLine()) {
                            prezime += sc.nextLine().trim();
                        }
                    } while (prezime.isEmpty());
                    System.out.println("========================================"
                            + "\nUnesite telefon novog kupca:");
                    String tel;
                    do {
                        tel = "";
                        if (sc.hasNextLine()) {
                            tel += sc.nextLine().trim();
                        }
                    } while (tel.isEmpty());
                    System.out.println("========================================"
                            + "\nUnesite stanje novog kupca:");
                    double stanje;
                    stanje = 0;
                    do {
                        if (sc.hasNextDouble()) {
                            stanje = sc.nextDouble();
                        }
                    } while (stanje < 1);
                    Kupac novikupac = new Kupac(ime, prezime, tel, stanje);
                    korisnici.add(novikupac);
                    pw = new PrintWriter(f);
                    pw.append(novikupac.getIme() + " " + novikupac.getPrezime() + " " + novikupac.getTel() + "  " + novikupac.getStanje());
                    pw.close();
                    System.out.println("========================================"
                            + "\nKorisnik uspesno dodat.");
                    adminMeni();
                }
                break;
            case 4:
                System.out.println("========================================"
                        + "\nIzaberite korisnika: ");
                for (int i = 0; i < korisnici.size(); i++) {
                    System.out.println(i + 1 + ". " + korisnici.get(i).toString());
                }
                int broj3;
                broj3 = 0;
                do {
                    if (sc.hasNextInt()) {
                        broj3 = sc.nextInt();
                        sc.nextLine();
                    } else {
                        System.out.println("========================================"
                                + "\nMorate uneti ceo broj");
                    }
                    if (broj3 < 1 && broj3 - 1 > korisnici.size()) {
                        System.out.println("========================================"
                                + "\nUneti broj je izvan opsega");
                    }
                } while (broj3 < 1 && broj3 - 1 > korisnici.size());
                korisnici.remove(broj3 - 1);
                System.out.println("========================================"
                        + "\nKorisnik uspesno izbrisan");
                upisiKorisnike();
                adminMeni();
                break;
            case 5:
                login();
                break;
            case 6:
                sc.close();
                System.exit(0);
        }
    }

    public static void kupacMeni() throws IOException {
        System.out.println("========================================\n"
                + "Dobro dosli, \n" + k.toString());
        sc = new Scanner(System.in);
        double suma = 0;
        for (Proizvod p : korpa) {
            suma += p.getCena();
        }
        System.out.println("Vase trenutno zaduzenje: " + suma);
        System.out.println("========================================\n"
                + "Izaberite opciju:\n"
                + "1. Kupi ljubimca\n"
                + "2. Kupi proizvod\n"
                + "3. Isprazni korpu\n"
                + "4. Plati\n"
                + "5. Odjava\n"
                + "6. Izlaz");
        int broj = 0;
        do {
            if (sc.hasNextInt()) {
                broj = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("========================================\n"
                        + "Morate uneti ceo broj");
            }
            if (broj < 1 || broj > 6) {
                System.out.println("========================================\n"
                        + "Uneli ste broj izvan opsega");
            }
        } while (broj < 1 || broj > 6);
        switch (broj) {
            case 1:
                System.out.println("========================================\n"
                        + "Izaberite ljubimca: ");
                for (int i = 0; i < ljubimci.size(); i++) {
                    System.out.println(i + 1 + ". " + ljubimci.get(i).toString());
                }
                int broj2 = 0;
                do {
                    if (sc.hasNextInt()) {
                        broj2 = sc.nextInt();
                        sc.nextLine();
                    } else {
                        System.out.println("========================================\n"
                                + "Morate uneti ceo broj");
                    }
                    if (broj2 < 1 || broj2 - 1 > ljubimci.size()) {
                        System.out.println("========================================\n"
                                + "Uneli ste broj izvan opsega");
                    }
                } while (broj2 < 1 || broj2 - 1 > ljubimci.size());
                korpa.add(ljubimci.get(broj2 - 1));
                k.korpa.add(ljubimci.get(broj2 - 1).getNaziv());
                upisiKorisnike();
                System.out.println("Ljubimac uspesno dadat u korpu.");
                ljubimci.get(broj2 - 1).odazoviSe();
                kupacMeni();
                break;
            case 2:
                System.out.println("========================================\n"
                        + "Izaberite proizvod: ");
                for (int i = 0; i < proizvodi.size(); i++) {
                    System.out.println(i + 1 + ". " + proizvodi.get(i).toString());
                }
                int broj3 = 0;
                do {
                    if (sc.hasNextInt()) {
                        broj3 = sc.nextInt();
                        sc.nextLine();
                    } else {
                        System.out.println("========================================\n"
                                + "Morate uneti ceo broj");
                    }
                    if (broj3 < 1 || broj3 - 1 > proizvodi.size()) {
                        System.out.println("========================================\n"
                                + "Uneli ste broj izvan opsega");
                    }
                } while (broj3 < 1 || broj3 - 1 > proizvodi.size());
                korpa.add(proizvodi.get(broj3 - 1));
                k.korpa.add(proizvodi.get(broj3 - 1).getNaziv());
                upisiKorisnike();
                System.out.println("Proizvod uspesno dodat u korpu.");
                kupacMeni();
                break;
            case 3:
                k.korpa.clear();
                korpa.clear();
                System.out.println("========================================\n"
                        + "Korpa je ispraznjena");
                kupacMeni();
                break;
            case 4:
                System.out.println("========================================\n"
                        + "Vas racun: " + suma);
                System.out.println("Vase trenutno stanje: " + k.getStanje());
                if (suma > k.getStanje()) {
                    System.out.println("Nemate dovoljno novca.\n"
                            + "========================================\n"
                            + "!!!Dobili ste poklon vaucer u vrednosti 5000.00RSD!!!\n"
                            + "========================================\n"
                            + "Pokusajte ponovo\n");
                    k.setStanje(k.getStanje() + 5000);
                    kupacMeni();
                } else {
                    System.out.println("Hvala na kupovini.\n========================================\n");
                    k.korpa.clear();
                    korpa.clear();
                    k.setStanje(k.getStanje() - suma);
                    upisiKorisnike();
                    kupacMeni();
                }
                break;
            case 5:
                login();
                break;
            case 6:
                upisiKorisnike();
                sc.close();
                System.exit(0);
                break;
        }
    }

    public static void ucitajKorpu() {//string u arraylist<Proizvod>
        for (String s : k.getKorpa()) {
            for (Proizvod p : proizvodi) {
                if (s.equals(p.naziv)) {
                    korpa.add(p);
                }
            }
            for (Ljubimac l : ljubimci) {
                if (s.equals(l.naziv)) {
                    korpa.add(l);
                }
            }
        }
    }

    public static void upisiKorisnike() throws IOException {
        pw = new PrintWriter(f);
        for (Korisnik k : korisnici) {
            if (k.isAdmin == true) {
                pw.append(k.getIme() + " " + k.getPrezime() + "\n");
            } else {
                Kupac kupac = (Kupac) k;
                pw.append(kupac.getIme() + " " + kupac.getPrezime() + " " + kupac.getTel() + " " + kupac.getKorpaString() + " " + kupac.getStanje() + "\n");
            }
        }
        pw.close();
    }
}
