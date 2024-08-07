package TASK5;

import javax.swing.*;
import java.awt.*;

public class OnlineBankingApp extends JFrame {
    public OnlineBankingApp() {
        setTitle("Online Banking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(10, 20, 200, 25);
        createAccountButton.addActionListener(e -> new CreateAccountScreen().setVisible(true));
        panel.add(createAccountButton);

        JButton depositButton = new JButton("Deposit Funds");
        depositButton.setBounds(10, 50, 200, 25);
        depositButton.addActionListener(e -> new DepositFundsScreen().setVisible(true));
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw Funds");
        withdrawButton.setBounds(10, 80, 200, 25);
        withdrawButton.addActionListener(e -> new WithdrawFundsScreen().setVisible(true));
        panel.add(withdrawButton);

        JButton transferButton = new JButton("Transfer Funds");
        transferButton.setBounds(10, 110, 200, 25);
        transferButton.addActionListener(e -> new TransferFundsScreen().setVisible(true));
        panel.add(transferButton);

        JButton viewHistoryButton = new JButton("View Transaction History");
        viewHistoryButton.setBounds(10, 140, 200, 25);
        viewHistoryButton.addActionListener(e -> new ViewTransactionHistoryScreen().setVisible(true));
        panel.add(viewHistoryButton);

        JButton manageInfoButton = new JButton("Manage Personal Information");
        manageInfoButton.setBounds(10, 170, 200, 25);
        manageInfoButton.addActionListener(e -> new ManagePersonalInfoScreen().setVisible(true));
        panel.add(manageInfoButton);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                OnlineBankingApp frame = new OnlineBankingApp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
