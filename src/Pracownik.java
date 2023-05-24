import java.util.*;

class Pracownik implements Comparable<Pracownik> {
    private static List<Pracownik> pracownicy = new ArrayList<>();
    private String imie, nazwisko;
    private DzialPracownikow dzialPracownikow;
    private Date dataUrodzenia;

    public Pracownik(String imie, String nazwisko, Date dataUrodzenia, DzialPracownikow dzialPracownikow) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.dzialPracownikow = dzialPracownikow;
        pracownicy.add(this);
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dzialPracownikow=" + dzialPracownikow +
                ", dataUrodzenia=" + dataUrodzenia +
                '}';
    }

    public static List<Pracownik> getPracownicy() {
        return pracownicy;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public DzialPracownikow getDzial() {
        return dzialPracownikow;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getInicjal() {
        return (imie.charAt(0) + "" + nazwisko.charAt(0)).toUpperCase();
    }

    @Override
    public int compareTo(Pracownik p) {
        int compareTo = imie.compareTo(p.getImie());
        if (compareTo != 0) return compareTo;
        compareTo = nazwisko.compareTo(p.getNazwisko());
        if (compareTo != 0) return compareTo;
        return dataUrodzenia.compareTo(p.getDataUrodzenia());
    }
}
