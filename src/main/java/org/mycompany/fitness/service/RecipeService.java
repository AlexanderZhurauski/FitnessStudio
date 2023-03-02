package org.mycompany.fitness.service;

import jakarta.persistence.OptimisticLockException;
import org.mycompany.fitness.core.dto.services.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeDTO;
import org.mycompany.fitness.core.exceptions.custom.EntityNotFoundException;
import org.mycompany.fitness.dao.entities.Recipe;
import org.mycompany.fitness.dao.repositories.api.IRecipeRepository;
import org.mycompany.fitness.service.api.IProductService;
import org.mycompany.fitness.service.api.IRecipeService;
import org.mycompany.fitness.service.converters.api.IEntityConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public class RecipeService implements IRecipeService {

    private IRecipeRepository recipeRepository;
    private IEntityConverter<Recipe, RecipeCreateDTO, RecipeDTO> converter;
    private IProductService productService;

    public RecipeService(IRecipeRepository recipeRepository,
                         IEntityConverter<Recipe, RecipeCreateDTO, RecipeDTO> converter,
                         IProductService productService) {
        this.recipeRepository = recipeRepository;
        this.converter = converter;
        this.productService = productService;
    }

    @Override
    public UUID create(RecipeCreateDTO recipeCreateDTO) {
        Recipe recipe = converter.convertToEntity(recipeCreateDTO);
        return this.recipeRepository.save(recipe).getUuid();
    }

    @Override
    public Page<RecipeDTO> getPage(Pageable pageable) {
        Page<Recipe> recipePage = this.recipeRepository.findAll(pageable);
        return recipePage.map(converter::convertFromEntity);
    }

    @Override
    public RecipeDTO update(UUID uuid, Instant lastUpdated,
                             RecipeCreateDTO recipeCreateDTO) {
        Recipe recipe = this.recipeRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid, "recipe"));

        if (recipe.getLastUpdated().toEpochMilli() != lastUpdated.toEpochMilli()) {
            throw new OptimisticLockException("Recipe with id '" + recipe.getUuid()
                    + "' has already been modified!");
        }

        Recipe updatedRecipe = converter.convertToEntity(recipeCreateDTO);
        recipe.setTitle(updatedRecipe.getTitle());
        recipe.setComposition(updatedRecipe.getComposition());
        this.recipeRepository.save(recipe);

        return converter.convertFromEntity(recipe);
    }
}
