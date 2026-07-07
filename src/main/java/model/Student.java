package model;

public class Student {

    private int studentId;
    private String name;
    private int age;
    private String course;
    private double marks;
    private String grade;

    public Student(String name, int age, String course ,double marks) {

        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }
    public Student(int studentId, String name, int age, String course, double marks) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    public int getStudentId() {
        return studentId;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }
    public String getGrade() {
        return grade;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Student() {
    }

}
