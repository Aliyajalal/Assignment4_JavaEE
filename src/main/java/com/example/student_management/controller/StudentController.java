package com.example.student_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students") // Handles requests to the "/students" URL
public class StudentController {

    @Autowired
    private StudentService studentService; // Inject the student service to handle student data

    // Displays the list of all students
    @GetMapping
    public String viewStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; // Returns the "students" view
    }

    // Shows the form to add a new student
    @GetMapping("/new")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new Student()); // Passes an empty Student object to the form
        return "new-student"; // Returns the "new-student" view to the user
    }

    // Handles the submission of the new student form
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {
        if (result.hasErrors()) { // Checks if there are validation errors
            return "new-student"; // Returns to the form if there are validation errors
        }
        studentService.addStudent(student); // Adds the student if no errors
        return "redirect:/students"; // Redirects to the list of students
    }

    // Handles the deletion of a student
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id); // Deletes the student by ID
        return "redirect:/students"; // Redirects back to the list of students
    }

    // Provides student data as a JSON response
    @GetMapping("/json")
    @ResponseBody
    public List<Student> getStudentsJson() {
        return studentService.getAllStudents(); // Returns the list of students in JSON format
    }
}
