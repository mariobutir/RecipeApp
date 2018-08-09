package com.example.recipeapp.services;

import com.example.recipeapp.models.Ingredient;

public interface IngredientService {

	Ingredient findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
