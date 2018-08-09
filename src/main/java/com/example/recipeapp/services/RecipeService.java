package com.example.recipeapp.services;

import java.util.Set;

import com.example.recipeapp.commands.RecipeCommand;
import com.example.recipeapp.models.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe findById(Long l);

	RecipeCommand findCommandById(Long l);

	RecipeCommand saveRecipeCommand(RecipeCommand command);

	void deleteById(Long idToDelete);
}
