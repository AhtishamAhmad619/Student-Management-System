package gui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import java.awt.*;

public class AddStudentFrame extends JFrame {

    JTextField nameField;
    JTextField ageField;
    JTextField courseField;
    JTextField marksField;

    JButton saveBtn;

    public AddStudentFrame() {

        setTitle("Add Student");

        setSize(400,300);

        setLayout(new GridLayout(5,2));

        add(new JLabel("Name"));

        nameField = new JTextField();

        add(nameField);

        add(new JLabel("Age"));

        ageField = new JTextField();

        add(ageField);

        add(new JLabel("Course"));

        courseField = new JTextField();

        add(courseField);

        add(new JLabel("Marks"));

        marksField = new JTextField();

        add(marksField);

        saveBtn = new JButton("Save");

        add(saveBtn);

        saveBtn.addActionListener(e -> {

            try {



                String name =
                        nameField.getText();

                int age =
                        Integer.parseInt(ageField.getText());

                String course =
                        courseField.getText();

                double marks =
                        Double.parseDouble(marksField.getText());

                Student student =
                        new Student( name, age, course, marks);

                StudentDAO dao =
                        new StudentDAO();

                dao.addStudent(student);

                JOptionPane.showMessageDialog(
                        this,
                        "Student Added Successfully!"
                );

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input"
                );
            }
        });

        setLocationRelativeTo(null);

        setVisible(true);
    }
}
