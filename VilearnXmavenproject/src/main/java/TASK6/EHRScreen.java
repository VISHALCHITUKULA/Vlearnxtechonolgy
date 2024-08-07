package TASK6;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EHRScreen extends JFrame {
    public EHRScreen() {
        setTitle("Electronic Health Records");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel patientIdLabel = new JLabel("Patient ID:");
        patientIdLabel.setBounds(10, 20, 80, 25);
        panel.add(patientIdLabel);

        JTextField patientIdText = new JTextField(20);
        patientIdText.setBounds(100, 20, 165, 25);
        panel.add(patientIdText);

        JTextArea ehrArea = new JTextArea();
        ehrArea.setBounds(10, 50, 360, 200);
        panel.add(ehrArea);

        JButton viewRecordButton = new JButton("View Record");
        viewRecordButton.setBounds(10, 260, 150, 25);
        viewRecordButton.addActionListener(e -> {
            int patientId = Integer.parseInt(patientIdText.getText());

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "SELECT diagnosis, treatment FROM ehr WHERE patient_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, patientId);
                ResultSet resultSet = statement.executeQuery();

                StringBuilder sb = new StringBuilder();
                while (resultSet.next()) {
                    sb.append("Diagnosis: ").append(resultSet.getString("diagnosis")).append("\n");
                    sb.append("Treatment: ").append(resultSet.getString("treatment")).append("\n\n");
                }
                ehrArea.setText(sb.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error retrieving EHR!");
            }
        });
        panel.add(viewRecordButton);
    }
}
