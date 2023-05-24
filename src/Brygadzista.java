import java.util.Date;

public class Brygadzista extends Uzytkownik{
    private String brygada;

    public Brygadzista(String imie, String nazwisko, Date dataUrodzenia, DzialPracownikow dzial, String login, String haslo, String brygada) {
        super(imie, nazwisko, dataUrodzenia, dzial, login, haslo);
        this.brygada = brygada;
    }

    public String getBrygada() {
        return brygada;
    }

    public void setBrygada(String brygada) {
        this.brygada = brygada;
    }
}
