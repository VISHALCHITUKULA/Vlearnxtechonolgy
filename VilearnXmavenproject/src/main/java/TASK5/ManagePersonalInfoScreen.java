package TASK5;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagePersonalInfoScreen extends JFrame {
    private JTextField userIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JButton updateButton;

    public ManagePersonalInfoScreen() {
        setTitle("Manage Personal Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        panel.add(new JLabel("User ID:"));
        userIdField = new JTextField();
        panel.add(userIdField);

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JTextField();
        panel.add(passwordField);

        panel.add(new JLabel("Address:"));
        addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        panel.add(phoneNumberField);

        updateButton = new JButton("Update Information");
        updateButton.addActionListener(e -> updatePersonalInfo());
        panel.add(updateButton);

        add(panel);
    }

    private void updatePersonalInfo() {
        int userId = Integer.parseInt(userIdField.getText());
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE users SET name = ?, email = ?, password = ?, address = ?, phone_number = ? WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.setString(4, address);
                stmt.setString(5, phoneNumber);
                stmt.setInt(6, userId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Information updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating information.");
        }
    }
}
