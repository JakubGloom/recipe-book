package com.gluma.recepiebook.services;

import com.gluma.recepiebook.commands.RecipeCommand;
import com.gluma.recepiebook.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}
