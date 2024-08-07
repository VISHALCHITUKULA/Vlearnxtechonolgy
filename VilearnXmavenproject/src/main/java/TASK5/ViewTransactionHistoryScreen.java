package TASK5;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewTransactionHistoryScreen extends JFrame {
    private JTextField accountIdField;
    private JButton viewHistoryButton;
    private JTextArea historyArea;

    public ViewTransactionHistoryScreen() {
        setTitle("Transaction History");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Account ID:"));
        accountIdField = new JTextField();
        inputPanel.add(accountIdField);

        viewHistoryButton = new JButton("View History");
        viewHistoryButton.addActionListener(e -> viewTransactionHistory());
        inputPanel.add(viewHistoryButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        panel.add(new JScrollPane(historyArea), BorderLayout.CENTER);

        add(panel);
    }

    private void viewTransactionHistory() {
        int accountId = Integer.parseInt(accountIdField.getText());

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM transaction WHERE account_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, accountId);
                try (ResultSet rs = stmt.executeQuery()) {
                    StringBuilder history = new StringBuilder();
                    while (rs.next()) {
                        history.append("Transaction ID: ").append(rs.getInt("transaction_id"))
                               .append(", Amount: ").append(rs.getDouble("amount"))
                               .append(", Type: ").append(rs.getString("transaction_type"))
                               .append(", Date: ").append(rs.getTimestamp("transaction_date"))
                               .append("\n");
                    }
                    historyArea.setText(history.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving transaction history.");
        }
    }
}
