package com.assignment.recipe.services;

import com.assignment.recipe.models.Recipe;
import com.assignment.recipe.repositories.RecipeRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> listAll() {
        return recipeRepository.findAll();
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Recipe get(Long id) {
        return recipeRepository.findById(id).get();
    }

    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }
}
