package TASK4;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageItemsScreen extends JFrame {
    public ManageItemsScreen() {
        setTitle("Manage Items");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        JTextField titleText = new JTextField(20);
        titleText.setBounds(100, 20, 165, 25);
        panel.add(titleText);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(10, 50, 80, 25);
        panel.add(authorLabel);

        JTextField authorText = new JTextField(20);
        authorText.setBounds(100, 50, 165, 25);
        panel.add(authorText);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(10, 80, 80, 25);
        panel.add(categoryLabel);

        JTextField categoryText = new JTextField(20);
        categoryText.setBounds(100, 80, 165, 25);
        panel.add(categoryText);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(10, 110, 80, 25);
        panel.add(typeLabel);

        JTextField typeText = new JTextField(20);
        typeText.setBounds(100, 110, 165, 25);
        panel.add(typeText);

        JButton addButton = new JButton("Add Item");
        addButton.setBounds(10, 150, 150, 25);
        addButton.addActionListener(e -> {
            String title = titleText.getText();
            String author = authorText.getText();
            String category = categoryText.getText();
            String type = typeText.getText();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO items (title, author, category, type) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, title);
                statement.setString(2, author);
                statement.setString(3, category);
                statement.setString(4, type);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Item added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding item!");
            }
        });
        panel.add(addButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ManageItemsScreen screen = new ManageItemsScreen();
            screen.setVisible(true);
        });
    }
}
