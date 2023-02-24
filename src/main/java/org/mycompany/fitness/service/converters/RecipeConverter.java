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
                .collect(Collectors.toList());
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

    @Override
    public RecipeDTO convertFromEntity(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        BaseEssence baseEssence = new BaseEssence(recipe.getUuid(),
                recipe.getCreationTime(), recipe.getLastUpdated());
        recipeDTO.setBaseEssence(baseEssence);
        recipeDTO.setTitle(recipe.getTitle());

        List<ProductInstance> productList = recipe.getComposition();
        List<RecipeCompositionDTO> productComposition = productList.stream()
                .map(product -> new RecipeCompositionDTO(productConverter.convertFromEntity(product.getProduct()),
                        product.getWeight(), product.getCalories(), product.getProteins(),
                        product.getFats(), product.getCarbohydrates()))
                .toList();
        recipeDTO.setComposition(productComposition);

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
