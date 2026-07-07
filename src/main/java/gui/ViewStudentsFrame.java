package gui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewStudentsFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewStudentsFrame() {

        setTitle("Student Report");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        // Columns
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Course");
        model.addColumn("Marks");

        table = new JTable(model);

        loadData();

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void loadData() {

        StudentDAO dao = new StudentDAO();
        List<Student> students = dao.getAllStudentsUsingProcedure();

        for (Student s : students) {

            model.addRow(new Object[]{
                    s.getStudentId(),
                    s.getName(),
                    s.getAge(),
                    s.getCourse(),
                    s.getMarks()
            });
        }
    }
}
