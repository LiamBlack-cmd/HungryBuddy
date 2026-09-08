package com.example.hungrybuddy.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AuthCheckScreen(
    onLoggedIn: () -> Unit,
    onNotLoggedIn: () -> Unit
) {

    LaunchedEffect(Unit) {

        val currentUser =
            FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            onLoggedIn()
        } else {
            onNotLoggedIn()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator()
    }
}