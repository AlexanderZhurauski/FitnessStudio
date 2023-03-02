package org.mycompany.fitness.service.converters;

import org.mycompany.fitness.core.dto.BaseEssence;
import org.mycompany.fitness.core.dto.services.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.services.product.ProductDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeCompositionCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeCompositionDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeDTO;
import org.mycompany.fitness.dao.entities.Product;
import org.mycompany.fitness.dao.entities.ProductInstance;
import org.mycompany.fitness.dao.entities.Recipe;
import org.mycompany.fitness.service.api.IProductService;
import org.mycompany.fitness.service.converters.api.IEntityConverter;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeConverter implements IEntityConverter<Recipe, RecipeCreateDTO, RecipeDTO> {

    private IProductService productService;
    private IEntityConverter<Product, ProductCreateDTO, ProductDTO> productConverter;

    public RecipeConverter(IProductService productService,
                           IEntityConverter<Product, ProductCreateDTO, ProductDTO> productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @Override
    public Recipe convertToEntity(RecipeCreateDTO recipeCreateDTO) {
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeCreateDTO.getTitle());

        List<RecipeCompositionCreateDTO> recipeComposition = recipeCreateDTO.getComposition();
        List<ProductInstance> productList = recipeComposition.stream()
                .map(dto -> {
                    Product product = this.productService.getByID(dto.getProduct());
                    ProductInstance productInstance = new ProductInstance();

                    productInstance.setProduct(product);
                    productInstance.setWeight(dto.getWeight());

                    return productInstance;
                })
                .collect(Collectors.toList());
        recipe.setComposition(productList);

        return recipe;
    }

    @Override
    public RecipeDTO convertFromEntity(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        BaseEssence baseEssence = new BaseEssence(recipe.getUuid(),
                recipe.getCreationTime(), recipe.getLastUpdated());
        recipeDTO.setBaseEssence(baseEssence);
        recipeDTO.setTitle(recipe.getTitle());

        List<ProductInstance> productList = recipe.getComposition();
        List<RecipeCompositionDTO> productComposition = calculateRecipeComposition(productList);
        recipeDTO.setComposition(productComposition);

        int weight = (int) round(productComposition
                .stream()
                .mapToInt(RecipeCompositionDTO::getWeight)
                .sum(), 2);
        int calories = (int) round(productComposition
                .stream()
                .mapToInt(RecipeCompositionDTO::getCalories)
                .sum(), 2);
        double proteins = round(productComposition
                .stream()
                .mapToDouble(RecipeCompositionDTO::getProteins)
                .sum(), 2);
        double fats = round(productComposition
                .stream()
                .mapToDouble(RecipeCompositionDTO::getFats)
                .sum(), 2);
        double carbohydrates = round(productComposition
                .stream()
                .mapToDouble(RecipeCompositionDTO::getCarbohydrates)
                .sum(), 2);

        recipeDTO.setWeight(weight);
        recipeDTO.setCalories(calories);
        recipeDTO.setProteins(proteins);
        recipeDTO.setFats(fats);
        recipeDTO.setCarbohydrates(carbohydrates);

        return  recipeDTO;
    }

    private List<RecipeCompositionDTO> calculateRecipeComposition(List<ProductInstance> productList) {
        List<RecipeCompositionDTO> recipeComposition = productList.stream()
                .map(product -> {
                    RecipeCompositionDTO compositionDTO = new RecipeCompositionDTO();

                    ProductDTO productDTO = productConverter.convertFromEntity(product.getProduct());
                    int actualWeight = product.getWeight();
                    int standardWeight = productDTO.getWeight();
                    double ratio = 1.0 * actualWeight / standardWeight;
                    int calories = (int) (ratio * productDTO.getCalories());
                    double proteins = round(ratio * productDTO.getProteins(),1);
                    double fats = round(ratio * productDTO.getFats(), 1);
                    double carbohydrates = round(ratio * productDTO.getCarbohydrates(), 1);

                    compositionDTO.setProduct(productDTO);
                    compositionDTO.setWeight(actualWeight);
                    compositionDTO.setCalories(calories);
                    compositionDTO.setProteins(proteins);
                    compositionDTO.setFats(fats);
                    compositionDTO.setCarbohydrates(carbohydrates);

                    return compositionDTO;
                })
                .toList();

        return recipeComposition;
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
