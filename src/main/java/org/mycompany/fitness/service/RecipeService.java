package org.mycompany.fitness.service;

import jakarta.persistence.OptimisticLockException;
import org.mycompany.fitness.core.dto.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.recipe.RecipeDTO;
import org.mycompany.fitness.core.exceptions.custom.EntityNotFoundException;
import org.mycompany.fitness.dao.entities.Recipe;
import org.mycompany.fitness.dao.repositories.IRecipeRepository;
import org.mycompany.fitness.service.api.IRecipeService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public class RecipeService implements IRecipeService {

    private IRecipeRepository recipeRepository;
    private Converter<RecipeCreateDTO, Recipe> toEntityConverter;
    private Converter<Recipe, RecipeDTO> toDTOConverter;

    public RecipeService(IRecipeRepository recipeRepository,
                         Converter<RecipeCreateDTO, Recipe> toEntityConverter,
                         Converter<Recipe, RecipeDTO> toDTOConverter) {
        this.recipeRepository = recipeRepository;
        this.toEntityConverter = toEntityConverter;
        this.toDTOConverter = toDTOConverter;
    }

    @Override
    public void create(RecipeCreateDTO recipeCreateDTO) {
        Recipe recipe = toEntityConverter.convert(recipeCreateDTO);
        this.recipeRepository.save(recipe);
    }

    @Override
    public Page<RecipeDTO> getPage(Pageable pageable) {
        Page<Recipe> recipePage = this.recipeRepository.findAll(pageable);
        return recipePage.map(this.toDTOConverter::convert);
    }

    @Override
    public void update(UUID uuid, Instant lastUpdated, RecipeCreateDTO recipeCreateDTO) {
        Recipe recipe = this.recipeRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid, "recipe"));

        if (recipe.getLastUpdated().toEpochMilli() != lastUpdated.toEpochMilli()) {
            throw new OptimisticLockException("Recipe with id '" + recipe.getUuid()
                    + "' has already been modified!");
        }

        Recipe updatedRecipe = this.toEntityConverter.convert(recipeCreateDTO);
        recipe.setTitle(updatedRecipe.getTitle());
        recipe.setComposition(updatedRecipe.getComposition());
        this.recipeRepository.save(recipe);
    }
}
