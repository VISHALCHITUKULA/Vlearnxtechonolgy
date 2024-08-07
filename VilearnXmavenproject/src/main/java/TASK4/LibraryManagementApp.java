package TASK4;

import javax.swing.*;
import java.awt.*;

public class LibraryManagementApp extends JFrame {
    public LibraryManagementApp() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton manageItemsButton = new JButton("Manage Items");
        manageItemsButton.setBounds(10, 20, 200, 25);
        manageItemsButton.addActionListener(e -> new ManageItemsScreen().setVisible(true));
        panel.add(manageItemsButton);

        JButton checkoutButton = new JButton("Checkout/Return");
        checkoutButton.setBounds(10, 50, 200, 25);
        checkoutButton.addActionListener(e -> new CheckoutReturnScreen().setVisible(true));
        panel.add(checkoutButton);

        JButton fineManagementButton = new JButton("Overdue Fine Management");
        fineManagementButton.setBounds(10, 80, 200, 25);
        fineManagementButton.addActionListener(e -> new FineManagementScreen().setVisible(true));
        panel.add(fineManagementButton);

        JButton searchButton = new JButton("Search Items");
        searchButton.setBounds(10, 110, 200, 25);
        searchButton.addActionListener(e -> new SearchScreen().setVisible(true));
        panel.add(searchButton);

        JButton userManagementButton = new JButton("User Management");
        userManagementButton.setBounds(10, 140, 200, 25);
        userManagementButton.addActionListener(e -> new UserManagementScreen().setVisible(true));
        panel.add(userManagementButton);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LibraryManagementApp frame = new LibraryManagementApp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
