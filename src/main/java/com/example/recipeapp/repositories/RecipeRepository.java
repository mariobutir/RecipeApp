package com.example.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.recipeapp.models.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
