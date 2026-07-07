package gui;

import dao.StudentDAO;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    JLabel totalLabel;
    JLabel averageLabel;
    JLabel highestLabel;
    JLabel lowestLabel;

    public DashboardFrame() {

        setTitle("Student Statistics Dashboard");
        setSize(450,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(4,1,10,10));

        totalLabel = new JLabel();
        averageLabel = new JLabel();
        highestLabel = new JLabel();
        lowestLabel = new JLabel();

        add(totalLabel);
        add(averageLabel);
        add(highestLabel);
        add(lowestLabel);

        loadStatistics();

        setVisible(true);
    }

    private void loadStatistics() {

        StudentDAO dao = new StudentDAO();

        totalLabel.setText("Total Students : " + dao.countStudents());

        averageLabel.setText("Average Marks : " + dao.averageMarks());

        highestLabel.setText("Highest Marks : " + dao.highestMarks());

        lowestLabel.setText("Lowest Marks : " + dao.lowestMarks());

    }

}