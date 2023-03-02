package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.services.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.services.product.ProductDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface IRecipeService {

    UUID create(RecipeCreateDTO recipeCreateDTO);
    Page<RecipeDTO> getPage(Pageable pageable);
    RecipeDTO update(UUID uuid, Instant lastUpdated,
                      RecipeCreateDTO recipeCreateDTO);
}
