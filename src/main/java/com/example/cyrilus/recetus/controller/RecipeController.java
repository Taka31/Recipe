package com.example.cyrilus.recetus.controller;

import com.example.cyrilus.recetus.exception.MyException;
import com.example.cyrilus.recetus.model.Recipe;
import com.example.cyrilus.recetus.service.RecipeService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class RecipeController {

    private final RecipeService myService;

    @Autowired
    public RecipeController(RecipeService myService) {
        this.myService = myService;
    }

    @GetMapping
    public List<Recipe> getAllRecipe() {
        return myService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public Recipe getByID(@PathVariable Long id) {
        Recipe recipe = myService.getRecipeById(id);
        if (Objects.isNull(recipe)) {
            throw new MyException("Recipe not found...");
        }
        return recipe;
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe recipeTemp = myService.createRecipe(recipe);
        if (Objects.isNull(recipeTemp)) {
            return ResponseEntity.noContent().build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recipeTemp.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @Valid @RequestBody Recipe recipe) {
        Recipe recipeTemp = myService.updateRecipe(recipe, id);
        if (Objects.isNull(recipeTemp)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        myService.deleteRecipe(id);
    }

    @GetMapping("/greater")
    public List<Recipe> findByDurationGreaterThan(@PathParam("min") int min){
        return myService.findByDurationMinuteGreaterThan(min);
    }

    @GetMapping("/special")
    public List<Recipe> specialLasagna(){
        return myService.specialLasagna();
    }

    @GetMapping("/name")
    public List<Recipe> findByNameContain(@PathParam("word") String word){
        return myService.findByNameContain(word);
    }




}
