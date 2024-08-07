package TASK3;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EditProductScreen extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;

    public EditProductScreen() {
        setTitle("Edit Product");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("Product ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        panel.add(quantityField);

        panel.add(new JLabel("Price:"));
        priceField = new JTextField();
        panel.add(priceField);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            try {
                editProduct();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error editing product.");
            }
        });
        panel.add(editButton);

        add(panel);
    }

    private void editProduct() throws SQLException {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());

        ProductDAO.editProduct(new Product(id, name, quantity, price));
        JOptionPane.showMessageDialog(this, "Product edited successfully!");
        dispose();
    }
}
