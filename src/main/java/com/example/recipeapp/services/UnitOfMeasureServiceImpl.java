package com.example.recipeapp.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.recipeapp.models.UnitOfMeasure;
import com.example.recipeapp.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public Set<UnitOfMeasure> listAllUoms() {

		return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false).collect(Collectors.toSet());
	}
}
