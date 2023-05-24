import java.util.HashMap;
import java.util.Map;

enum RodzajPracy {
    Ogolna, Montaz, Demontaz, Wymiana
}

class Praca extends Thread {
    private static int liczbaPrac = 0;
    private int numer;
    private RodzajPracy rodzajPracy;
    private int czasPracy;
    private boolean czyZrealizowane;
    private String opis;
    private Map<Integer, Praca> czekajacePrace;

    public Praca(RodzajPracy rodzajPracy, int czasPracy, String opis) {
        this.numer = ++liczbaPrac;
        this.rodzajPracy = rodzajPracy;
        this.czasPracy = czasPracy;
        this.czyZrealizowane = false;
        this.opis = opis;
        this.czekajacePrace = new HashMap<>();
    }

    public static Praca pobierzPrace(int numer) {
        return null;
    }

    public void dodajCzekajacaPrace(Praca praca) {
        czekajacePrace.put(praca.getNumer(), praca);
    }

    public int getNumer() {
        return numer;
    }

    @Override
    public void run() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Praca numer: " + numer +
                "\nRodzaj pracy: " + rodzajPracy +
                "\nCzas pracy: " + czasPracy +
                "\nCzy zrealizowane: " + czyZrealizowane +
                "\nOpis: " + opis +
                "\nCzekające prace: " + czekajacePrace.keySet();
    }
}