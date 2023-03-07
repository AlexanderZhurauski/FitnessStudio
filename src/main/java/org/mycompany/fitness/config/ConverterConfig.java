package org.mycompany.fitness.config;

import org.mycompany.fitness.converters.domain.*;
import org.mycompany.fitness.core.dto.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.product.ProductDTO;
import org.mycompany.fitness.core.dto.recipe.RecipeCreateDTO;
import org.mycompany.fitness.core.dto.recipe.RecipeDTO;
import org.mycompany.fitness.core.dto.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.dao.entities.Product;
import org.mycompany.fitness.dao.entities.Recipe;
import org.mycompany.fitness.dao.entities.User;
import org.mycompany.fitness.service.api.IProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConverterConfig {

    @Bean
    public Converter<User, UserDTO> userToDTOConverter() {
        return new UserToDTOConverter();
    }

    @Bean
    public Converter<UserCreateDTO, User> userToEntityConverter(PasswordEncoder passwordEncoder) {
        return new UserToEntityConverter(passwordEncoder);
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
