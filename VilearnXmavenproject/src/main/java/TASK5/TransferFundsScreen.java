package TASK5;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferFundsScreen extends JFrame {
    private JTextField fromAccountIdField;
    private JTextField toAccountIdField;
    private JTextField amountField;
    private JButton transferButton;

    public TransferFundsScreen() {
        setTitle("Transfer Funds");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("From Account ID:"));
        fromAccountIdField = new JTextField();
        panel.add(fromAccountIdField);

        panel.add(new JLabel("To Account ID:"));
        toAccountIdField = new JTextField();
        panel.add(toAccountIdField);

        panel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);

        transferButton = new JButton("Transfer");
        transferButton.addActionListener(e -> transferFunds());
        panel.add(transferButton);

        add(panel);
    }

    private void transferFunds() {
        int fromAccountId = Integer.parseInt(fromAccountIdField.getText());
        int toAccountId = Integer.parseInt(toAccountIdField.getText());
        double amount = Double.parseDouble(amountField.getText());

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sqlWithdraw = "UPDATE account SET balance = balance - ? WHERE account_id = ?";
            String sqlDeposit = "UPDATE account SET balance = balance + ? WHERE account_id = ?";
            try (PreparedStatement stmtWithdraw = conn.prepareStatement(sqlWithdraw);
                 PreparedStatement stmtDeposit = conn.prepareStatement(sqlDeposit)) {

                conn.setAutoCommit(false);

                stmtWithdraw.setDouble(1, amount);
                stmtWithdraw.setInt(2, fromAccountId);
                stmtWithdraw.executeUpdate();

                stmtDeposit.setDouble(1, amount);
                stmtDeposit.setInt(2, toAccountId);
                stmtDeposit.executeUpdate();

                conn.commit();
                JOptionPane.showMessageDialog(this, "Funds transferred successfully!");
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error transferring funds.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error transferring funds.");
        }
    }
}
