package com.example.hungrybuddy.ui.screens

// Compose and Android imports.
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
// Material 3 UI components.
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
// Compose state imports.
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
// UI utilities.
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
// Imports our reusable validation functions.
import com.example.hungrybuddy.utils.ValidationUtils


@Composable
fun RegisterScreen(
    onRegisterClick: (String, String) -> Unit,
    onLoginClick: () -> Unit,
    errorMessage: String? = null,
    isLoading: Boolean = false
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var validationError by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Join HungryBuddy",
            style = MaterialTheme.typography.bodyLarge
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                validationError = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Email")
            },
            singleLine = true
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                validationError = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                validationError = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Confirm Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        validationError?.let { message ->

            Text(
                text = message,
                color = MaterialTheme.colorScheme.error
            )
        }

        errorMessage?.let { message ->

            Text(
                text = message,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {

                when {
                    // Check whether the user entered an email address.
                    email.isBlank() -> {
                        validationError =
                            "Please enter your email."
                    }

                    // Uses the same email validation function that our unit tests verify.
                    !ValidationUtils.isValidEmail(email) -> {
                        validationError =
                            "Please enter a valid email address."
                    }
                    // Check that the password was entered.
                    password.isBlank() -> {
                        validationError =
                            "Please enter a password."
                    }

                    // Use the reusable and unit-tested password validation.
                    !ValidationUtils.isValidPassword(password) -> {
                        validationError =
                            "Password must be at least 6 characters."
                    }
                    // Check that the confirmation password was entered.
                    confirmPassword.isBlank() -> {
                        validationError =
                            "Please confirm your password."
                    }

                    // Use the reusable and unit-tested password matching function.
                    !ValidationUtils.doPasswordsMatch(
                        password,
                        confirmPassword
                    ) -> {
                        validationError =
                            "Passwords do not match."
                    }

                    else -> {
                        validationError = null

                        onRegisterClick(
                            email.trim(),
                            password
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {

            if (isLoading) {

                CircularProgressIndicator()

            } else {

                Text("Create Account")
            }
        }

        TextButton(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text("Already have an account? Login")
        }
    }
}