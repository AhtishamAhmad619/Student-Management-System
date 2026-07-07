package gui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    JButton addBtn;
    JButton viewBtn;
    JButton searchBtn;
    JButton updateBtn;
    JButton deleteBtn;
    JButton dashboardBtn;

    public MainFrame() {

        setTitle("Student Management System");

        setSize(700,500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10,10));
        JLabel titleLabel = new JLabel(
                "Student Management System",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );
        titleLabel.setForeground(
                new Color(25, 118, 210)
        );

        add(titleLabel, BorderLayout.NORTH);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));



        addBtn = new JButton("Add Student");
        viewBtn = new JButton("View Students");
        searchBtn = new JButton("Search Student");
        updateBtn = new JButton("Update Student");
        deleteBtn = new JButton("Delete Student");
        dashboardBtn = new JButton("Dashboard");


        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(dashboardBtn);

        JButton[] buttons = {
                addBtn,
                viewBtn,
                searchBtn,
                updateBtn,
                deleteBtn,
                dashboardBtn
        };
        for (JButton btn : buttons) {

            btn.setFont(new Font("Arial", Font.BOLD, 16));

            btn.setFocusPainted(false);

            btn.setBackground(new Color(33, 150, 243));

            btn.setForeground(Color.WHITE);

            btn.setOpaque(true);

            btn.setBorderPainted(false);
        }


        add(buttonPanel, BorderLayout.CENTER);
        JLabel footer = new JLabel(
                "Student Management System v1.0",
                SwingConstants.CENTER
        );

        footer.setFont(new Font("Arial", Font.PLAIN, 12));

        add(footer, BorderLayout.SOUTH);


        addBtn.addActionListener(
                e -> new AddStudentFrame()
        );

        viewBtn.addActionListener(
                e -> new ViewStudentsFrame()
        );

        updateBtn.addActionListener(
                e -> new UpdateStudentFrame()
        );

        deleteBtn.addActionListener(
                e -> new DeleteStudentFrame()
        );

        searchBtn.addActionListener(
                e -> new SearchStudentFrame()
        );

        dashboardBtn.addActionListener(
                e -> new DashboardFrame()
        );

        setVisible(true);
    }
}
