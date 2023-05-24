import java.util.HashMap;
import java.util.Map;


class Zlecenie {
    private static int liczbaZlecen = 0;
    private int numer;
    private Map<Integer, Praca> prace;
    private String brygadzista;
    private Map<String, String> dzialyPracownikow;

    public Zlecenie(String brygadzista) {
        this.numer = ++liczbaZlecen;
        this.prace = new HashMap<>();
        this.brygadzista = brygadzista;
        this.dzialyPracownikow = new HashMap<>();
    }

    public void dodajPrace(Praca praca) {
        prace.put(praca.getNumer(), praca);
    }

    public void dodajPracownikaDoDzialu(String dzial, String pracownik) {
        dzialyPracownikow.put(dzial, pracownik);
    }

    public boolean czyBralUdzialBrygadzista(String brygadzista) {
        return this.brygadzista.equals(brygadzista);
    }

    @Override
    public String toString() {
        return "Zlecenie numer: " + numer +
                "\nPrace: " + prace.keySet() +
                "\nBrygadzista: " + brygadzista +
                "\nDziały pracowników: " + dzialyPracownikow.keySet();
    }
}