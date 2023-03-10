package org.mycompany.fitness.service;

import jakarta.persistence.OptimisticLockException;
import org.mycompany.fitness.core.dto.recipe.RecipeCompositionCreateDTO;
import org.mycompany.fitness.core.dto.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.recipe.RecipeDTO;
import org.mycompany.fitness.core.exceptions.custom.EntityNotFoundException;
import org.mycompany.fitness.dao.entities.Product;
import org.mycompany.fitness.dao.entities.ProductInstance;
import org.mycompany.fitness.dao.entities.Recipe;
import org.mycompany.fitness.dao.repositories.IRecipeRepository;
import org.mycompany.fitness.service.api.IProductService;
import org.mycompany.fitness.service.api.IRecipeService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RecipeService implements IRecipeService {

    private IRecipeRepository recipeRepository;
    private Converter<Recipe, RecipeDTO> toDTOConverter;
    private IProductService productService;

    public RecipeService(IRecipeRepository recipeRepository,
                         Converter<Recipe, RecipeDTO> toDTOConverter,
                         IProductService productService) {
        this.recipeRepository = recipeRepository;
        this.toDTOConverter = toDTOConverter;
        this.productService = productService;
    }

    @Override
    public void create(RecipeCreateDTO recipeCreateDTO) {
        Recipe recipe = convertToEntity(recipeCreateDTO);
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

        List<ProductInstance> productInstances = convertToProductInstances(recipeCreateDTO.getComposition());
        recipe.setTitle(recipeCreateDTO.getTitle());
        recipe.setComposition(productInstances);
        this.recipeRepository.save(recipe);
    }

    private Recipe convertToEntity(RecipeCreateDTO recipeCreateDTO) {

        Recipe recipe = new Recipe();
        recipe.setTitle(recipeCreateDTO.getTitle());

        List<RecipeCompositionCreateDTO> recipeComposition = recipeCreateDTO.getComposition();
        List<ProductInstance> productList = convertToProductInstances(recipeComposition);
        recipe.setComposition(productList);

        return recipe;
    }

    private List<ProductInstance> convertToProductInstances(
            List<RecipeCompositionCreateDTO> recipeCompositionCreateDTOS) {

        return recipeCompositionCreateDTOS.stream()
                .map(dto -> {
                    Product product = this.productService.getByID(dto.getProduct());
                    ProductInstance productInstance = new ProductInstance();

                    productInstance.setProduct(product);
                    productInstance.setWeight(dto.getWeight());

                    return productInstance;
                })
                .collect(Collectors.toList());
    }
}
