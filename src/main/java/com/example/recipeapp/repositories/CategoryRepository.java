package com.example.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.recipeapp.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	Optional<Category> findByDescription(String description);
}
