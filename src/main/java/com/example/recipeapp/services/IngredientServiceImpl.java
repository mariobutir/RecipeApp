package com.example.recipeapp.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import com.example.recipeapp.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public Ingredient findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent()) {
			log.error("recipe id not found. Id: " + recipeId);
		}

		Recipe recipe = recipeOptional.get();

		Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();

		if (!ingredientOptional.isPresent()) {
			log.error("Ingredient id not found: " + ingredientId);
		}

		return ingredientOptional.get();
	}

	@Override
	@Transactional
	public Ingredient saveIngredient(Ingredient command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipe().getId());

		if (!recipeOptional.isPresent()) {

			// todo toss error if not found!
			log.error("Recipe not found for id: " + command.getRecipe().getId());
			return new Ingredient();
		} else {
			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

			if (ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
						.orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); // todo address this
			} else {
				// add new Ingredient
				recipe.addIngredient(command);
			}

			Recipe savedRecipe = recipeRepository.save(recipe);

			// to do check for fail
			return savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId())).findFirst().get();
		}

	}
}
