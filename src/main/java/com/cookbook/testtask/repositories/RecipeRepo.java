package com.cookbook.testtask.repositories;

import com.cookbook.testtask.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepo extends CrudRepository<Recipe, Long> {
}