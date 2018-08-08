package com.example.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipeapp.services.RecipeServiceImpl;

@Controller
public class RecipeController {

	private final RecipeServiceImpl recipeService;

	public RecipeController(RecipeServiceImpl recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/show/{id}")
	public String showById(@PathVariable String id, Model model) {

		model.addAttribute("recipes", recipeService.findById(new Long(id)));
		return "/recipe/show";
	}

}
