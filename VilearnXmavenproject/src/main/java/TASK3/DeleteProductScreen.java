package TASK3;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DeleteProductScreen extends JFrame {
    private JTextField idField;

    public DeleteProductScreen() {
        setTitle("Delete Product");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        panel.add(new JLabel("Product ID:"));
        idField = new JTextField();
        panel.add(idField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            try {
                deleteProduct();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting product.");
            }
        });
        panel.add(deleteButton);

        add(panel);
    }

    private void deleteProduct() throws SQLException {
        int id = Integer.parseInt(idField.getText());

        ProductDAO.deleteProduct(id);
        JOptionPane.showMessageDialog(this, "Product deleted successfully!");
        dispose();
    }
}
