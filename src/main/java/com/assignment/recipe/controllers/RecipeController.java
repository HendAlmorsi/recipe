package com.assignment.recipe.controllers;

import com.assignment.recipe.models.Recipe;
import com.assignment.recipe.services.RecipeService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("")
    public List<Recipe> list() {
        return recipeService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> get(@PathVariable Long id) {
        try {
            Recipe recipe = recipeService.get(id);
            return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody Recipe recipe) {
        recipeService.save(recipe);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Recipe recipe, @PathVariable Long id) {
        try {
            Recipe existingRecipe = recipeService.get(id);
            recipe.setId(existingRecipe.getId());
            recipeService.save(recipe);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recipeService.delete(id);
    }
}
