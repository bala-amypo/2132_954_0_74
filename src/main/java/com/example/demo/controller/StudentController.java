package com.example.demo.controller
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
    
}
