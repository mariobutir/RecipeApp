package com.example.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.recipeapp.models.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByDescription(String description);

}
