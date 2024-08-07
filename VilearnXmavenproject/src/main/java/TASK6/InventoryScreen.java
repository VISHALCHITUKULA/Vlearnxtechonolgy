package TASK6;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryScreen extends JFrame {
    public InventoryScreen() {
        setTitle("Inventory Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel itemNameLabel = new JLabel("Item Name:");
        itemNameLabel.setBounds(10, 20, 80, 25);
        panel.add(itemNameLabel);

        JTextField itemNameText = new JTextField(20);
        itemNameText.setBounds(100, 20, 165, 25);
        panel.add(itemNameText);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(10, 50, 80, 25);
        panel.add(quantityLabel);

        JTextField quantityText = new JTextField(20);
        quantityText.setBounds(100, 50, 165, 25);
        panel.add(quantityText);

        JLabel supplierLabel = new JLabel("Supplier:");
        supplierLabel.setBounds(10, 80, 80, 25);
        panel.add(supplierLabel);

        JTextField supplierText = new JTextField(20);
        supplierText.setBounds(100, 80, 165, 25);
        panel.add(supplierText);

        JButton addItemButton = new JButton("Add Item");
        addItemButton.setBounds(10, 110, 150, 25);
        addItemButton.addActionListener(e -> {
            String itemName = itemNameText.getText();
            int quantity = Integer.parseInt(quantityText.getText());
            String supplier = supplierText.getText();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO inventory (item_name, quantity, supplier) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, itemName);
                statement.setInt(2, quantity);
                statement.setString(3, supplier);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Item added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding item!");
            }
        });
        panel.add(addItemButton);
    }
}
