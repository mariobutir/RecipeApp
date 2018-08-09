package com.example.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipeapp.services.IngredientServiceImpl;
import com.example.recipeapp.services.RecipeServiceImpl;

@Controller
public class IngredientController {

	private final IngredientServiceImpl ingredientService;
	private final RecipeServiceImpl recipeService;

	public IngredientController(IngredientServiceImpl ingredientService, RecipeServiceImpl recipeService) {
		this.ingredientService = ingredientService;
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/{id}/ingredients")
	public String showIngredients(@PathVariable Long id, Model model) {

		model.addAttribute("recipe", recipeService.findById(id));
		return "/recipe/ingredients/list";
	}

	@RequestMapping("recipe/{recipeId}/ingredients/{id}/show")
	public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
		return "recipe/ingredients/show";
	}

}
