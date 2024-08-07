package TASK6;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StaffManagementScreen extends JFrame {
    public StaffManagementScreen() {
        setTitle("Staff Management");
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

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(10, 50, 80, 25);
        panel.add(roleLabel);

        JTextField roleText = new JTextField(20);
        roleText.setBounds(100, 50, 165, 25);
        panel.add(roleText);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(10, 80, 80, 25);
        panel.add(contactLabel);

        JTextField contactText = new JTextField(20);
        contactText.setBounds(100, 80, 165, 25);
        panel.add(contactText);

        JLabel scheduleLabel = new JLabel("Schedule:");
        scheduleLabel.setBounds(10, 110, 80, 25);
        panel.add(scheduleLabel);

        JTextField scheduleText = new JTextField(20);
        scheduleText.setBounds(100, 110, 165, 25);
        panel.add(scheduleText);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(10, 140, 150, 25);
        saveButton.addActionListener(e -> {
            String name = nameText.getText();
            String role = roleText.getText();
            String contact = contactText.getText();
            String schedule = scheduleText.getText();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO staff (name, role, contact, schedule) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, name);
                statement.setString(2, role);
                statement.setString(3, contact);
                statement.setString(4, schedule);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Staff saved successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving staff!");
            }
        });
        panel.add(saveButton);
    }
}
