package com.example.student_management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.student_management.model.Student;

@Service // Marks this class as a service to be managed by Spring
public class StudentService {
    private List<Student> students = new ArrayList<>(); // In-memory list of students

    // Returns the list of all students
    public List<Student> getAllStudents() {
        return students;
    }

    // Adds a new student to the list
    public void addStudent(Student student) {
        students.add(student);
    }

    // Deletes a student by ID
    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id); // Removes student by matching ID
    }
}
