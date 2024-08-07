package TASK4;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class CheckoutReturnScreen extends JFrame {
    public CheckoutReturnScreen() {
        setTitle("Checkout/Return Items");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel itemIdLabel = new JLabel("Item ID:");
        itemIdLabel.setBounds(10, 20, 80, 25);
        panel.add(itemIdLabel);

        JTextField itemIdText = new JTextField(20);
        itemIdText.setBounds(100, 20, 165, 25);
        panel.add(itemIdText);

        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(10, 50, 80, 25);
        panel.add(userIdLabel);

        JTextField userIdText = new JTextField(20);
        userIdText.setBounds(100, 50, 165, 25);
        panel.add(userIdText);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(10, 90, 150, 25);
        checkoutButton.addActionListener(e -> {
            int itemId = Integer.parseInt(itemIdText.getText());
            int userId = Integer.parseInt(userIdText.getText());

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "UPDATE items SET is_checked_out = TRUE, due_date = ? WHERE item_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDate(1, Date.valueOf("2022-12-31")); // replace with actual due date logic
                statement.setInt(2, itemId);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Item checked out successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error checking out item!");
            }
        });
        panel.add(checkoutButton);

        JButton returnButton = new JButton("Return");
        returnButton.setBounds(10, 120, 150, 25);
        returnButton.addActionListener(e -> {
            int itemId = Integer.parseInt(itemIdText.getText());

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "UPDATE items SET is_checked_out = FALSE, due_date = NULL WHERE item_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, itemId);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Item returned successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error returning item!");
            }
        });
        panel.add(returnButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CheckoutReturnScreen screen = new CheckoutReturnScreen();
            screen.setVisible(true);
        });
    }
}

