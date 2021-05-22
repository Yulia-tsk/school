package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;
@Repository
public class StudentAccessService {

    private final JdbcTemplate jdbcTemplate;
    private final ResultSet resultSet;

    @Autowired
    public StudentAccessService(JdbcTemplate jdbcTemplate, ResultSet resultSet) {
        this.jdbcTemplate = jdbcTemplate;
        this.resultSet = resultSet;
    }

     List<Student> selectAllStudents(){
        String sql =
                "SELECT " +
                " student_id, " +
                " first_name, " +
                " last_name, " +
                " email, " +
                " gender  " +
                "FROM student;";
         return  jdbcTemplate.query(sql, mapStudentFromDb());
    }

    private RowMapper<Student> mapStudentFromDb() {

        return (resultSet, i) -> {
            String studentIdStr = resultSet.getString("student_id");
            UUID studentId = UUID.fromString(studentIdStr);
            String firstName = resultSet.getString(("first_name"));
            String lastName = resultSet.getString(("last_name"));
            String email = resultSet.getString(("email"));
            String genderStr = resultSet.getString(("gender").toUpperCase());
            Student.Gender gender = Student.Gender.valueOf(genderStr);

            return new Student(
                    studentId, firstName, lastName, email, gender
            );
        };
    }

    public int insertStudent(UUID studentId, Student student) {
        String sql =  "" +
         "INSERT INTO student (" + " student_id, " + " first_name, " +" last_name, " +" email, " +" gender) " +
                "VALUES (?, ?, ?, ?, ?);" ;
       return jdbcTemplate.update(
                sql,
                studentId,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender().name().toUpperCase()

        );
    }
@SuppressWarnings("ConstantConditions")
    boolean isEmailTaken(String email) {
        String sql = "" +
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM student " +
                " WHERE email = ? " +
        ");";
        return jdbcTemplate.queryForObject(
                sql, new Object[] {email}, (resultSet, i) ->resultSet.getBoolean(1)
        );

    }
}