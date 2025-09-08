/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rc;
import java.util.ArrayList;
/**
 *
 * @author RC_Student_lab
 */
// Student class inheriting from Person
public class Student extends Person {
    private ArrayList<Double> grades;
    private String course;
    
    public Student(int id, String name, String course) {
        super(id, name); // Calling parent constructor
        this.course = course;
        this.grades = new ArrayList<>();
    }
    
    public void addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        }
    }
    
    public ArrayList<Double> getGrades() {
        return new ArrayList<>(grades); // Return copy for information hiding
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        if (course != null && !course.trim().isEmpty()) {
            this.course = course;
        }
    }
    
    public double calculateAverage() {
        if (grades.isEmpty()) return 0;
        
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
    
    public String getGradeLetter() {
        double average = calculateAverage();
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        return "F";
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Course: " + course + ", Average: " + 
               String.format("%.2f", calculateAverage()) + " (" + getGradeLetter() + ")";
    }
}

