package com.example.hungrybuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrybuddy.data.remote.RetrofitInstance
import com.example.hungrybuddy.data.repository.RecipeRepository
import com.example.hungrybuddy.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val repository = RecipeRepository(
        RetrofitInstance.api
    )

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe.asStateFlow()

    private val _isLoadingDetails = MutableStateFlow(false)
    val isLoadingDetails: StateFlow<Boolean> = _isLoadingDetails.asStateFlow()

    fun searchRecipes(query: String) {

        if (query.isBlank()) {
            return
        }

        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null

            try {

                val result = repository.searchRecipes(query)

                _recipes.value = result

            } catch (exception: Exception) {

                _errorMessage.value =
                    exception.message ?: "Something went wrong."

            } finally {

                _isLoading.value = false
            }
        }
    }

    fun loadRecipeDetails(mealId: String) {

        viewModelScope.launch {

            _isLoadingDetails.value = true
            _selectedRecipe.value = null

            try {

                val recipe = repository.getRecipeDetails(mealId)

                _selectedRecipe.value = recipe

            } catch (exception: Exception) {

                _errorMessage.value =
                    exception.message ?: "Unable to load recipe."

            } finally {

                _isLoadingDetails.value = false
            }
        }
    }

    fun clearSelectedRecipe() {

        _selectedRecipe.value = null
    }
}