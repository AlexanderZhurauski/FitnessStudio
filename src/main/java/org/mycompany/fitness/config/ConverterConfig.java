package org.mycompany.fitness.config;

import org.mycompany.fitness.core.dto.services.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.services.product.ProductDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.services.recipe.RecipeDTO;
import org.mycompany.fitness.core.dto.services.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.services.user.UserDTO;
import org.mycompany.fitness.dao.entities.Product;
import org.mycompany.fitness.dao.entities.Recipe;
import org.mycompany.fitness.dao.entities.User;
import org.mycompany.fitness.service.api.IProductService;
import org.mycompany.fitness.service.converters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConverterConfig {

    @Bean
    public Converter<User, UserDTO> userToDTOConverter() {
        return new UserToDTOConverter();
    }

    @Bean
    public Converter<UserCreateDTO, User> userToEntityConverter() {
        return new UserToEntityConverter();
    }

    @Bean
    public Converter<Product, ProductDTO> productToDTOConverter() {
        return new ProductToDTOConverter();
    }

    @Bean
    public Converter<ProductCreateDTO, Product> productToEntityConverter() {
        return new ProductToEntityConverter();
    }

    @Bean
    public Converter<Recipe, RecipeDTO> recipeToDTOConverter() {
        return new RecipeToDTOConverter(productToDTOConverter());
    }

    @Bean
    public Converter<RecipeCreateDTO, Recipe> recipeToEntityConverter(IProductService productService) {
        return new RecipeToEntityConverter(productService);
    }
}
