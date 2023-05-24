import java.util.Date;

public class Uzytkownik extends Pracownik {
    private String login;
    private String haslo;
    private String inicjal;

    public Uzytkownik(String imie, String nazwisko, Date dataUrodzenia, DzialPracownikow dzial, String login, String haslo) {
        super(imie, nazwisko, dataUrodzenia, dzial);
        this.login = login;
        this.haslo = haslo;
        this.inicjal = (imie.charAt(0) + "" + nazwisko.charAt(0)).toUpperCase();
    }

    public void zmienImie(String noweImie) {
        setImie(noweImie);
        this.inicjal = (noweImie.charAt(0) + "" + this.getNazwisko().charAt(0)).toUpperCase();
    }

    public void zmienNazwisko(String noweNazwisko) {
        setNazwisko(noweNazwisko);
        this.inicjal = (this.getImie().charAt(0) + "" + noweNazwisko.charAt(0)).toUpperCase();
    }
    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getInicjal() {
        return inicjal;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

}
