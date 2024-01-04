package com.example.cyrilus.recetus.repository;

import com.example.cyrilus.recetus.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeJpaRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByDurationPreparationGreaterThan(int min);

    @Query("SELECT r FROM Recipe r WHERE r.name = 'Classic Lasagna'")
    List<Recipe> specialLasagna();

    @Query("SELECT r FROM Recipe r WHERE r.name like %:word%")
    List<Recipe> findByNameContain(String word);


}
