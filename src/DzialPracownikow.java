import java.util.List;
import java.util.ArrayList;

public class DzialPracownikow {
    private static List<DzialPracownikow> dzialy = new ArrayList<>();
    private String nazwa;
    private List<Pracownik> pracownicy = new ArrayList<>();
    public DzialPracownikow(String nazwa) {
        this.nazwa = nazwa;
    }
    public static DzialPracownikow createDzial(String nazwa) throws NotUniqueException {
        for (DzialPracownikow dzial : dzialy) {
            if (dzial.nazwa.equals(nazwa)) {
                throw new NotUniqueException("Dział o nazwie " + nazwa + " już istnieje");
            }
        }
        DzialPracownikow dzial = new DzialPracownikow(nazwa);
        dzialy.add(dzial);
        return dzial;
    }
    public String toString() {
        return "Dział: " + nazwa;
    }
}

class NotUniqueException extends Exception {
    public NotUniqueException(String message) {
        super(message);
    }
}
