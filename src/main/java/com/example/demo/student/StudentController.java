package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        //throw new ApiRequestException("Oops... no students found with custom exception");


        return studentService.getAllStudents();
    }

    @PostMapping
    public void addNewStudent(@RequestBody @Valid Student student){
        studentService.addNewStudent(student);
    }

    @GetMapping(path = "{studentId}/courses")
    public List<StudentCourse> getAllCoursesForStudent(@PathVariable("studentId") UUID studentId){
        System.out.println(studentId);
        return studentService.getAllStudentCourses(studentId);
    }
}
