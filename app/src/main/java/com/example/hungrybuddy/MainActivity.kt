package com.example.hungrybuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hungrybuddy.navigation.HungryBuddyNavGraph
import com.example.hungrybuddy.ui.theme.HungryBuddyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            HungryBuddyTheme {

                HungryBuddyNavGraph()
            }
        }
    }
}