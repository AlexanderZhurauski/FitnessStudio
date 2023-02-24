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
import org.mycompany.fitness.service.converters.ProductConverter;
import org.mycompany.fitness.service.converters.RecipeConverter;
import org.mycompany.fitness.service.converters.UserConverter;
import org.mycompany.fitness.service.converters.api.IEntityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

    @Bean
    public IEntityConverter<User, UserCreateDTO, UserDTO> userConverter() {
        return new UserConverter();
    }

    @Bean
    public IEntityConverter<Product, ProductCreateDTO, ProductDTO> productConverter() {
        return new ProductConverter();
    }

    @Bean
    public IEntityConverter<Recipe, RecipeCreateDTO, RecipeDTO> recipeConverter(IProductService productService) {
        return new RecipeConverter(productService, productConverter());
    }
}
