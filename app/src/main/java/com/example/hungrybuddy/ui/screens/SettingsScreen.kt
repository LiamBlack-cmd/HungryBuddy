package com.example.hungrybuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Switch
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SettingsScreen(
    userEmail: String?,
    onLogoutClick: () -> Unit
) {
    var darkMode by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "HungryBuddy Settings",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Account",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = userEmail ?: "No email available",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Language",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "English",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Dark Mode",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )

            Switch(
                checked = darkMode,
                onCheckedChange = {
                    darkMode = it
                }
            )
        }

        Text(
            text = "More settings will be added here.",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = onLogoutClick
        ) {
            Text("Logout")
        }
    }
}