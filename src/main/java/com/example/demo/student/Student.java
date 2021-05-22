package com.example.demo.student;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Student {

    private final UUID id;
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    private final String email;
    @NotNull
    private final Gender gender;

    public Student(@JsonProperty("uuid") UUID uuid,
                   @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("email") String email,
                   @JsonProperty("gender") Gender gender) {
        this.id = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }

    enum Gender{
        MALE,
        FEMALE
    }
}
