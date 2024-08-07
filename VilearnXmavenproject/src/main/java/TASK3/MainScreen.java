package TASK3;

import javax.swing.*;

public class MainScreen extends JFrame {
    public MainScreen() {
        setTitle("Inventory Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu productMenu = new JMenu("Product");
        JMenuItem addProduct = new JMenuItem("Add Product");
        JMenuItem editProduct = new JMenuItem("Edit Product");
        JMenuItem deleteProduct = new JMenuItem("Delete Product");
        productMenu.add(addProduct);
        productMenu.add(editProduct);
        productMenu.add(deleteProduct);
        menuBar.add(productMenu);

        JMenu reportMenu = new JMenu("Reports");
        JMenuItem lowStockReport = new JMenuItem("Low Stock Report");
        reportMenu.add(lowStockReport);
        menuBar.add(reportMenu);

        setJMenuBar(menuBar);

        addProduct.addActionListener(e -> new AddProductScreen().setVisible(true));
        editProduct.addActionListener(e -> new EditProductScreen().setVisible(true));
        deleteProduct.addActionListener(e -> new DeleteProductScreen().setVisible(true));
        lowStockReport.addActionListener(e -> new LowStockReportScreen().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        });
    }
}
