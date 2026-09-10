package com.example.hungrybuddy.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hungrybuddy.ui.screens.HomeScreen
import com.example.hungrybuddy.ui.screens.RecipeDetailsScreen
import com.example.hungrybuddy.ui.screens.RecipeSearchScreen
import com.example.hungrybuddy.ui.screens.SettingsScreen
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import com.example.hungrybuddy.ui.screens.RegisterScreen
import com.example.hungrybuddy.ui.screens.LoginScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.hungrybuddy.ui.screens.AuthCheckScreen

object Routes {
    const val HOME = "home"
    const val SEARCH = "search"
    const val SETTINGS = "settings"
    const val DETAILS = "details"
    const val REGISTER = "register"
    const val LOGIN = "login"
    const val AUTH_CHECK = "auth_check"
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun HungryBuddyNavGraph() {



    val navController = rememberNavController()

    val firebaseAuth = FirebaseAuth.getInstance()

    var registerError by remember {
        mutableStateOf<String?>(null)
    }

    var isRegistering by remember {
        mutableStateOf(false)
    }
    var loginError by remember {
        mutableStateOf<String?>(null)
    }

    var isLoggingIn by remember {
        mutableStateOf(false)
    }

    val bottomNavItems = listOf(

        BottomNavItem(
            route = Routes.HOME,
            label = "Home",
            icon = Icons.Default.Home
        ),

        BottomNavItem(
            route = Routes.SEARCH,
            label = "Search",
            icon = Icons.Default.Search
        ),

        BottomNavItem(
            route = Routes.SETTINGS,
            label = "Settings",
            icon = Icons.Default.Settings
        )
    )

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route

    val showBottomNavigation =
        currentRoute == Routes.HOME ||
                currentRoute == Routes.SEARCH ||
                currentRoute == Routes.SETTINGS

    Scaffold(

        bottomBar = {

            if (showBottomNavigation) {

                NavigationBar {

                    bottomNavItems.forEach { item ->

                        NavigationBarItem(

                            selected = currentRoute == item.route,

                            onClick = {

                                navController.navigate(item.route) {

                                    popUpTo(Routes.HOME) {
                                        saveState = true
                                    }

                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },

                            icon = {

                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },

                            label = {
                                Text(item.label)
                            }
                        )
                    }
                }
            }
        }

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.AUTH_CHECK,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = Routes.AUTH_CHECK
            ) {

                AuthCheckScreen(

                    onLoggedIn = {

                        navController.navigate(Routes.HOME) {

                            popUpTo(Routes.AUTH_CHECK) {
                                inclusive = true
                            }
                        }
                    },

                    onNotLoggedIn = {

                        navController.navigate(Routes.LOGIN) {

                            popUpTo(Routes.AUTH_CHECK) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Routes.HOME
            ) {

                HomeScreen()
            }

            composable(
                route = Routes.SEARCH
            ) {

                RecipeSearchScreen(

                    onRecipeClick = { recipeId ->

                        navController.navigate(
                            "${Routes.DETAILS}/$recipeId"
                        )
                    }
                )
            }

            composable(
                route = Routes.SETTINGS
            ) {

                SettingsScreen(
                    userEmail = firebaseAuth.currentUser?.email,

                    onLogoutClick = {

                        firebaseAuth.signOut()

                        navController.navigate(Routes.LOGIN) {

                            popUpTo(Routes.HOME) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = "${Routes.DETAILS}/{recipeId}",

                arguments = listOf(

                    navArgument("recipeId") {

                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->

                val recipeId =
                    backStackEntry.arguments?.getString("recipeId")

                if (recipeId != null) {

                    RecipeDetailsScreen(

                        recipeId = recipeId,

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }
            }
            composable(
                route = Routes.REGISTER
            ) {

                RegisterScreen(

                    errorMessage = registerError,

                    isLoading = isRegistering,

                    onRegisterClick = { email, password ->

                        registerError = null
                        isRegistering = true

                        firebaseAuth
                            .createUserWithEmailAndPassword(
                                email,
                                password
                            )
                            .addOnCompleteListener { task ->

                                isRegistering = false

                                if (task.isSuccessful) {

                                    navController.navigate(Routes.HOME) {

                                        popUpTo(Routes.REGISTER) {
                                            inclusive = true
                                        }
                                    }

                                } else {

                                    registerError = when (task.exception) {

                                        is FirebaseAuthUserCollisionException -> {
                                            "An account with this email already exists."
                                        }

                                        is FirebaseAuthWeakPasswordException -> {
                                            "Your password is too weak. Please choose a stronger password."
                                        }

                                        is FirebaseAuthInvalidCredentialsException -> {
                                            "Please enter a valid email address."
                                        }



                                        else -> {
                                            "Registration failed. Please try again."
                                        }
                                    }
                                }
                            }
                    },
                    //login screen
                    onLoginClick = {

                        navController.navigate(Routes.LOGIN)
                    }
                )
            }
            composable(
                route = Routes.LOGIN
            ) {

                LoginScreen(

                    errorMessage = loginError,

                    isLoading = isLoggingIn,

                    onLoginClick = { email, password ->

                        loginError = null
                        isLoggingIn = true

                        firebaseAuth
                            .signInWithEmailAndPassword(
                                email,
                                password
                            )
                            .addOnCompleteListener { task ->

                                isLoggingIn = false

                                if (task.isSuccessful) {

                                    navController.navigate(Routes.HOME) {

                                        popUpTo(Routes.LOGIN) {
                                            inclusive = true
                                        }
                                    }

                                } else {

                                    loginError = when (task.exception) {

                                        is FirebaseAuthInvalidUserException,
                                        is FirebaseAuthInvalidCredentialsException -> {
                                            "Incorrect email or password."
                                        }



                                        else -> {
                                            "Login failed. Please check your details and try again."
                                        }
                                    }
                                }
                            }
                    },

                    onRegisterClick = {

                        navController.navigate(Routes.REGISTER)
                    }
                )
            }
        }
    }
}