package com.example.cyrilus.recetus.repository;

import com.example.cyrilus.recetus.model.Recipe;
import org.springframework.data.repository.CrudRepository;

//@RepositoryRestResource(path = "recipe")
@Deprecated
public interface RecipeRepositoryDataRest extends CrudRepository<Recipe, Long> {

}
