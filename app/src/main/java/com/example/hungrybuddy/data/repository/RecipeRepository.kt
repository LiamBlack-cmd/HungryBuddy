package com.example.hungrybuddy.data.repository

import com.example.hungrybuddy.data.remote.HungryBuddyApi
import com.example.hungrybuddy.model.Recipe

class RecipeRepository(
    private val api: HungryBuddyApi
) {

    suspend fun searchRecipes(query: String): List<Recipe> {
        return api.searchMeals(query).meals ?: emptyList()
    }

    suspend fun getRecipeDetails(mealId: String): Recipe? {
        return api.getMealDetails(mealId).meals?.firstOrNull()
    }

    suspend fun getRecipesByCategory(category: String): List<Recipe> {
        return api.getMealsByCategory(category).meals ?: emptyList()
    }
}