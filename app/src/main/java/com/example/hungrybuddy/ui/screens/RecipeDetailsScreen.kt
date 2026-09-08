package com.example.hungrybuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.hungrybuddy.viewmodel.RecipeViewModel
import androidx.compose.material3.Button

@Composable
fun RecipeDetailsScreen(
    recipeId: String,
    onBackClick: () -> Unit,
    recipeViewModel: RecipeViewModel = viewModel()
) {

    val recipe by recipeViewModel.selectedRecipe.collectAsState()
    val isLoading by recipeViewModel.isLoadingDetails.collectAsState()

    LaunchedEffect(recipeId) {

        recipeViewModel.loadRecipeDetails(recipeId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Button(
            onClick = {
                onBackClick()
            }
        ) {
            Text("Back")
        }

        if (isLoading) {

            CircularProgressIndicator()

        } else if (recipe != null) {

            val currentRecipe = recipe!!

            val ingredients = listOf(
                currentRecipe.strIngredient1 to currentRecipe.strMeasure1,
                currentRecipe.strIngredient2 to currentRecipe.strMeasure2,
                currentRecipe.strIngredient3 to currentRecipe.strMeasure3,
                currentRecipe.strIngredient4 to currentRecipe.strMeasure4,
                currentRecipe.strIngredient5 to currentRecipe.strMeasure5,
                currentRecipe.strIngredient6 to currentRecipe.strMeasure6,
                currentRecipe.strIngredient7 to currentRecipe.strMeasure7,
                currentRecipe.strIngredient8 to currentRecipe.strMeasure8,
                currentRecipe.strIngredient9 to currentRecipe.strMeasure9,
                currentRecipe.strIngredient10 to currentRecipe.strMeasure10,
                currentRecipe.strIngredient11 to currentRecipe.strMeasure11,
                currentRecipe.strIngredient12 to currentRecipe.strMeasure12,
                currentRecipe.strIngredient13 to currentRecipe.strMeasure13,
                currentRecipe.strIngredient14 to currentRecipe.strMeasure14,
                currentRecipe.strIngredient15 to currentRecipe.strMeasure15,
                currentRecipe.strIngredient16 to currentRecipe.strMeasure16,
                currentRecipe.strIngredient17 to currentRecipe.strMeasure17,
                currentRecipe.strIngredient18 to currentRecipe.strMeasure18,
                currentRecipe.strIngredient19 to currentRecipe.strMeasure19,
                currentRecipe.strIngredient20 to currentRecipe.strMeasure20
            )

            AsyncImage(
                model = currentRecipe.strMealThumb,
                contentDescription = currentRecipe.strMeal,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = currentRecipe.strMeal ?: "Unknown Recipe",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Category: ${currentRecipe.strCategory ?: "Unknown"}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Cuisine: ${currentRecipe.strArea ?: "Unknown"}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            ingredients.forEach { (ingredient, measure) ->

                if (!ingredient.isNullOrBlank()) {

                    Row {

                        Text(
                            text = "• $ingredient",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        if (!measure.isNullOrBlank()) {

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Text(
                                text = measure,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }

            Text(
                text = "Instructions",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = currentRecipe.strInstructions
                    ?: "No instructions available.",
                style = MaterialTheme.typography.bodyLarge
            )

        } else {

            Text(
                text = "Unable to load this recipe.",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}