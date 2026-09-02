package com.academy.student;

import java.util.Scanner;

public class StudentManager {

    private static final int MAX_STUDENTS = 20;

    private final Student[] students = new Student[MAX_STUDENTS];
    private int studentCount = 0;
    private final Scanner scanner;

    public StudentManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("====================================");
        System.out.println("Student Management System");
        System.out.println("====================================");
        System.out.println("1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Search Student");
        System.out.println("4. Average Marks");
        System.out.println("5. Exit");
        System.out.print("Enter Choice : ");
    }

    private int findStudentIndex(int id) {
        for (int i = 0; i < studentCount; i++) {
            int studentId = students[i].getStudentId();
            if (studentId == id) {
                return i;
            }
        }
        return -1;
    }

    public void addStudent() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Full");
            return;
        }

        try {
            System.out.print("Student ID : ");
            int studentId = Integer.parseInt(scanner.nextLine());
            if(studentId < 0) {
                System.out.println("ID has to be a positive number");
                return;
            }
            if(studentCount != 0) {
                if(findStudentIndex(studentId) != -1) {
                    System.out.println("ID already exists");
                    return;
                }
            }
            System.out.print("Name : ");
            String name = scanner.nextLine();
            if(name.isEmpty()) {
                System.out.println("Name cannot be an empty string");
                return;
            }
            System.out.print("Course : ");
            String course = scanner.nextLine();
            if(course.isEmpty()) {
                System.out.println("Course cannot be an empty string");
                return;
            }
            System.out.print("Marks : ");
            double marks = Double.parseDouble(scanner.nextLine());
            if(marks < 0 || marks > 100) {
                System.out.println("Marks cannot be less than 0 or greater than 100");
                return;
            }

            students[studentCount] = new Student(studentId, name, course, marks);
            studentCount++;
            System.out.println("Student Added Successfully");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input. Please try again.");
        }
    }

    public void displayStudents() {
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-8s %-20s %-15s %-8s%n", "ID", "Name", "Course", "Marks");
        System.out.println("----------------------------------------------------------");
        for(int i = 0; i < studentCount; i++) {
            System.out.printf("%-8d %-20s %-15s %-8.2f%n", students[i].getStudentId(), students[i].getName(), students[i].getCourse(), students[i].getMarks());
            System.out.println("----------------------------------------------------------");
        }
    }

    public void searchStudent() {
        if(studentCount == 0) {
            System.out.println("No students to search.");
        }
        try {
            System.out.print("Student ID : ");
            int studentId = Integer.parseInt(scanner.nextLine());
            if(studentId < 0) {
                System.out.println("ID has to be a positive number");
                return;
            }

            int index = findStudentIndex(studentId);
            if(index == -1) {
                System.out.println("Student Not Found.");
            } else {
                students[index].display();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input. Please try again.");
        }
    }

    public void calculateAverage() {
        if(studentCount == 0) {
            System.out.println("No students available");
            return;
        }

        double sum = 0;
        for(int i = 0; i < studentCount; i++) {
            sum += students[i].getMarks();
        }

        System.out.println("Average Marks : " + (sum / studentCount));
    }
}