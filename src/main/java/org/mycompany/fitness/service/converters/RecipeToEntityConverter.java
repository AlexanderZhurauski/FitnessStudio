package org.mycompany.fitness.service.converters;

import org.mycompany.fitness.core.dto.services.recipe.RecipeCompositionCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeCreateDTO;
import org.mycompany.fitness.dao.entities.Product;
import org.mycompany.fitness.dao.entities.ProductInstance;
import org.mycompany.fitness.dao.entities.Recipe;
import org.mycompany.fitness.service.api.IProductService;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeToEntityConverter implements Converter<RecipeCreateDTO, Recipe> {

    private IProductService productService;

    public RecipeToEntityConverter(IProductService productService) {
        this.productService = productService;
    }
    @Override
    public Recipe convert(RecipeCreateDTO recipeCreateDTO) {

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
}
