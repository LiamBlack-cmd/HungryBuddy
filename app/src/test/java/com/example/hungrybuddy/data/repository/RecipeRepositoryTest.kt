// Test package for the recipe repository.
package com.example.hungrybuddy.data.repository

// Imports the API interface used by the repository.
import com.example.hungrybuddy.data.remote.HungryBuddyApi

// Imports the API response model.
import com.example.hungrybuddy.data.remote.MealResponse

// Imports the Recipe model.
import com.example.hungrybuddy.model.Recipe

// Imports the JUnit assertion.
import org.junit.Assert.assertEquals

// Imports the JUnit test annotation.
import org.junit.Test

class RecipeRepositoryTest {

    // Fake API used by the first test.
    private class FakeHungryBuddyApi : HungryBuddyApi {

        // Returns one fake recipe for a successful search.
        override suspend fun searchMeals(
            searchQuery: String
        ): MealResponse {

            return MealResponse(
                meals = listOf(
                    Recipe(
                        idMeal = "123",
                        strMeal = "Chicken Curry",
                        strCategory = "Chicken",
                        strArea = "Indian",
                        strInstructions = "Cook the chicken with curry spices.",
                        strMealThumb = null,
                        strYoutube = null,

                        // Empty ingredient fields for this test.
                        strIngredient1 = null,
                        strIngredient2 = null,
                        strIngredient3 = null,
                        strIngredient4 = null,
                        strIngredient5 = null,
                        strIngredient6 = null,
                        strIngredient7 = null,
                        strIngredient8 = null,
                        strIngredient9 = null,
                        strIngredient10 = null,
                        strIngredient11 = null,
                        strIngredient12 = null,
                        strIngredient13 = null,
                        strIngredient14 = null,
                        strIngredient15 = null,
                        strIngredient16 = null,
                        strIngredient17 = null,
                        strIngredient18 = null,
                        strIngredient19 = null,
                        strIngredient20 = null,

                        // Empty measurement fields for this test.
                        strMeasure1 = null,
                        strMeasure2 = null,
                        strMeasure3 = null,
                        strMeasure4 = null,
                        strMeasure5 = null,
                        strMeasure6 = null,
                        strMeasure7 = null,
                        strMeasure8 = null,
                        strMeasure9 = null,
                        strMeasure10 = null,
                        strMeasure11 = null,
                        strMeasure12 = null,
                        strMeasure13 = null,
                        strMeasure14 = null,
                        strMeasure15 = null,
                        strMeasure16 = null,
                        strMeasure17 = null,
                        strMeasure18 = null,
                        strMeasure19 = null,
                        strMeasure20 = null
                    )
                )
            )
        }

        // Required API function; not used by this test.
        override suspend fun getMealDetails(
            mealId: String
        ): MealResponse {

            return MealResponse(
                meals = emptyList()
            )
        }

        // Required API function; not used by this test.
        override suspend fun getMealsByCategory(
            category: String
        ): MealResponse {

            return MealResponse(
                meals = emptyList()
            )
        }
    }

    // Tests that a successful API response is returned by the repository.
    @Test
    fun searchRecipes_returnsRecipes() {

        // Creates the repository with the fake API.
        val repository = RecipeRepository(
            FakeHungryBuddyApi()
        )

        // Runs the suspend repository function in the test.
        val result = kotlinx.coroutines.runBlocking {
            repository.searchRecipes("chicken")
        }

        // Confirms that one recipe was returned.
        assertEquals(1, result.size)

        // Confirms that the recipe name is correct.
        assertEquals(
            "Chicken Curry",
            result.first().strMeal
        )
    }

    // Tests that a null API result becomes an empty repository list.
    @Test
    fun searchRecipes_withNoResults_returnsEmptyList() {

        // Creates a fake API specifically for an empty-result response.
        val fakeApi = object : HungryBuddyApi {

            // Simulates an API search that found no recipes.
            override suspend fun searchMeals(
                searchQuery: String
            ): MealResponse {

                return MealResponse(
                    meals = null
                )
            }

            // Required API function; not used by this test.
            override suspend fun getMealDetails(
                mealId: String
            ): MealResponse {

                return MealResponse(
                    meals = emptyList()
                )
            }

            // Required API function; not used by this test.
            override suspend fun getMealsByCategory(
                category: String
            ): MealResponse {

                return MealResponse(
                    meals = emptyList()
                )
            }
        }

        // Creates the repository using the empty-result fake API.
        val repository = RecipeRepository(fakeApi)

        // Performs the search without contacting the real API.
        val result = kotlinx.coroutines.runBlocking {
            repository.searchRecipes(
                "something-that-does-not-exist"
            )
        }

        // Confirms that the repository returns an empty list.
        assertEquals(0, result.size)
    }
    // Tests that the repository correctly returns the details
// of a recipe when the API provides a matching recipe.
    @Test
    fun getRecipeDetails_returnsRecipe() {

        // Creates a fake API that returns one recipe for the requested ID.
        val fakeApi = object : HungryBuddyApi {

            // This function is not needed for this particular test.
            override suspend fun searchMeals(
                searchQuery: String
            ): MealResponse {

                return MealResponse(
                    meals = emptyList()
                )
            }

            // Simulates the API returning recipe details.
            override suspend fun getMealDetails(
                mealId: String
            ): MealResponse {

                return MealResponse(
                    meals = listOf(
                        Recipe(
                            idMeal = mealId,
                            strMeal = "Chicken Curry",
                            strCategory = "Chicken",
                            strArea = "Indian",
                            strInstructions = "Cook the chicken with curry spices.",
                            strMealThumb = null,
                            strYoutube = null,

                            // Empty ingredient fields for this test.
                            strIngredient1 = null,
                            strIngredient2 = null,
                            strIngredient3 = null,
                            strIngredient4 = null,
                            strIngredient5 = null,
                            strIngredient6 = null,
                            strIngredient7 = null,
                            strIngredient8 = null,
                            strIngredient9 = null,
                            strIngredient10 = null,
                            strIngredient11 = null,
                            strIngredient12 = null,
                            strIngredient13 = null,
                            strIngredient14 = null,
                            strIngredient15 = null,
                            strIngredient16 = null,
                            strIngredient17 = null,
                            strIngredient18 = null,
                            strIngredient19 = null,
                            strIngredient20 = null,

                            // Empty measurement fields for this test.
                            strMeasure1 = null,
                            strMeasure2 = null,
                            strMeasure3 = null,
                            strMeasure4 = null,
                            strMeasure5 = null,
                            strMeasure6 = null,
                            strMeasure7 = null,
                            strMeasure8 = null,
                            strMeasure9 = null,
                            strMeasure10 = null,
                            strMeasure11 = null,
                            strMeasure12 = null,
                            strMeasure13 = null,
                            strMeasure14 = null,
                            strMeasure15 = null,
                            strMeasure16 = null,
                            strMeasure17 = null,
                            strMeasure18 = null,
                            strMeasure19 = null,
                            strMeasure20 = null
                        )
                    )
                )
            }

            // This function is not needed for this test.
            override suspend fun getMealsByCategory(
                category: String
            ): MealResponse {

                return MealResponse(
                    meals = emptyList()
                )
            }
        }

        // Creates the repository using our fake API.
        val repository = RecipeRepository(fakeApi)

        // Requests the details for recipe ID 123.
        val result = kotlinx.coroutines.runBlocking {
            repository.getRecipeDetails("123")
        }

        // Confirms that a recipe was returned.
        assertEquals(
            "Chicken Curry",
            result?.strMeal
        )

        // Confirms that the returned recipe has the requested ID.
        assertEquals(
            "123",
            result?.idMeal
        )
    }
    // Tests that the repository returns null when no recipe details are found.
    @Test
    fun getRecipeDetails_withNoResults_returnsNull() {

        // Creates a fake API that returns no recipe details.
        val fakeApi = object : HungryBuddyApi {

            // This function is not needed for this test.
            override suspend fun searchMeals(
                searchQuery: String
            ): MealResponse {

                // Returns an empty response.
                return MealResponse(
                    meals = emptyList()
                )
            }

            // Simulates the API finding no recipe for the requested ID.
            override suspend fun getMealDetails(
                mealId: String
            ): MealResponse {

                // Returns no meals.
                return MealResponse(
                    meals = emptyList()
                )
            }

            // This function is not needed for this test.
            override suspend fun getMealsByCategory(
                category: String
            ): MealResponse {

                // Returns an empty response.
                return MealResponse(
                    meals = emptyList()
                )
            }
        }

        // Creates the repository using the fake API.
        val repository = RecipeRepository(fakeApi)

        // Requests details for a recipe that does not exist.
        val result = kotlinx.coroutines.runBlocking {
            repository.getRecipeDetails("999999")
        }

        // Confirms that no recipe was returned.
        assertEquals(
            null,
            result
        )
    }
    // Tests that the repository correctly returns recipes for a category.
    @Test
    fun getRecipesByCategory_returnsRecipes() {

        // Creates a fake API that returns one recipe for the category.
        val fakeApi = object : HungryBuddyApi {

            // This function is not needed for this test.
            override suspend fun searchMeals(
                searchQuery: String
            ): MealResponse {

                // Returns an empty response.
                return MealResponse(
                    meals = emptyList()
                )
            }

            // This function is not needed for this test.
            override suspend fun getMealDetails(
                mealId: String
            ): MealResponse {

                // Returns an empty response.
                return MealResponse(
                    meals = emptyList()
                )
            }

            // Simulates the API returning one recipe for a category.
            override suspend fun getMealsByCategory(
                category: String
            ): MealResponse {

                // Returns one fake recipe.
                return MealResponse(
                    meals = listOf(
                        Recipe(
                            idMeal = "456",
                            strMeal = "Beef Stew",
                            strCategory = "Beef",
                            strArea = "British",
                            strInstructions = "Cook the beef with vegetables.",
                            strMealThumb = null,
                            strYoutube = null,

                            // Empty ingredient fields for this test.
                            strIngredient1 = null,
                            strIngredient2 = null,
                            strIngredient3 = null,
                            strIngredient4 = null,
                            strIngredient5 = null,
                            strIngredient6 = null,
                            strIngredient7 = null,
                            strIngredient8 = null,
                            strIngredient9 = null,
                            strIngredient10 = null,
                            strIngredient11 = null,
                            strIngredient12 = null,
                            strIngredient13 = null,
                            strIngredient14 = null,
                            strIngredient15 = null,
                            strIngredient16 = null,
                            strIngredient17 = null,
                            strIngredient18 = null,
                            strIngredient19 = null,
                            strIngredient20 = null,

                            // Empty measurement fields for this test.
                            strMeasure1 = null,
                            strMeasure2 = null,
                            strMeasure3 = null,
                            strMeasure4 = null,
                            strMeasure5 = null,
                            strMeasure6 = null,
                            strMeasure7 = null,
                            strMeasure8 = null,
                            strMeasure9 = null,
                            strMeasure10 = null,
                            strMeasure11 = null,
                            strMeasure12 = null,
                            strMeasure13 = null,
                            strMeasure14 = null,
                            strMeasure15 = null,
                            strMeasure16 = null,
                            strMeasure17 = null,
                            strMeasure18 = null,
                            strMeasure19 = null,
                            strMeasure20 = null
                        )
                    )
                )
            }
        }

        // Creates the repository using the fake API.
        val repository = RecipeRepository(fakeApi)

        // Requests recipes from the Beef category.
        val result = kotlinx.coroutines.runBlocking {
            repository.getRecipesByCategory("Beef")
        }

        // Confirms that exactly one recipe was returned.
        assertEquals(
            1,
            result.size
        )

        // Confirms that the expected recipe was returned.
        assertEquals(
            "Beef Stew",
            result.first().strMeal
        )

        // Confirms that the recipe belongs to the expected category.
        assertEquals(
            "Beef",
            result.first().strCategory
        )
    }
    // Tests that the repository returns an empty list when
// no recipes are found for a category.
    @Test
    fun getRecipesByCategory_withNoResults_returnsEmptyList() {

        // Creates a fake API that returns no category results.
        val fakeApi = object : HungryBuddyApi {

            // This function is not needed for this test.
            override suspend fun searchMeals(
                searchQuery: String
            ): MealResponse {

                // Returns an empty response.
                return MealResponse(
                    meals = emptyList()
                )
            }

            // This function is not needed for this test.
            override suspend fun getMealDetails(
                mealId: String
            ): MealResponse {

                // Returns an empty response.
                return MealResponse(
                    meals = emptyList()
                )
            }

            // Simulates a category search with no matching recipes.
            override suspend fun getMealsByCategory(
                category: String
            ): MealResponse {

                // Returns null to simulate no results from the API.
                return MealResponse(
                    meals = null
                )
            }
        }

        // Creates the repository using the fake API.
        val repository = RecipeRepository(fakeApi)

        // Requests recipes from a category with no results.
        val result = kotlinx.coroutines.runBlocking {
            repository.getRecipesByCategory("UnknownCategory")
        }

        // Confirms that the repository safely returns an empty list.
        assertEquals(
            0,
            result.size
        )
    }
}