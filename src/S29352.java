import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class S29352 {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuView;
    private JMenuItem menuItemLogin;
    private JMenuItem menuItemLogout;
    private JButton buttonCreate;
    private JButton buttonEdit;
    private JButton buttonDelete;
    private JButton buttonDisplay;
    private DefaultListModel<String> listModel;
    private JList<String> listData;
    private JScrollPane scrollPane;
    private boolean loggedIn;
    private java.util.List<String> objects; // Lista obiektów

    public S29352() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Menadżer Obiektów");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        createMenuBar();
        createButtons();
        createList();

        frame.add(menuBar);
        frame.add(buttonCreate);
        frame.add(buttonEdit);
        frame.add(buttonDelete);
        frame.add(scrollPane);

        loggedIn = false;
        objects = new java.util.ArrayList<>(); // Inicjalizacja listy obiektów

        frame.setVisible(true);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        menuFile = new JMenu("Plik");
        menuItemLogin = new JMenuItem("Zaloguj");
        menuItemLogout = new JMenuItem("Wyloguj");

        menuItemLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        menuItemLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        menuFile.add(menuItemLogin);
        menuFile.add(menuItemLogout);
        menuBar.add(menuFile);
    }

    private void createButtons() {
        buttonCreate = new JButton("Utwórz");
        buttonEdit = new JButton("Edytuj");
        buttonDelete = new JButton("Usuń");

        buttonCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createObject();
            }
        });

        buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editObject();
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteObject();
            }
        });


        buttonCreate.setEnabled(false);
        buttonEdit.setEnabled(false);
        buttonDelete.setEnabled(false);
    }

    private void createList() {
        listModel = new DefaultListModel<>();
        listData = new JList<>(listModel);
        scrollPane = new JScrollPane(listData);
        scrollPane.setPreferredSize(new Dimension(400, 400));
    }

    private void login() {
        if (!loggedIn) {
            String username = JOptionPane.showInputDialog(frame, "Nazwa użytkownika:");
            String password = JOptionPane.showInputDialog(frame, "Hasło:");

            if (validateCredentials(username, password)) {
                loggedIn = true;
                menuItemLogin.setEnabled(false);
                menuItemLogout.setEnabled(true);
                updateUI();
                showMessage("Zalogowano jako: " + username);
            } else {
                showMessage("Błędne dane logowania.");
            }
        } else {
            showMessage("Jesteś już zalogowany.");
        }
    }

    private void logout() {
        if (loggedIn) {
            loggedIn = false;
            menuItemLogin.setEnabled(true);
            menuItemLogout.setEnabled(false);
            updateUI();
            showMessage("Wylogowano.");
        } else {
            showMessage("Nie jesteś zalogowany.");
        }
    }

    private boolean validateCredentials(String username, String password) {
        // Walidacja danych logowania
        // Zaimplementuj własną logikę walidacji
        return username.equals("admin") && password.equals("admin123");
    }

    private void createObject() {
        // Implementacja logiki tworzenia obiektu
        if (loggedIn) {
            String objectName = JOptionPane.showInputDialog(frame, "Podaj nazwę obiektu:");
            if (objectName != null && !objectName.isEmpty()) {
                objects.add(objectName); // Dodaj obiekt do listy
                listModel.addElement(objectName); // Dodaj obiekt do modelu listy
                showMessage("Utworzono obiekt: " + objectName);
            } else {
                showMessage("Nie podano nazwy obiektu.");
            }
        } else {
            showMessage("Musisz być zalogowany, aby utworzyć obiekt.");
        }
    }

    private void editObject() {
        // Implementacja logiki edycji obiektu
        if (loggedIn) {
            int selectedIndex = listData.getSelectedIndex();
            if (selectedIndex != -1) {
                String currentName = listData.getSelectedValue();
                String newName = JOptionPane.showInputDialog(frame, "Edytuj nazwę obiektu:", currentName);
                if (newName != null && !newName.isEmpty()) {
                    objects.set(selectedIndex, newName);
                    listModel.set(selectedIndex, newName); // Zaktualizuj nazwę obiektu w modelu listy
                    showMessage("Zaktualizowano obiekt: " + currentName + " -> " + newName);
                }
            } else {
                showMessage("Wybierz obiekt do edycji.");
            }
        } else {
            showMessage("Musisz być zalogowany, aby edytować obiekt.");
        }
    }

    private void deleteObject() {
        // Implementacja logiki usuwania obiektu
        if (loggedIn) {
            int selectedIndex = listData.getSelectedIndex();
            if (selectedIndex != -1) {
                String objectName = listData.getSelectedValue();
                int option = JOptionPane.showConfirmDialog(frame, "Czy na pewno usunąć obiekt: " + objectName + "?");
                if (option == JOptionPane.YES_OPTION) {
                    objects.remove(selectedIndex);
                    listModel.remove(selectedIndex); // Usuń obiekt z modelu listy
                    showMessage("Usunięto obiekt: " + objectName);
                }
            } else {
                showMessage("Wybierz obiekt do usunięcia.");
            }
        } else {
            showMessage("Musisz być zalogowany, aby usunąć obiekt.");
        }
    }

    private void displayObjects() {
        // Implementacja logiki wyświetlania obiektów
        if (loggedIn) {
            StringBuilder sb = new StringBuilder();
            for (String object : objects) {
                sb.append(object).append("\n");
            }
            showMessage("Obiekty:\n" + sb.toString());
        } else {
            showMessage("Musisz być zalogowany, aby wyświetlić obiekty.");
        }
    }

    private void updateUI() {
        buttonCreate.setEnabled(loggedIn);
        buttonEdit.setEnabled(loggedIn);
        buttonDelete.setEnabled(loggedIn);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new S29352();
            }
        });
    }
}
