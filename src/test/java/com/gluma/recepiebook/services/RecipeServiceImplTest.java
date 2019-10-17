package com.gluma.recepiebook.services;

import com.gluma.recepiebook.domain.Recipe;
import com.gluma.recepiebook.repositories.RecipeRepository;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipeDataSet = new HashSet();
        recipeDataSet.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeDataSet);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(),1);
        verify(recipeRepository, times(1)).findAll(); //verifying if findAll() was called once


    }
}