package com.example.hungrybuddy.utils

// This object contains validation functions that can be tested
// without depending on Android framework classes.
object ValidationUtils {

    // A simple email pattern that works in normal Kotlin/JVM unit tests.
    private val emailPattern =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    // Checks whether the email is not empty and follows a valid format.
    fun isValidEmail(email: String): Boolean {

        return email.isNotBlank() &&
                emailPattern.matches(email.trim())
    }

    // Checks whether the password contains at least 6 characters.
    fun isValidPassword(password: String): Boolean {

        return password.length >= 6
    }

    // Checks whether the password and confirmation password are identical.
    fun doPasswordsMatch(
        password: String,
        confirmPassword: String
    ): Boolean {

        return password == confirmPassword
    }
}