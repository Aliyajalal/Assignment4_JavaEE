package com.example.student_management;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.student_management.controller.StudentController;
import com.example.student_management.service.StudentService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock  //  Replace @MockBean
    private StudentService studentService;

    @Test
    public void testViewStudentsPage() throws Exception {
        when(studentService.getAllStudents()).thenReturn(new ArrayList<>()); //  Mock the service method

        mockMvc.perform(get("/students"))
               .andExpect(status().isOk())
               .andExpect(view().name("students"))
               .andExpect(model().attributeExists("students"));
    }

    @Test
    public void testSaveStudentWithInvalidData() throws Exception {
        mockMvc.perform(post("/students/save")
               .param("name", "")  
               .param("email", "invalid-email")
               .param("age", "16"))
               .andExpect(status().isOk())  
               .andExpect(view().name("new-student"))
               .andExpect(model().attributeHasFieldErrors("student", "name", "email", "age"))
               .andExpect(model().attributeExists("student"));
    }
}
