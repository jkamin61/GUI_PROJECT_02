import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class S29352 {
    private JFrame frame;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JButton buttonCreate;
    private JButton buttonEdit;
    private JButton buttonDelete;
    private JLabel welcomeLabel;
    private DefaultListModel<String> listModelDepartment;
    private DefaultListModel<String> listModelEmployee;
    private DefaultListModel<String> listModelUser;
    private DefaultListModel<String> listModelSupervisor;
    private DefaultListModel<String> listModelBrigade;
    private DefaultListModel<String> listModelOrder;
    private DefaultListModel<String> listModelWork;
    private List<String> objects;
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
    private JButton buttonCompleteOrder;
    private JButton buttonEmployeeList;
    private boolean isBrygadzista;

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
        createLists();
        createAdditionalButtons();

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);

        loggedIn = false;
        isBrygadzista = false;

        objects = new ArrayList<>();
        frame.setVisible(true);

        buttonCompleteOrder.setEnabled(false);
        buttonCompleteOrder.setVisible(false);
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

        buttonDepartment.addActionListener(e -> showDepartmentList());
        buttonEmployee.addActionListener(e -> showEmployeeList());
        buttonUser.addActionListener(e -> showUserList());
        buttonSupervisor.addActionListener(e -> showSupervisorList());
        buttonBrigade.addActionListener(e -> showBrigadeList());
        buttonOrder.addActionListener(e -> showOrderList());
        buttonWork.addActionListener(e -> showWorkList());

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

    private void createLists() {
        listModelDepartment = new DefaultListModel<>();
        listModelEmployee = new DefaultListModel<>();
        listModelUser = new DefaultListModel<>();
        listModelSupervisor = new DefaultListModel<>();
        listModelBrigade = new DefaultListModel<>();
        listModelOrder = new DefaultListModel<>();
        listModelWork = new DefaultListModel<>();

        listData = new JList<>(listModelDepartment);
        listData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listData.addListSelectionListener(e -> updateButtonStates());

        scrollPane = new JScrollPane(listData);
    }

    private void createAdditionalButtons() {
        buttonCompleteOrder = new JButton("Zakończ zlecenie");
        buttonCompleteOrder.addActionListener(e -> completeOrder());
        buttonCompleteOrder.setEnabled(false);

        buttonEmployeeList = new JButton("Lista pracowników");
        buttonEmployeeList.addActionListener(e -> showEmployeeList());
        buttonEmployeeList.setEnabled(false);
        buttonEmployeeList.setVisible(false);

        JPanel leftPanel = (JPanel) topPanel.getComponent(0);
        leftPanel.add(buttonCompleteOrder);
        leftPanel.add(buttonEmployeeList);
    }

    private void completeOrder() {
        int selectedIndex = listData.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedOrder = listData.getSelectedValue();
            showMessage("Zlecenie zakończone: " + selectedOrder);
            listModelOrder.remove(selectedIndex);
            objects.remove(selectedIndex);
        }
    }

    private void updateButtonStates() {
        boolean listItemSelected = listData.getSelectedIndex() != -1;
        boolean isOrderView = listData.getModel() == listModelOrder;
        boolean isDepartmentView = listData.getModel() == listModelDepartment;

        buttonCreate.setEnabled(loggedIn);
        buttonEdit.setEnabled(loggedIn && listItemSelected);
        buttonDelete.setEnabled(loggedIn && listItemSelected);
        buttonCompleteOrder.setEnabled(loggedIn && listData.getModel() == listModelOrder && listItemSelected);
        buttonEmployeeList.setEnabled(loggedIn && listData.getModel() == listModelDepartment && listItemSelected);

        if (isOrderView) {
            buttonCompleteOrder.setEnabled(loggedIn && listItemSelected);
            buttonCompleteOrder.setVisible(true);
        } else {
            buttonCompleteOrder.setVisible(false);
        }
        if (isDepartmentView) {
            buttonEmployeeList.setEnabled(loggedIn && listItemSelected);
            buttonEmployeeList.setVisible(true);
        } else {
            buttonEmployeeList.setVisible(false);
        }
    }

    private void showLoginDialog() {
        String username = JOptionPane.showInputDialog(frame, "Nazwa użytkownika:");
        String password = JOptionPane.showInputDialog(frame, "Hasło:");

        if (validateCredentials(username, password)) {
            loggedIn = true;
            updateUI();
            showMessage("Zalogowano jako: " + username);
            welcomeLabel.setText("Witaj " + getInitials(username));
            if (isBrygadzista) {
                listModelDepartment.addElement("Dział Pracowników: Programiści, Sprzedaż");
                listModelEmployee.addElement("Pracownik: Jan Kowalski, Stanowisko: Programista Frontend, Dział: Programiści");
                listModelEmployee.addElement("Pracownik: Janusz Maj, Stanowisko: Programista Backend, Dział: Programiści");
                listModelEmployee.addElement("Pracownik: Konrad Mat, Stanowisko: Programista Fullstack, Dział: Programiści");
                listModelUser.addElement("Użytkownik: admin, Hasło: admin, Dział: Programiści");
                listModelSupervisor.addElement("Brygadzista: admin, Dział: Programiści");
                listModelOrder.addElement("Zlecenie numer: 1, Praca: Mobile-app, Brygadzista: admin, Działy pracowników: Programiści, Sprzedaż");
                if (listModelOrder != null) {
                    showMessage("Zaległe zlecenia: " + listModelOrder.size());
                    showOrderList();
                }
                objects.add("Zlecenie numer: 1");
            } else {
                listModelDepartment.addElement("Dział Pracowników: Sprzedaż");
                listModelEmployee.addElement("Pracownik: Jan Kowalski, Stanowisko: Sprzedawca, Dział: Sprzedaż");
                listModelUser.addElement("Użytkownik: user, Hasło: user, Dział: Sprzedaż");
                listModelSupervisor.addElement("Brygadzista: user, Dział: Sprzedaż");
                listModelOrder.addElement("Zlecenie numer: 1, Praca: Mobile-app, Brygadzista: user, Działy pracowników: Sprzedaż");
                objects.add("Zlecenie numer: 1");
            }
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

    private void clearLists() {
        listModelDepartment.clear();
        listModelEmployee.clear();
        listModelUser.clear();
        listModelSupervisor.clear();
        listModelBrigade.clear();
        listModelOrder.clear();
        listModelWork.clear();
    }

    private void logout() {
        loggedIn = false;
        isBrygadzista = false;
        updateUI();
        clearLists();
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

    private void showDepartmentList() {
        listData.setModel(listModelDepartment);
        updateButtonStates();
    }

    private void showEmployeeList() {
        listData.setModel(listModelEmployee);
        updateButtonStates();
    }

    private void showUserList() {
        listData.setModel(listModelUser);
        updateButtonStates();
    }

    private void showSupervisorList() {
        listData.setModel(listModelSupervisor);
        updateButtonStates();
    }

    private void showBrigadeList() {
        listData.setModel(listModelBrigade);
        updateButtonStates();
    }

    private void showOrderList() {
        listData.setModel(listModelOrder);
        updateButtonStates();
    }

    private void showWorkList() {
        listData.setModel(listModelWork);
        updateButtonStates();
    }

    private void createObject() {
        String input = JOptionPane.showInputDialog(frame, "Podaj nazwę nowego działu:", "Utwórz dział", JOptionPane.PLAIN_MESSAGE);
        if (input != null && !input.isEmpty()) {
            DefaultListModel<String> currentListModel = getCurrentListModel();
            if (currentListModel != null) {
                currentListModel.addElement(input);
                objects.add(input);
            }
        }
    }

    private DefaultListModel<String> getCurrentListModel() {
        if (listData.getModel() == listModelDepartment) {
            return listModelDepartment;
        } else if (listData.getModel() == listModelEmployee) {
            return listModelEmployee;
        } else if (listData.getModel() == listModelUser) {
            return listModelUser;
        } else if (listData.getModel() == listModelSupervisor) {
            return listModelSupervisor;
        } else if (listData.getModel() == listModelBrigade) {
            return listModelBrigade;
        } else if (listData.getModel() == listModelOrder) {
            return listModelOrder;
        } else if (listData.getModel() == listModelWork) {
            return listModelWork;
        }
        return null;
    }

    private void editObject() {
        int selectedIndex = listData.getSelectedIndex();
        if (selectedIndex != -1) {
            DefaultListModel<String> currentListModel = getCurrentListModel();
            if (currentListModel != null) {
                String currentValue = currentListModel.getElementAt(selectedIndex);
                String newValue = JOptionPane.showInputDialog(frame, "Edytuj nazwę działu:", currentValue);
                if (newValue != null && !newValue.isEmpty()) {
                    currentListModel.setElementAt(newValue, selectedIndex);
                    objects.set(selectedIndex, newValue);
                }
            }
        }
    }

    private void deleteObject() {
        int selectedIndex = listData.getSelectedIndex();
        if (selectedIndex != -1) {
            DefaultListModel<String> currentListModel = getCurrentListModel();
            if (currentListModel != null) {
                currentListModel.remove(selectedIndex);
                objects.remove(selectedIndex);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                S29352 window = new S29352();
                window.frame.setVisible(true);
                window.showLoginDialog();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
