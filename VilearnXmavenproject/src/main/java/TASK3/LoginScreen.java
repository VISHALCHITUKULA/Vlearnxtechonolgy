package TASK3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    authenticateUser();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginScreen.this, "Error logging in.");
                }
            }
        });
        panel.add(loginButton);

        add(panel);
    }

    private void authenticateUser() throws SQLException {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (UserAuthentication.authenticate(username, password)) {
            dispose();
            MainScreen mainScreen = new MainScreen();
            mainScreen.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }
}
