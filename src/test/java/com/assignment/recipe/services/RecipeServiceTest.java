package com.assignment.recipe.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.assignment.recipe.models.Recipe;
import com.assignment.recipe.repositories.RecipeRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;

public class RecipeServiceTest {
    RecipeRepository recipeRepository = mock(RecipeRepository.class);
    RecipeService recipeService = new RecipeService(recipeRepository);

    @Test
    public void listAll() {
        Mockito.when(recipeRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(recipeService.listAll().isEmpty());

        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(mock(Recipe.class));

        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);
        assertTrue(recipeService.listAll().size() == 1);
    }
}