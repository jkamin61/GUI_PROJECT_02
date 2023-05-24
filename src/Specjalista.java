import java.util.Date;
class Specjalista extends Pracownik {
    private String specjalizacja;

    public Specjalista(String imie, String nazwisko, Date dataUrodzenia, DzialPracownikow dzial, String specjalizacja) {
        super(imie, nazwisko, dataUrodzenia, dzial);
        this.specjalizacja = specjalizacja;
    }

    public String getSpecjalista() {
        return getInicjal();
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }
}