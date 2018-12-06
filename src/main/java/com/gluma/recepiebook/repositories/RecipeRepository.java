package com.gluma.recepiebook.repositories;

import com.gluma.recepiebook.domain.Recipe;
import com.gluma.recepiebook.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {

}
