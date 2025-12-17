package com.example.demo.controller
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.


public class StudentController{
    private final StudentService ser;
    @Autowired
    public StudentController(StudentService ser){
        thi.ser=ser;
    }
    @PostMapping("/add")
    public StudentaddStudent(@RequestBody Student st){
        return ser.insertStudent(st);
    }
    @GetMapping("/getAll")
    public List<Student>getAllStudents(){
        return ser.getAllStudents();
    }
    @GetMapping("/get/{id}")
    public Optional<Student> getStudent(@PathVariable Long id){
        return ser.getOneStudent(id);
    }
    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,@RequestBody Student newStudent){
        Optional<Student> student=ser.getOneStudent(id);
        if(student.isPresent()){
            newStudent.setId(id);

            ser.insertStudent(newStudent);
            return "Updated Successfully";
        }
        return "Id not found";
    }
    @DeleteMapping("/del/{id}")
    public String deleteStudent(@PathVariable Long id){
        Optional<Student> student=ser.getOneStudent(id);
        if(student.isPresent()){
            ser.deleteStudent(id);
            return "Deleted Successfully";
        }
        return "Id not found";
    }
}
