package com.gluma.recepiebook.services;

import com.gluma.recepiebook.commands.RecipeCommand;
import com.gluma.recepiebook.converters.RecipeCommandToRecipe;
import com.gluma.recepiebook.converters.RecipeToRecipeCommand;
import com.gluma.recepiebook.domain.Recipe;
import com.gluma.recepiebook.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }

        return recipeOptional.get();
    }

    //its transactional bc we make convection outside the scope
    /*this method goes to the database makes findbyid and converts to the command object
    returns back to the view layer for display on the recipe form
    form. And then the recipe form, we're reusing that same exact form and here on
    line 24, it does get the object and populates the form fields with the
    recipe object pass back from the controller. And then this Thymeleaf th
    action tells Thymeleaf to generate an HTML tag to post it. To do a form post against
    the URL recipe and that goes against the same post mapping that we defined for
    the save of a new recipe. So Spring is smart enough know if it's a brand new
    recipe or an existing recipe and that is a function of having that ID value
    populated or not. So an existing one will have the ID
    value populated from that hidden parameter for ID. A new one will not have
    a ID. It will be an empty String.*/
    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
