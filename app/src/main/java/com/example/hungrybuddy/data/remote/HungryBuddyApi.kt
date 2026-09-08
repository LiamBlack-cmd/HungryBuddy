package com.example.hungrybuddy.data.remote

import com.example.hungrybuddy.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

data class MealResponse(
    val meals: List<Recipe>?
)

interface HungryBuddyApi {

    @GET("search.php")
    suspend fun searchMeals(
        @Query("s") searchQuery: String
    ): MealResponse

    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") mealId: String
    ): MealResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): MealResponse
}