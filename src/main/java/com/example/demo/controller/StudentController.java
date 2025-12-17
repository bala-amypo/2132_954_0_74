

package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/students") // ✅ base path
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public Student postStudent(@RequestBody Student st) {
        return studentService.insertStudent(st);
    }

    // READ ALL
    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Optional<Student> getById(@PathVariable Long id) {
        return studentService.getOneStudent(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody Student st) {
        Optional<Student> studentOpt = studentService.getOneStudent(id);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setName(st.getName());
            student.setEmail(st.getEmail());
            student.setCgpa(st.getCgpa());
            student.setDob(st.getDob());

            studentService.insertStudent(student);
            return "Updated Successfully ✅";
        }
        return "Student Not Found ❌";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.getOneStudent(id);

        if (student.isPresent()) {
            studentService.deleteStudent(id);
            return "Deleted Successfully ✅";
        }
        return "Student Not Found ❌";
    }
}