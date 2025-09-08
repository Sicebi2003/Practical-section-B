package rc;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author RC_Student_lab
 */
// Main application class
public class GradeManagementSystem {
    private ArrayList<Student> students;
    private Scanner scanner;
    private int nextId;
    
    public GradeManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
        nextId = 1;
    }
    
    public void run() {
        displayWelcomeMessage();
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1: addStudent(); break;
                case 2: addGradeToStudent(); break;
                case 3: viewStudentReport(); break;
                case 4: viewAllStudentsReport(); break;
                case 5: updateStudentDetails(); break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using the Grade Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void displayWelcomeMessage() {
        System.out.println("================================================");
        System.out.println("      STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("================================================");
        System.out.println("This system helps manage student records and grades");
        System.out.println("You can add students, record grades, and generate reports");
        System.out.println("================================================");
    }
    
    private void displayMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add New Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View Student Report");
        System.out.println("4. View All Students Report");
        System.out.println("5. Update Student Details");
        System.out.println("6. Exit");
        System.out.println("=====================");
    }
    
    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        String name = getStringInput("Enter student name: ");
        String course = getStringInput("Enter course: ");
        
        Student student = new Student(nextId++, name, course);
        students.add(student);
        
        System.out.println("Student added successfully with ID: " + student.getId());
    }
    
    private void addGradeToStudent() {
        if (students.isEmpty()) {
            System.out.println("No students available. Please add a student first.");
            return;
        }
        
        System.out.println("\n--- Add Grade to Student ---");
        viewAllStudents();
        
        int studentId = getIntInput("Enter student ID: ");
        Student student = findStudentById(studentId);
        
        if (student != null) {
            double grade = getDoubleInput("Enter grade (0-100): ");
            if (grade >= 0 && grade <= 100) {
                student.addGrade(grade);
                System.out.println("Grade added successfully.");
                System.out.println("New average: " + String.format("%.2f", student.calculateAverage()));
            } else {
                System.out.println("Invalid grade. Please enter a value between 0 and 100.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }
    
    private void viewStudentReport() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        
        System.out.println("\n--- Student Report ---");
        viewAllStudents();
        
        int studentId = getIntInput("Enter student ID to view report: ");
        Student student = findStudentById(studentId);
        
        if (student != null) {
            System.out.println("\n=== STUDENT REPORT ===");
            System.out.println("Student: " + student.getName());
            System.out.println("ID: " + student.getId());
            System.out.println("Course: " + student.getCourse());
            System.out.println("Grades: " + student.getGrades());
            System.out.println("Average: " + String.format("%.2f", student.calculateAverage()));
            System.out.println("Grade: " + student.getGradeLetter());
            System.out.println("======================");
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }
    
    private void viewAllStudentsReport() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        
        System.out.println("\n=== ALL STUDENTS REPORT ===");
        System.out.println("Total Students: " + students.size());
        System.out.println("----------------------------------------");
        
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + 
                             " | Name: " + student.getName() +
                             " | Course: " + student.getCourse() +
                             " | Avg: " + String.format("%.2f", student.calculateAverage()) +
                             " | Grade: " + student.getGradeLetter());
        }
        System.out.println("----------------------------------------");
        
        displayClassStatistics();
    }
    
    private void displayClassStatistics() {
        if (students.isEmpty()) return;
        
        double classTotal = 0;
        int gradeCount = 0;
        int aCount = 0, bCount = 0, cCount = 0, dCount = 0, fCount = 0;
        
        for (Student student : students) {
            double avg = student.calculateAverage();
            classTotal += avg;
            gradeCount++;
            
            String gradeLetter = student.getGradeLetter();
            switch (gradeLetter) {
                case "A": aCount++; break;
                case "B": bCount++; break;
                case "C": cCount++; break;
                case "D": dCount++; break;
                case "F": fCount++; break;
            }
        }
        
        double classAverage = classTotal / gradeCount;
        
        System.out.println("\n=== CLASS STATISTICS ===");
        System.out.println("Class Average: " + String.format("%.2f", classAverage));
        System.out.println("Grade Distribution:");
        System.out.println("A: " + aCount + " students");
        System.out.println("B: " + bCount + " students");
        System.out.println("C: " + cCount + " students");
        System.out.println("D: " + dCount + " students");
        System.out.println("F: " + fCount + " students");
        System.out.println("=======================");
    }
    
    private void updateStudentDetails() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        
        System.out.println("\n--- Update Student Details ---");
        viewAllStudents();
        
        int studentId = getIntInput("Enter student ID to update: ");
        Student student = findStudentById(studentId);
        
        if (student != null) {
            System.out.println("Current details: " + student);
            
            String newName = getStringInput("Enter new name (press Enter to keep current): ");
            if (!newName.trim().isEmpty()) {
                student.setName(newName);
            }
            
            String newCourse = getStringInput("Enter new course (press Enter to keep current): ");
            if (!newCourse.trim().isEmpty()) {
                student.setCourse(newCourse);
            }
            
            System.out.println("Student details updated successfully.");
            System.out.println("Updated details: " + student);
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }
    
    private void viewAllStudents() {
        System.out.println("Registered Students:");
        for (Student student : students) {
            System.out.println("  " + student.getId() + ": " + student.getName() + 
                             " (" + student.getCourse() + ")");
        }
    }
    
    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    // Utility methods for input validation
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    // Main method
    public static void main(String[] args) {
        GradeManagementSystem system = new GradeManagementSystem();
        system.run();
    }
}
