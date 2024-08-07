package TASK3;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddProductScreen extends JFrame {
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;

    public AddProductScreen() {
        setTitle("Add Product");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        panel.add(quantityField);

        panel.add(new JLabel("Price:"));
        priceField = new JTextField();
        panel.add(priceField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            try {
                addProduct();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding product.");
            }
        });
        panel.add(addButton);

        add(panel);
    }

    private void addProduct() throws SQLException {
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());

        ProductDAO.addProduct(new Product(name, quantity, price));
        JOptionPane.showMessageDialog(this, "Product added successfully!");
        dispose();
    }
}
