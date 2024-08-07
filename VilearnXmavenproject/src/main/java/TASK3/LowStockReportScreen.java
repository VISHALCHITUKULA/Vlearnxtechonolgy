package TASK3;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class LowStockReportScreen extends JFrame {
    private JTextArea reportArea;

    public LowStockReportScreen() {
        setTitle("Low Stock Report");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        panel.add(new JScrollPane(reportArea), BorderLayout.CENTER);

        generateReport();

        add(panel);
    }

    private void generateReport() {
        try {
            List<Product> lowStockProducts = ProductDAO.getLowStockProducts();
            StringBuilder report = new StringBuilder();
            for (Product product : lowStockProducts) {
                report.append("Product ID: ").append(product.getId())
                      .append(", Name: ").append(product.getName())
                      .append(", Quantity: ").append(product.getQuantity())
                      .append(", Price: ").append(product.getPrice())
                      .append("\n");
            }
            reportArea.setText(report.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating report.");
        }
    }
}
