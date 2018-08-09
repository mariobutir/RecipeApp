package com.example.recipeapp.services;

import java.util.Set;

import com.example.recipeapp.models.Recipe;

public interface RecipeService {

	public Set<Recipe> getRecipes();
	public Recipe save(Recipe recipe);
	public void deleteById(Long id);
}
