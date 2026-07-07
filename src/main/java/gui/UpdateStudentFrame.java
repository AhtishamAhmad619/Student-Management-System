package gui;

import dao.StudentDAO;

import javax.swing.*;
import java.awt.*;

public class UpdateStudentFrame extends JFrame {

    JTextField idField;
    JTextField marksField;
    JButton updateBtn;

    public UpdateStudentFrame() {

        setTitle("Update Student Marks");
        setSize(350, 200);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Student ID"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("New Marks"));
        marksField = new JTextField();
        add(marksField);

        updateBtn = new JButton("Update");
        add(updateBtn);

        // ✅ ActionListener goes INSIDE the constructor
        updateBtn.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());
                double marks = Double.parseDouble(marksField.getText());

                StudentDAO dao = new StudentDAO();
                dao.updateStudentMarks(id, marks);

                JOptionPane.showMessageDialog(
                        this,
                        "Marks Updated Successfully!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input!"
                );
            }

        });

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}