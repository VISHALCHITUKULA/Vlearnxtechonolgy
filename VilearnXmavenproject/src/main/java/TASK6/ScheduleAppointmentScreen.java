package TASK6;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class ScheduleAppointmentScreen extends JFrame {
    public ScheduleAppointmentScreen() {
        setTitle("Schedule Appointment");
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

        JLabel doctorLabel = new JLabel("Doctor:");
        doctorLabel.setBounds(10, 50, 80, 25);
        panel.add(doctorLabel);

        JTextField doctorText = new JTextField(20);
        doctorText.setBounds(100, 50, 165, 25);
        panel.add(doctorText);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(10, 80, 80, 25);
        panel.add(dateLabel);

        JTextField dateText = new JTextField(20);
        dateText.setBounds(100, 80, 165, 25);
        panel.add(dateText);

        JButton scheduleButton = new JButton("Schedule");
        scheduleButton.setBounds(10, 110, 150, 25);
        scheduleButton.addActionListener(e -> {
            int patientId = Integer.parseInt(patientIdText.getText());
            String doctor = doctorText.getText();
            Date date = Date.valueOf(dateText.getText()); // format: yyyy-mm-dd

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO appointments (patient_id, doctor_name, appointment_date, status) VALUES (?, ?, ?, 'Scheduled')";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, patientId);
                statement.setString(2, doctor);
                statement.setDate(3, date);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Appointment scheduled successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error scheduling appointment!");
            }
        });
        panel.add(scheduleButton);
    }
}
