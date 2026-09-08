package com.example.hungrybuddy.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.hungrybuddy.model.Recipe

@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit = {}
) {

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column {

            AsyncImage(
                model = recipe.strMealThumb,
                contentDescription = recipe.strMeal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onError = {
                    println("IMAGE ERROR: ${it.result.throwable}")
                }
            )

            Text(
                text = recipe.strMeal ?: "Unknown Recipe",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 4.dp
                )
            )

            Text(
                text = "Category: ${recipe.strCategory ?: "Unknown"}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 4.dp
                )
            )

            Text(
                text = "Cuisine: ${recipe.strArea ?: "Unknown"}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 4.dp,
                    bottom = 12.dp
                )
            )
        }
    }
}