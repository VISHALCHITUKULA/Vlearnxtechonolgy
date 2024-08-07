package TASK6;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterPatientScreen extends JFrame {
    public RegisterPatientScreen() {
        setTitle("Register Patient");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(100, 20, 165, 25);
        panel.add(nameText);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(10, 50, 80, 25);
        panel.add(ageLabel);

        JTextField ageText = new JTextField(20);
        ageText.setBounds(100, 50, 165, 25);
        panel.add(ageText);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(10, 80, 80, 25);
        panel.add(genderLabel);

        JTextField genderText = new JTextField(20);
        genderText.setBounds(100, 80, 165, 25);
        panel.add(genderText);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(10, 110, 80, 25);
        panel.add(contactLabel);

        JTextField contactText = new JTextField(20);
        contactText.setBounds(100, 110, 165, 25);
        panel.add(contactText);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(10, 140, 80, 25);
        panel.add(addressLabel);

        JTextField addressText = new JTextField(20);
        addressText.setBounds(100, 140, 165, 25);
        panel.add(addressText);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10, 180, 150, 25);
        registerButton.addActionListener(e -> {
            String name = nameText.getText();
            int age = Integer.parseInt(ageText.getText());
            String gender = genderText.getText();
            String contact = contactText.getText();
            String address = addressText.getText();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO patients (name, age, gender, contact, address) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, name);
                statement.setInt(2, age);
                statement.setString(3, gender);
                statement.setString(4, contact);
                statement.setString(5, address);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Patient registered successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error registering patient!");
            }
        });
        panel.add(registerButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterPatientScreen screen = new RegisterPatientScreen();
            screen.setVisible(true);
        });
    }
}
