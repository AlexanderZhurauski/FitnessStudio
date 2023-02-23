package org.mycompany.fitness.service;

import jakarta.persistence.OptimisticLockException;
import org.mycompany.fitness.core.dto.services.product.ProductDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeDTO;
import org.mycompany.fitness.core.exceptions.custom.EntityNotFoundException;
import org.mycompany.fitness.dao.entities.Product;
import org.mycompany.fitness.dao.entities.Recipe;
import org.mycompany.fitness.dao.repositories.api.IRecipeRepository;
import org.mycompany.fitness.service.api.IProductService;
import org.mycompany.fitness.service.api.IRecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class RecipeService implements IRecipeService {

    private IRecipeRepository recipeRepository;
    private IProductService productService;

    public RecipeService(IRecipeRepository recipeRepository,
                         IProductService productService) {
        this.recipeRepository = recipeRepository;
        this.productService = productService;
    }

    @Override
    public UUID create(RecipeCreateDTO recipeCreateDTO) {
        Recipe recipe = new Recipe(recipeCreateDTO0);
        return this.recipeRepository.save(recipe).getUuid();
    }

    @Override
    public Page<RecipeDTO> getPage(Pageable pageable) {
        Page<Recipe> recipePage = this.recipeRepository.findAll(pageable);
        return recipePage.map(RecipeDTO::new);
    }

    @Override
    public RecipeDTO update(UUID uuid, Long lastUpdated,
                             RecipeCreateDTO recipeCreateDTO) {
        Recipe recipe = this.recipeRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid, "recipe"));

        if (recipe.getLastUpdated().toEpochMilli() != lastUpdated) {
            throw new OptimisticLockException("Recipe with id '" + recipe.getUuid()
                    + "' has already been modified!");
        }

        recipe.setTitle(recipeCreateDTO.getTitle());

        this.recipeRepository.save(recipe);
        return new RecipeDTO(recipe);
    }
}
