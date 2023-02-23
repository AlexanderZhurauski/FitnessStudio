package org.mycompany.fitness.service;

import jakarta.persistence.OptimisticLockException;
import org.mycompany.fitness.core.dto.BaseEssence;
import org.mycompany.fitness.core.dto.services.recipe.RecipeCompositionCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeDTO;
import org.mycompany.fitness.core.exceptions.custom.EntityNotFoundException;
import org.mycompany.fitness.dao.entities.Product;
import org.mycompany.fitness.dao.entities.ProductInstance;
import org.mycompany.fitness.dao.entities.Recipe;
import org.mycompany.fitness.dao.repositories.api.IRecipeRepository;
import org.mycompany.fitness.service.api.IProductService;
import org.mycompany.fitness.service.api.IRecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
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
        Recipe recipe = convertToEntity(recipeCreateDTO);
        return this.recipeRepository.save(recipe).getUuid();
    }

    @Override
    public Page<RecipeDTO> getPage(Pageable pageable) {
        Page<Recipe> recipePage = this.recipeRepository.findAll(pageable);
        return recipePage.map(this::convertFromEntity);
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
        return convertFromEntity(recipe);
    }

    private Recipe convertToEntity(RecipeCreateDTO createDTO) {
        Recipe recipe = new Recipe();
        recipe.setTitle(createDTO.getTitle());

        List<RecipeCompositionCreateDTO> recipeComposition = createDTO.getComposition();
        List<ProductInstance> productList = recipeComposition.stream()
                .map(dto -> {
                    Product product = this.productService.getByID(dto.getProduct());
                    ProductInstance productInstance = new ProductInstance();
                    double ratio = 1.0 * dto.getWeight() / product.getWeight();

                    productInstance.setProduct(product);
                    productInstance.setWeight(dto.getWeight());
                    productInstance.setCalories((int) (ratio * product.getCalories()));
                    productInstance.setProteins(round(ratio
                            * product.getProteins(), 1));
                    productInstance.setFats(round(ratio
                            * product.getFats(), 1));
                    productInstance.setCarbohydrates(round(ratio
                            * product.getCarbohydrates(), 1));

                    return productInstance;
                })
                .toList();
        recipe.setComposition(productList);

        int weight = productList.stream()
                .mapToInt(ProductInstance::getWeight)
                .sum();
        int calories = productList.stream()
                .mapToInt(ProductInstance::getCalories)
                .sum();
        double proteins = productList.stream()
                .mapToDouble(ProductInstance::getProteins)
                .sum();
        double fats = productList.stream()
                .mapToDouble(ProductInstance::getFats)
                .sum();
        double carbohydrates = productList.stream()
                .mapToDouble(ProductInstance::getCarbohydrates)
                .sum();

        recipe.setWeight(weight);
        recipe.setCalories(calories);
        recipe.setProteins(proteins);
        recipe.setFats(fats);
        recipe.setCarbohydrates(carbohydrates);

        return recipe;
    }

    private RecipeDTO convertFromEntity(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        BaseEssence baseEssence = new BaseEssence(recipe.getUuid(),
                recipe.getCreationTime(), recipe.getLastUpdated());
        recipeDTO.setBaseEssence(baseEssence);
        recipeDTO.setTitle(recipe.getTitle());


        return  recipeDTO;
    }

    private double round(double number, int places) {
        if (places < 1 || places > 1000) {
            throw new IllegalArgumentException("Can't round to '"
                    + places + "' decimal places!");
        }
        double multiplier = 10.0 * places;
        return Math.round(number * multiplier) / multiplier;
    }
}
