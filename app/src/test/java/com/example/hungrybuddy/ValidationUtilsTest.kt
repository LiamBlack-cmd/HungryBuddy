package com.example.hungrybuddy

import com.example.hungrybuddy.utils.ValidationUtils
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationUtilsTest {

    // Test that a correctly formatted email is accepted.
    @Test
    fun validEmail_returnsTrue() {

        val result =
            ValidationUtils.isValidEmail("test@example.com")

        assertTrue(result)
    }

    // Test that an incorrectly formatted email is rejected.
    @Test
    fun invalidEmail_returnsFalse() {

        val result =
            ValidationUtils.isValidEmail("invalid-email")

        assertFalse(result)
    }

    // Test that a password with at least 6 characters is accepted.
    @Test
    fun validPassword_returnsTrue() {

        val result =
            ValidationUtils.isValidPassword("123456")

        assertTrue(result)
    }

    // Test that a password shorter than 6 characters is rejected.
    @Test
    fun shortPassword_returnsFalse() {

        val result =
            ValidationUtils.isValidPassword("12345")

        assertFalse(result)
    }

    // Test that matching passwords return true.
    @Test
    fun matchingPasswords_returnsTrue() {

        val result =
            ValidationUtils.doPasswordsMatch(
                "password123",
                "password123"
            )

        assertTrue(result)
    }

    // Test that different passwords return false.
    @Test
    fun differentPasswords_returnsFalse() {

        val result =
            ValidationUtils.doPasswordsMatch(
                "password123",
                "password456"
            )

        assertFalse(result)
    }
}