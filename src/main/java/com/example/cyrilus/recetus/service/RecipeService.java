package com.example.cyrilus.recetus.service;

import com.example.cyrilus.recetus.model.Recipe;
import com.example.cyrilus.recetus.repository.RecipeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RecipeService {


    private final RecipeJpaRepository repo;

    @Autowired
    public RecipeService(RecipeJpaRepository repo) {
        this.repo = repo;
    }

    public List<Recipe> getAllRecipes() {
        return repo.findAll();
    }

    public Recipe getRecipeById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Recipe createRecipe(Recipe recipe) {
        return this.repo.save(recipe);
    }

    public Recipe updateRecipe(Recipe recipe, Long id) {

        Recipe myRecipeTemp = repo.findById(id).orElse(null);
        if (!Objects.isNull(myRecipeTemp)) {
            recipe.setId(id);
            myRecipeTemp = repo.save(recipe);
        }
        return myRecipeTemp;
    }

    public void deleteRecipe(Long id) {
        repo.deleteById(id);
    }

    public List<Recipe> findByDurationMinuteGreaterThan (int min){
        return repo.findByDurationPreparationGreaterThan(min);
    }

    public List<Recipe> specialLasagna(){
        return repo.specialLasagna();
    }

    public List<Recipe> findByNameContain(String word){
        return repo.findByNameContain(word);
    }

}
