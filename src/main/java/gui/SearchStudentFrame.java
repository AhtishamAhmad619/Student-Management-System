package gui;

import dao.StudentDAO;
import model.Student;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class SearchStudentFrame extends JFrame {

    JTextField idField;
    JButton searchBtn;
    JTextArea resultArea;

    public SearchStudentFrame() {

        setTitle("Search Student");
        setSize(400,300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("ID or Name:"));

        idField = new JTextField(10);
        topPanel.add(idField);

        searchBtn = new JButton("Search");
        topPanel.add(searchBtn);

        add(topPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        searchBtn.addActionListener(e -> {

            String input = idField.getText().trim();

            StudentDAO dao = new StudentDAO();

            try {

                // Search by ID
                int id = Integer.parseInt(input);

                Student student = dao.getStudentById(id);

                if (student != null) {

                    resultArea.setText(
                            "ID : " + student.getStudentId() +
                                    "\nName : " + student.getName() +
                                    "\nAge : " + student.getAge() +
                                    "\nCourse : " + student.getCourse() +
                                    "\nMarks : " + student.getMarks()
                    );

                } else {

                    resultArea.setText("Student Not Found");
                }

            } catch (NumberFormatException ex) {

                // Search by Name
                java.util.List<Student> students = dao.searchStudentsByName(input);

                // If no name found, search by Course
                if (students.isEmpty()) {
                    students = dao.searchStudentsByCourse(input);
                }

                if (students.isEmpty()) {

                    resultArea.setText("No student found.");

                } else {

                    resultArea.setText("");

                    for (Student s : students) {

                        resultArea.append(
                                "ID : " + s.getStudentId() +
                                        "\nName : " + s.getName() +
                                        "\nAge : " + s.getAge() +
                                        "\nCourse : " + s.getCourse() +
                                        "\nMarks : " + s.getMarks() +
                                        "\n-------------------------\n"
                        );
                    }
                }
            }

        });
        setVisible(true);
    }

}
