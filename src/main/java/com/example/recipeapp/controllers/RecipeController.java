package com.example.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recipeapp.models.Recipe;
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

	@RequestMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());

		return "recipe/recipeform";
	}

	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute Recipe newRecipe) {
		Recipe savedRecipe = recipeService.save(newRecipe);
		return "redirect:/recipe/show/" + savedRecipe.getId();
	}
	
    @RequestMapping("recipe/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return  "recipe/recipeform";
    }
}
