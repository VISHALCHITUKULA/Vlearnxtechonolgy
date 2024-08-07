package TASK4;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchScreen extends JFrame {
    public SearchScreen() {
        setTitle("Search Items");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(10, 20, 80, 25);
        panel.add(searchLabel);

        JTextField searchText = new JTextField(20);
        searchText.setBounds(100, 20, 165, 25);
        panel.add(searchText);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(10, 60, 150, 25);
        searchButton.addActionListener(e -> {
            String searchQuery = searchText.getText();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "SELECT * FROM items WHERE title LIKE ? OR author LIKE ? OR category LIKE ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, "%" + searchQuery + "%");
                statement.setString(2, "%" + searchQuery + "%");
                statement.setString(3, "%" + searchQuery + "%");
                ResultSet resultSet = statement.executeQuery();

                JTextArea resultsArea = new JTextArea();
                resultsArea.setBounds(10, 100, 350, 150);
                panel.add(resultsArea);

                while (resultSet.next()) {
                    String result = "ID: " + resultSet.getInt("item_id") +
                            ", Title: " + resultSet.getString("title") +
                            ", Author: " + resultSet.getString("author") +
                            ", Category: " + resultSet.getString("category") +
                            ", Type: " + resultSet.getString("type") + "\n";
                    resultsArea.append(result);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error searching items!");
            }
        });
        panel.add(searchButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SearchScreen screen = new SearchScreen();
            screen.setVisible(true);
        });
    }
}

