package com.example.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.services.IngredientServiceImpl;
import com.example.recipeapp.services.RecipeServiceImpl;
import com.example.recipeapp.services.UnitOfMeasureServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

	private final IngredientServiceImpl ingredientService;
	private final RecipeServiceImpl recipeService;
	private final UnitOfMeasureServiceImpl unitOfMeasureService;

	public IngredientController(IngredientServiceImpl ingredientService, RecipeServiceImpl recipeService,
			UnitOfMeasureServiceImpl unitOfMeasureService) {
		this.ingredientService = ingredientService;
		this.recipeService = recipeService;
		this.unitOfMeasureService = unitOfMeasureService;
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

	@RequestMapping("recipe/{recipeId}/ingredients/{id}/update")
	public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

		model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
		return "recipe/ingredients/ingredientform";
	}

	@PostMapping("recipe/{recipeId}/ingredient")
	public String saveOrUpdate(@ModelAttribute Ingredient command) {
		Ingredient savedIngredient = ingredientService.saveIngredient(command);

		log.debug("saved receipe id:" + savedIngredient.getRecipe().getId());
		log.debug("saved ingredient id:" + savedIngredient.getId());

		return "redirect:/recipe/" + savedIngredient.getRecipe().getId() + "/ingredient/" + savedIngredient.getId() + "/show";
	}
}
