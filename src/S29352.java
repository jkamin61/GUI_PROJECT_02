import java.awt.*;
import javax.swing.*;

public class S29352 {
    private JFrame frame;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JButton buttonCreate;
    private JButton buttonEdit;
    private JButton buttonDelete;
    private JLabel welcomeLabel;
    private DefaultListModel<String> listModel;
    private JList<String> listData;
    private JScrollPane scrollPane;
    private boolean loggedIn;
    private JButton buttonDepartment;
    private JButton buttonEmployee;
    private JButton buttonUser;
    private JButton buttonSupervisor;
    private JButton buttonBrigade;
    private JButton buttonOrder;
    private JButton buttonWork;
    private boolean isBrygadzista;
    private java.util.List<String> objects;

    public S29352() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("GUI_Project_02");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        createTopPanel();
        createSidePanel();
        createList();

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);

        loggedIn = false;
        isBrygadzista = false;

        objects = new java.util.ArrayList<>();
        frame.setVisible(true);
    }

    private void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        buttonCreate = new JButton("Utwórz");
        buttonEdit = new JButton("Edytuj");
        buttonDelete = new JButton("Usuń");

        buttonCreate.addActionListener(e -> createObject());
        buttonEdit.addActionListener(e -> editObject());
        buttonDelete.addActionListener(e -> deleteObject());

        buttonCreate.setEnabled(false);
        buttonEdit.setEnabled(false);
        buttonDelete.setEnabled(false);

        leftPanel.add(buttonCreate);
        leftPanel.add(buttonEdit);
        leftPanel.add(buttonDelete);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        welcomeLabel = new JLabel();
        JButton buttonChangePassword = new JButton("Zmień hasło");
        JButton buttonLogout = new JButton("Wyloguj");
        buttonLogout.addActionListener(e -> logout());
        buttonChangePassword.addActionListener(e -> changePassword());

        rightPanel.add(welcomeLabel);
        rightPanel.add(buttonChangePassword);
        rightPanel.add(Box.createHorizontalStrut(20));
        rightPanel.add(buttonLogout);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);
    }

    private void createSidePanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(8, 1));

        buttonDepartment = new JButton("Dział Pracowników");
        buttonEmployee = new JButton("Pracownik");
        buttonUser = new JButton("Użytkownik");
        buttonSupervisor = new JButton("Brygadzista");
        buttonBrigade = new JButton("Brygada");
        buttonOrder = new JButton("Zlecenie");
        buttonWork = new JButton("Praca");

        buttonEmployee.addActionListener(e -> showEmployeeDetails());
        buttonUser.addActionListener(e -> showUserDetails());
        buttonSupervisor.addActionListener(e -> showSupervisorDetails());
        buttonBrigade.addActionListener(e -> showBrigadeDetails());
        buttonOrder.addActionListener(e -> showOrderDetails());
        buttonWork.addActionListener(e -> showWorkDetails());
        buttonDepartment.addActionListener(e -> showDepartmentDetails());

        leftPanel.add(buttonDepartment);
        leftPanel.add(buttonEmployee);
        leftPanel.add(buttonUser);
        leftPanel.add(buttonSupervisor);
        leftPanel.add(buttonBrigade);
        leftPanel.add(buttonOrder);
        leftPanel.add(buttonWork);

        buttonDepartment.setEnabled(false);
        buttonEmployee.setEnabled(false);
        buttonUser.setEnabled(false);
        buttonSupervisor.setEnabled(false);
        buttonBrigade.setEnabled(false);
        buttonOrder.setEnabled(false);
        buttonWork.setEnabled(false);

        if (isBrygadzista) {
            buttonDepartment.setEnabled(true);
            buttonEmployee.setEnabled(true);
            buttonUser.setEnabled(true);
            buttonSupervisor.setEnabled(true);
            buttonBrigade.setEnabled(true);
            buttonOrder.setEnabled(true);
            buttonWork.setEnabled(true);
        }
    }

    private void createList() {
        listModel = new DefaultListModel<>();
        listData = new JList<>(listModel);
        scrollPane = new JScrollPane(listData);
        scrollPane.setPreferredSize(new Dimension(400, 400));
    }

    private void showLoginDialog() {
        String username = JOptionPane.showInputDialog(frame, "Nazwa użytkownika:");
        String password = JOptionPane.showInputDialog(frame, "Hasło:");

        if (validateCredentials(username, password)) {
            loggedIn = true;
            updateUI();
            showMessage("Zalogowano jako: " + username);
            welcomeLabel.setText("Witaj " + getInitials(username));
        } else {
            showMessage("Błędne dane logowania.");
            System.exit(0);
        }
    }

    private boolean validateCredentials(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            isBrygadzista = true;
            return true;
        } else if (username.equals("user") && password.equals("user")) {
            isBrygadzista = false;
            return true;
        } else {
            return false;
        }
    }

    private void logout() {
        loggedIn = false;
        updateUI();
        showMessage("Wylogowano.");
        welcomeLabel.setText("");
        showLoginDialog();
    }

    private void changePassword() {
        if (loggedIn) {
            String newPassword = JOptionPane.showInputDialog(frame, "Podaj nowe hasło:");
            if (newPassword != null && !newPassword.isEmpty()) {
                showMessage("Zmieniono hasło.");
            } else {
                showMessage("Nie podano nowego hasła.");
            }
        } else {
            showMessage("Musisz być zalogowany, aby zmienić hasło.");
        }
    }

    private void updateUI() {
        buttonCreate.setEnabled(loggedIn);
        buttonEdit.setEnabled(loggedIn);
        buttonDelete.setEnabled(loggedIn);
        buttonDepartment.setEnabled(loggedIn);
        buttonEmployee.setEnabled(loggedIn);
        buttonUser.setEnabled(loggedIn);
        buttonSupervisor.setEnabled(isBrygadzista);
        buttonBrigade.setEnabled(loggedIn);
        buttonOrder.setEnabled(loggedIn);
        buttonWork.setEnabled(loggedIn);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private String getInitials(String username) {
        if (username == null || username.isEmpty()) {
            return "";
        }
        String[] nameParts = username.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String part : nameParts) {
            if (!part.isEmpty()) {
                initials.append(part.charAt(0));
            }
        }
        return initials.toString();
    }

    private void createObject() {
        if (loggedIn) {
            String objectName = JOptionPane.showInputDialog(frame, "Podaj nazwę obiektu:");
            if (objectName != null && !objectName.isEmpty()) {
                objects.add(objectName);
                listModel.addElement(objectName);
                showMessage("Utworzono obiekt: " + objectName);
            } else {
                showMessage("Nie podano nazwy obiektu.");
            }
        } else {
            showMessage("Musisz być zalogowany, aby utworzyć obiekt.");
        }
    }

    private void editObject() {
        if (loggedIn) {
            int selectedIndex = listData.getSelectedIndex();
            if (selectedIndex != -1) {
                String currentName = listData.getSelectedValue();
                String newName = JOptionPane.showInputDialog(frame, "Edytuj nazwę obiektu:", currentName);
                if (newName != null && !newName.isEmpty()) {
                    objects.set(selectedIndex, newName);
                    listModel.set(selectedIndex, newName);
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
        if (loggedIn) {
            int selectedIndex = listData.getSelectedIndex();
            if (selectedIndex != -1) {
                String objectName = listData.getSelectedValue();
                int option = JOptionPane.showConfirmDialog(frame, "Czy na pewno usunąć obiekt: " + objectName + "?");
                if (option == JOptionPane.YES_OPTION) {
                    objects.remove(selectedIndex);
                    listModel.remove(selectedIndex);
                    showMessage("Usunięto obiekt: " + objectName);
                }
            } else {
                showMessage("Wybierz obiekt do usunięcia.");
            }
        } else {
            showMessage("Musisz być zalogowany, aby usunąć obiekt.");
        }
    }

    private void showDepartmentDetails() {
//        JPanel departmentPanel = new JPanel();
//        JTextField departmentNameField = new JTextField(20);
//        JButton createButton = new JButton("Utwórz");
//
//        departmentPanel.add(new JLabel("Nazwa działu:"));
//        departmentPanel.add(departmentNameField);
//        departmentPanel.add(createButton);
//
//        createButton.addActionListener(e -> {
//            String departmentName = departmentNameField.getText();
//            if (!departmentName.isEmpty()) {
//                listModel.addElement(departmentName);
//                departmentNameField.setText("");
//            } else {
//                showMessage("Podaj nazwę działu.");
//            }
//        });
//
//        JOptionPane.showMessageDialog(frame, departmentPanel, "Dział Pracowników", JOptionPane.PLAIN_MESSAGE);
        if (loggedIn) {
            listModel.addElement("Przykładowy dział programistów");
        } else {
            showMessage("Musisz być zalogowany, aby zobaczyć działy.");
        }
    }

    private void showEmployeeDetails() {
        if (loggedIn) {
            listModel.addElement("Przykładowe informacje pracownika");
        } else {
            showMessage("Musisz być zalogowany, aby zobaczyć działy.");
        }
    }

    private void showUserDetails() {
        showMessage("Użytkownik");
    }

    private void showSupervisorDetails() {
        showMessage("Brygadzista");
    }

    private void showBrigadeDetails() {
        showMessage("Brygada");
    }

    private void showOrderDetails() {
        showMessage("Zlecenie");
    }

    private void showWorkDetails() {
        showMessage("Praca");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            S29352 app = new S29352();
            app.showLoginDialog();
        });
    }
}
