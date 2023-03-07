package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.services.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IRecipeService {

    void create(RecipeCreateDTO recipeCreateDTO);
    Page<RecipeDTO> getPage(Pageable pageable);
    void update(UUID uuid, Long lastUpdated,
                      RecipeCreateDTO recipeCreateDTO);
}
