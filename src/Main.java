import java.util.*;

public class Main {
    public static void main(String[] args) {

        try {
            //Ustalamy działy i ich pracowników
            DzialPracownikow dzialProgramistow = DzialPracownikow.createDzial("IT");
            Pracownik programista1 = new Pracownik("Jan", "Kowalski", new Date(90, 5, 10), dzialProgramistow);
            Pracownik programista2 = new Pracownik("Anna", "Nowak", new Date(85, 8, 20), dzialProgramistow);


            DzialPracownikow dzialSprzedazy = DzialPracownikow.createDzial("SALES");
            Pracownik sprzedawca1 = new Pracownik("Marek", "Jankowski", new Date(88, 2, 15), dzialSprzedazy);
            Pracownik sprzedawca2 = new Pracownik("Katarzyna", "Nowicka", new Date(92, 11, 1), dzialSprzedazy);

            //Ustalamy specjalistów i ich specjalizacje
            Specjalista programista3 = new Specjalista("Adam", "Murek", new Date(), dzialProgramistow, "Java");
            Specjalista sprzedawca3 = new Specjalista("Tomasz", "Lewandowski", new Date(), dzialSprzedazy, "e-Commerce");
            //Zbiór wszystkich pracowników
            System.out.println("Pracownicy:");
            for (Pracownik pracownik : Pracownik.getPracownicy()) {
                System.out.println(pracownik);
            }
            //Wymiana specjalistów i ich specjalizacji
            System.out.println("Specjalizacją pracownika " + programista3.getSpecjalista() + " z " + programista3.getDzial() + " jest " + programista3.getSpecjalizacja());
            System.out.println("Specjalizacja pracownika " + sprzedawca3.getSpecjalista() + " z " + sprzedawca3.getDzial() + " jest " + sprzedawca3.getSpecjalizacja());
            //Ustalenie brygadzistów, brygad i ich pracowników
            Brygadzista brygadzista1 = new Brygadzista("Marcin", "Kowal", new Date(90, 5, 10), dzialProgramistow, "login123", "haslo123", "brygada1");
            Brygada brygada1 = new Brygada("brygada1", brygadzista1, new ArrayList<>());
            System.out.println("Brygada: " + brygada1.getNazwa() + " brygadzista: " + brygada1.getBrygadzista().getInicjal());

            brygada1.dodajPracownika(programista1);
            brygada1.dodajPracownika(programista2);
            System.out.println("Pracownicy brygady: " + brygada1.getPracownicy());
            //Ustalenie użytkowników i ich uprawnień
            Uzytkownik uzytkownik1 = new Uzytkownik("Jan", "Kowalski", new Date(90, 5, 10), dzialProgramistow, "login123", "haslo123");
            System.out.println("Uzytkownik: " + uzytkownik1.getInicjal());

            Praca praca1 = new Praca(RodzajPracy.Ogolna, 10, "Praca ogólna 1");
            Praca praca2 = new Praca(RodzajPracy.Montaz, 20, "Praca montażowa 1");
            Praca praca3 = new Praca(RodzajPracy.Demontaz, 15, "Praca demontażowa 1");

            Zlecenie zlecenie = new Zlecenie("Jan Kowalski");
            zlecenie.dodajPrace(praca1);
            zlecenie.dodajPrace(praca2);
            zlecenie.dodajPrace(praca3);
            zlecenie.dodajPracownikaDoDzialu("Dział A", "Mariusz Nowak");
            zlecenie.dodajPracownikaDoDzialu("Dział B", "Anna Wiśniewska");

            praca2.dodajCzekajacaPrace(praca1);
            praca3.dodajCzekajacaPrace(praca2);

            Thread watek1 = new Thread(praca1);
            Thread watek2 = new Thread(praca2);
            Thread watek3 = new Thread(praca3);

            watek1.start();
            watek2.start();
            watek3.start();

            // Wyświetlanie informacji o zleceniu
            System.out.println(zlecenie.toString());


        } catch (NotUniqueException e) {
            System.out.println(e.getMessage());
        }


    }
}