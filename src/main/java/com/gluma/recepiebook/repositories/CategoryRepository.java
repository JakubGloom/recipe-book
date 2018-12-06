package com.gluma.recepiebook.repositories;

import com.gluma.recepiebook.domain.Category;
import com.gluma.recepiebook.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
