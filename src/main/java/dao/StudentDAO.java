package dao;

import model.Student;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void addStudent(Student student) {

        if (student.getAge() <= 0) {
            System.out.println("Invalid Age");
            return;
        }

        if (student.getMarks() < 0 || student.getMarks() > 100) {
            System.out.println("Marks must be between 0 and 100");
            return;
        }

        String sql =
                "INSERT INTO students(name, age, course, marks) VALUES (?, ?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setDouble(4, student.getMarks());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("model.Student Added Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewStudents() {

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs =
                     conn.prepareCall("{CALL GetAllStudents()}")) {

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("student_id") + " " +
                                rs.getString("name") + " " +
                                rs.getInt("age") + " " +
                                rs.getString("course") + " " +
                                rs.getDouble("marks")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void searchStudent(int id) {

        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println(
                        rs.getInt("student_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getString("course") + " | " +
                                rs.getDouble("marks")
                );

            } else {
                System.out.println("model.Student Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateStudentMarks(int id, double marks) {

        String sql =
                "UPDATE students SET marks = ? WHERE student_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDouble(1, marks);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("model.Student Updated Successfully!");
            } else {
                System.out.println("model.Student Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteStudent(int id) {

        String sql =
                "DELETE FROM students WHERE student_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("model.Student Deleted Successfully!");
            } else {
                System.out.println("model.Student Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int countStudents() {

        String sql = "SELECT COUNT(*) FROM students";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public double averageMarks() {

        String sql = "SELECT AVG(marks) FROM students";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public void topScorer() {

        String sql =
                "SELECT * FROM students ORDER BY marks DESC LIMIT 1";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {

                System.out.println(
                        "Top Scorer: " +
                                rs.getString("name") +
                                " | Marks: " +
                                rs.getDouble("marks")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchByCourse(String course) {

        String sql =
                "SELECT * FROM students WHERE course = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Replace ? with actual course name
            ps.setString(1, course);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("student_id") + " " +
                                rs.getString("name") + " " +
                                rs.getInt("age") + " " +
                                rs.getString("course")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByName(String name) {

        String sql =
                "SELECT * FROM students WHERE name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        rs.getInt("student_id") + " " +
                                rs.getString("name") + " " +
                                rs.getInt("age") + " " +
                                rs.getString("course") + " " +
                                rs.getDouble("marks")
                );
            }

            if (!found) {
                System.out.println("No student found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void studentGradeReport() {

        String sql =
                "SELECT student_id, name, marks, " +
                        "getGrade(marks) AS grade " +
                        "FROM students";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println(
                    "ID\tName\t\tMarks\tGrade");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("student_id") + "\t" +
                                rs.getString("name") + "\t\t" +
                                rs.getDouble("marks") + "\t" +
                                rs.getString("grade")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Student student = new Student();

                student.setStudentId(rs.getInt("student_Id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));


                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    public Student getStudentById(int id) {

        Student student = null;

        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }
    public List<Student> searchStudentsByName(String name) {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
    public List<Student> searchStudentsByCourse(String course) {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students WHERE course = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, course);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    public double highestMarks() {

        String sql = "SELECT MAX(marks) FROM students";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double lowestMarks() {

        String sql = "SELECT MIN(marks) FROM students";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public List<Student> getStudentsWithGrades() {

        List<Student> students = new ArrayList<>();

         String sql =
                "SELECT student_id, " +
                        "name, " +
                        "age, " +
                        "course, " +
                        "marks, " +
                        "CASE " +
                        "WHEN marks >= 90 THEN 'A' " +
                        "WHEN marks >= 80 THEN 'B' " +
                        "WHEN marks >= 70 THEN 'C' " +
                        "WHEN marks >= 60 THEN 'D' " +
                        "ELSE 'F' " +
                        "END AS grade " +
                        "FROM students";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Student student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));
                student.setGrade(rs.getString("grade"));

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
    public List<Student> getAllStudentsUsingProcedure() {

        List<Student> students = new ArrayList<>();

        String sql = "{CALL GetAllStudents()}";

        try (
                Connection conn = DBConnection.getConnection();
                CallableStatement cs = conn.prepareCall(sql);
                ResultSet rs = cs.executeQuery()
        ) {

            while (rs.next()) {

                Student student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}