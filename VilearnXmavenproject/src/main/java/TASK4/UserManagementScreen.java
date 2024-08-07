package TASK4;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserManagementScreen extends JFrame {
    public UserManagementScreen() {
        setTitle("User Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 20, 80, 25);
        panel.add(usernameLabel);

        JTextField usernameText = new JTextField(20);
        usernameText.setBounds(100, 20, 165, 25);
        panel.add(usernameText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(10, 80, 80, 25);
        panel.add(roleLabel);

        JTextField roleText = new JTextField(20);
        roleText.setBounds(100, 80, 165, 25);
        panel.add(roleText);

        JButton addUserButton = new JButton("Add User");
        addUserButton.setBounds(10, 120, 150, 25);
        addUserButton.addActionListener(e -> {
            String username = usernameText.getText();
            String password = new String(passwordText.getPassword());
            String role = roleText.getText();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, role);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "User added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding user!");
            }
        });
        panel.add(addUserButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserManagementScreen screen = new UserManagementScreen();
            screen.setVisible(true);
        });
    }
}

