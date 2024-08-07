package TASK5;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WithdrawFundsScreen extends JFrame {
    private JTextField accountIdField;
    private JTextField amountField;
    private JButton withdrawButton;

    public WithdrawFundsScreen() {
        setTitle("Withdraw Funds");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Account ID:"));
        accountIdField = new JTextField();
        panel.add(accountIdField);

        panel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(e -> withdrawFunds());
        panel.add(withdrawButton);

        add(panel);
    }

    private void withdrawFunds() {
        int accountId = Integer.parseInt(accountIdField.getText());
        double amount = Double.parseDouble(amountField.getText());

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE account SET balance = balance - ? WHERE account_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDouble(1, amount);
                stmt.setInt(2, accountId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Funds withdrawn successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error withdrawing funds.");
        }
    }
}
