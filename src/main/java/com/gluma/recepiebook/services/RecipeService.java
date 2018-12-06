package com.gluma.recepiebook.services;

import com.gluma.recepiebook.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
