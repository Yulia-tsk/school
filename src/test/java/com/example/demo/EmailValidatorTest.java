package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailValidatorTest {

    private final EmailValidator underTest = new EmailValidator();

    @Test
    public void itShouldValidateCorrectEmail() {
        assertThat(underTest.test("hello@gmail.com")).isTrue();
    }
    @Test
    public void itShouldValidateIncorrectEmail() {
        assertThat(underTest.test("hellogmail.com")).isFalse();
    }

    @Test
    public void itShouldValidateIncorrectEmailWithoutDot() {
        assertThat(underTest.test("hellogmailcom")).isFalse();
    }
}