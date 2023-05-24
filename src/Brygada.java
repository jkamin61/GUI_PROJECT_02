import java.util.ArrayList;
import java.util.List;

class Brygada {
    private String nazwa;
    private Brygadzista brygadzista;
    private List<Pracownik> pracownicy;

    public Brygada(String nazwa, Brygadzista brygadzista, List<Pracownik> pracownicy) {
        this.nazwa = nazwa;
        this.brygadzista = brygadzista;
        this.pracownicy = pracownicy;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Brygadzista getBrygadzista() {
        return brygadzista;
    }

    public List<Pracownik> getPracownicy() {
        return pracownicy;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setBrygadzista(Brygadzista brygadzista) {
        this.brygadzista = brygadzista;
    }

    public void setPracownicy(List<Pracownik> pracownicy) {
        this.pracownicy = pracownicy;
    }

    @Override
    public String toString() {
        return "Brygada{" +
                "nazwa='" + nazwa + '\'' +
                ", brygadzista=" + brygadzista +
                ", pracownicy=" + pracownicy +
                '}';
    }

    public void dodajPracownika(Pracownik pracownik) {
        pracownicy.add(pracownik);
    }

    public void usunPracownika(Pracownik pracownik) {
        pracownicy.remove(pracownik);
    }
}