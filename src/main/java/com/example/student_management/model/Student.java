package com.example.student_management.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Student {
    // Static counter to generate unique IDs for each student
    private static int counter = 1;
    private Integer id;

    // The name of the student, must not be null and should have a size between 2 and 40 characters
    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    // Email of the student, must not be null and should follow the correct email format
    @NotNull
    @Email
    private String email;

    // Age of the student, must be at least 18
    @NotNull
    @Min(18)
    private Integer age;

    // Constructor to initialize the student object and automatically set the ID using the counter
    public Student() {
        this.id = counter++; // Auto-increments the counter for each new student
    }

    // Getters and setters for all the fields
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}
