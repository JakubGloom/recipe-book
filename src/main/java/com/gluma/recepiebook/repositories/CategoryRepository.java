package com.gluma.recepiebook.repositories;

import com.gluma.recepiebook.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Recipe, Long> {
}
