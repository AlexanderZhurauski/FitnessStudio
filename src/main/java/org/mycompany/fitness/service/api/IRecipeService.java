package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.recipe.RecipeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface IRecipeService {

    void create(RecipeCreateDTO recipeCreateDTO);
    Page<RecipeDTO> getPage(Pageable pageable);
    void update(UUID uuid, Instant lastUpdated, RecipeCreateDTO recipeCreateDTO);
}
