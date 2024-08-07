package TASK4;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FineManagementScreen extends JFrame {
    public FineManagementScreen() {
        setTitle("Overdue Fine Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(10, 20, 80, 25);
        panel.add(userIdLabel);

        JTextField userIdText = new JTextField(20);
        userIdText.setBounds(100, 20, 165, 25);
        panel.add(userIdText);

        JLabel itemIdLabel = new JLabel("Item ID:");
        itemIdLabel.setBounds(10, 50, 80, 25);
        panel.add(itemIdLabel);

        JTextField itemIdText = new JTextField(20);
        itemIdText.setBounds(100, 50, 165, 25);
        panel.add(itemIdText);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 80, 80, 25);
        panel.add(amountLabel);

        JTextField amountText = new JTextField(20);
        amountText.setBounds(100, 80, 165, 25);
        panel.add(amountText);

        JButton addFineButton = new JButton("Add Fine");
        addFineButton.setBounds(10, 120, 150, 25);
        addFineButton.addActionListener(e -> {
            int userId = Integer.parseInt(userIdText.getText());
            int itemId = Integer.parseInt(itemIdText.getText());
            double amount = Double.parseDouble(amountText.getText());

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO fines (user_id, item_id, amount, status) VALUES (?, ?, ?, 'unpaid')";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, userId);
                statement.setInt(2, itemId);
                statement.setDouble(3, amount);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Fine added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding fine!");
            }
        });
        panel.add(addFineButton);

        JButton payFineButton = new JButton("Pay Fine");
        payFineButton.setBounds(10, 150, 150, 25);
        payFineButton.addActionListener(e -> {
            int userId = Integer.parseInt(userIdText.getText());
            int itemId = Integer.parseInt(itemIdText.getText());

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "UPDATE fines SET status = 'paid' WHERE user_id = ? AND item_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, userId);
                statement.setInt(2, itemId);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Fine paid successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error paying fine!");
            }
        });
        panel.add(payFineButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FineManagementScreen screen = new FineManagementScreen();
            screen.setVisible(true);
        });
    }
}

