package com.example.demo.student;

import com.example.demo.EmailValidator;
import com.example.demo.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    
    private final StudentAccessService studentAccessService;
    private final EmailValidator emailValidator;
    @Autowired
    public StudentService(StudentAccessService studentAccessService, EmailValidator emailValidator) {
        this.studentAccessService = studentAccessService;
        this.emailValidator = emailValidator;
    }


    List<Student> getAllStudents(){
        return studentAccessService.selectAllStudents();
    }

    void addNewStudent(Student student) {
        addNewStudent(null, student);
    }

    void addNewStudent(UUID uuid, Student student) {
        UUID newUuid = Optional.ofNullable(uuid).
                orElse(UUID.randomUUID());
        //TODO: validate email
       if (!emailValidator.test(student.getEmail())){
           throw new ApiRequestException(student.getEmail() + "is not valid");

       }
        //TODO: verify that email is not taken
        if(studentAccessService.isEmailTaken(student.getEmail())){
            throw new ApiRequestException(student.getEmail() + "is taken");
        }
        studentAccessService.insertStudent(newUuid, student);
    }
    List<StudentCourse> getAllStudentCourses(UUID studentId) {
        return studentAccessService.selectAllStudentCourses(studentId);
    }
}
