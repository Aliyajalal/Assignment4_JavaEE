package com.example.student_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;

public class StudentServiceTest {
    // Test for adding a student and ensuring the list size increases
    @Test
    void testAddStudentIncreasesSize() {
        StudentService studentService = new StudentService(); // Create a new service instance
        int initialSize = studentService.getAllStudents().size(); // Get the initial size
        studentService.addStudent(new Student()); // Add a new student
        assertEquals(initialSize + 1, studentService.getAllStudents().size()); // Assert that the size has increased by 1
    }
}
