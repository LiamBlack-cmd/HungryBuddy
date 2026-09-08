package com.example.hungrybuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hungrybuddy.ui.components.RecipeCard
import com.example.hungrybuddy.viewmodel.RecipeViewModel

@Composable
fun RecipeSearchScreen(
    onRecipeClick: (String) -> Unit,
    recipeViewModel: RecipeViewModel = viewModel()
) {

    var searchText by remember {
        mutableStateOf("")
    }

    val recipes by recipeViewModel.recipes.collectAsState()
    val isLoading by recipeViewModel.isLoading.collectAsState()
    val errorMessage by recipeViewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "HungryBuddy",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Search recipes")
            },
            singleLine = true
        )

        Button(
            onClick = {
                recipeViewModel.searchRecipes(searchText)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        if (isLoading) {

            CircularProgressIndicator()
        }

        errorMessage?.let { message ->

            Text(
                text = message,
                color = MaterialTheme.colorScheme.error
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(recipes) { recipe ->

                RecipeCard(
                    recipe = recipe,
                    onClick = {

                        recipe.idMeal?.let { mealId ->

                            onRecipeClick(mealId)
                        }
                    }
                )
            }
        }
    }
}