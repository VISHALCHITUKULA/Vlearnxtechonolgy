package TASK6;

import javax.swing.*;
import java.awt.*;

public class HospitalManagementApp extends JFrame {
    public HospitalManagementApp() {
        setTitle("Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton registerPatientButton = new JButton("Register Patient");
        registerPatientButton.setBounds(10, 20, 200, 25);
        registerPatientButton.addActionListener(e -> new RegisterPatientScreen().setVisible(true));
        panel.add(registerPatientButton);

        JButton scheduleAppointmentButton = new JButton("Schedule Appointment");
        scheduleAppointmentButton.setBounds(10, 50, 200, 25);
        scheduleAppointmentButton.addActionListener(e -> new ScheduleAppointmentScreen().setVisible(true));
        panel.add(scheduleAppointmentButton);

        JButton ehrButton = new JButton("Electronic Health Records");
        ehrButton.setBounds(10, 80, 200, 25);
        ehrButton.addActionListener(e -> new EHRScreen().setVisible(true));
        panel.add(ehrButton);

        JButton billingButton = new JButton("Billing and Invoicing");
        billingButton.setBounds(10, 110, 200, 25);
        billingButton.addActionListener(e -> new BillingScreen().setVisible(true));
        panel.add(billingButton);

        JButton inventoryButton = new JButton("Inventory Management");
        inventoryButton.setBounds(10, 140, 200, 25);
        inventoryButton.addActionListener(e -> new InventoryScreen().setVisible(true));
        panel.add(inventoryButton);

        JButton staffButton = new JButton("Staff Management");
        staffButton.setBounds(10, 170, 200, 25);
        staffButton.addActionListener(e -> new StaffManagementScreen().setVisible(true));
        panel.add(staffButton);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HospitalManagementApp frame = new HospitalManagementApp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
