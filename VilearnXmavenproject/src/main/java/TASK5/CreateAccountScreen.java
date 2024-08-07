package TASK5;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateAccountScreen extends JFrame {
    private JTextField userIdField;
    private JTextField balanceField;
    private JTextField accountTypeField;
    private JButton createButton;

    public CreateAccountScreen() {
        setTitle("Create Account");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("User ID:"));
        userIdField = new JTextField();
        panel.add(userIdField);

        panel.add(new JLabel("Balance:"));
        balanceField = new JTextField();
        panel.add(balanceField);

        panel.add(new JLabel("Account Type:"));
        accountTypeField = new JTextField();
        panel.add(accountTypeField);

        createButton = new JButton("Create Account");
        createButton.addActionListener(e -> createAccount());
        panel.add(createButton);

        add(panel);
    }

    private void createAccount() {
        int userId = Integer.parseInt(userIdField.getText());
        double balance = Double.parseDouble(balanceField.getText());
        String accountType = accountTypeField.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO account (user_id, balance, account_type) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                stmt.setDouble(2, balance);
                stmt.setString(3, accountType);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Account created successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error creating account.");
        }
    }
}
