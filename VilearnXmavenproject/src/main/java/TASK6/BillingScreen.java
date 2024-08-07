package TASK6;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillingScreen extends JFrame {
    public BillingScreen() {
        setTitle("Billing and Invoicing");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel patientIdLabel = new JLabel("Patient ID:");
        patientIdLabel.setBounds(10, 20, 80, 25);
        panel.add(patientIdLabel);

        JTextField patientIdText = new JTextField(20);
        patientIdText.setBounds(100, 20, 165, 25);
        panel.add(patientIdText);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 50, 80, 25);
        panel.add(amountLabel);

        JTextField amountText = new JTextField(20);
        amountText.setBounds(100, 50, 165, 25);
        panel.add(amountText);

        JButton generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBounds(10, 80, 150, 25);
        generateBillButton.addActionListener(e -> {
            int patientId = Integer.parseInt(patientIdText.getText());
            double amount = Double.parseDouble(amountText.getText());

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO billing (patient_id, amount, payment_status) VALUES (?, ?, 'Pending')";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, patientId);
                statement.setDouble(2, amount);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Bill generated successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error generating bill!");
            }
        });
        panel.add(generateBillButton);
    }
}
