package gui;

import dao.StudentDAO;

import javax.swing.*;
import java.awt.*;

public class DeleteStudentFrame extends JFrame {

    JTextField idField;
    JButton deleteBtn;

    public DeleteStudentFrame() {

        setTitle("Delete Student");
        setSize(300, 150);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Student ID"));

        idField = new JTextField();
        add(idField);

        deleteBtn = new JButton("Delete");
        add(deleteBtn);

        deleteBtn.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());

                StudentDAO dao = new StudentDAO();

                dao.deleteStudent(id);

                JOptionPane.showMessageDialog(
                        this,
                        "Student Deleted Successfully!"
                );

                idField.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Student ID!"
                );
            }

        });

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}