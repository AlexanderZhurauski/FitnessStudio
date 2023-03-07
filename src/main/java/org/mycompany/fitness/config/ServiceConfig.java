package org.mycompany.fitness.config;

import org.mycompany.fitness.core.dto.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.product.ProductDTO;
import org.mycompany.fitness.core.dto.recipe.RecipeDTO;
import org.mycompany.fitness.core.dto.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.dao.entities.Product;
import org.mycompany.fitness.dao.entities.Recipe;
import org.mycompany.fitness.dao.entities.User;
import org.mycompany.fitness.dao.repositories.IProductRepository;
import org.mycompany.fitness.dao.repositories.IRecipeRepository;
import org.mycompany.fitness.dao.repositories.IUserDataRepository;
import org.mycompany.fitness.service.ProductService;
import org.mycompany.fitness.service.RecipeService;
import org.mycompany.fitness.service.UserAuthenticationService;
import org.mycompany.fitness.service.UserDataService;
import org.mycompany.fitness.service.api.IProductService;
import org.mycompany.fitness.service.api.IRecipeService;
import org.mycompany.fitness.service.api.IUserAuthenticationService;
import org.mycompany.fitness.service.api.IUserDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ServiceConfig {

    @Bean
    public IUserDataService userDataService(IUserDataRepository userRepository,
                                            Converter<UserCreateDTO, User> toEntityConverter,
                                            Converter<User, UserDTO> toDTOConverter) {

        return new UserDataService(userRepository, toEntityConverter, toDTOConverter);
    }

    @Bean
    public IUserAuthenticationService userAuthenticationService(IUserDataService userDataService) {

        return new UserAuthenticationService(userDataService);
    }

    @Bean
    public IProductService productService(IProductRepository productRepository,
                                          Converter<ProductCreateDTO, Product> toEntityConverter,
                                          Converter<Product, ProductDTO> toDTOConverter) {

        return new ProductService(productRepository, toEntityConverter, toDTOConverter);
    }

    @Bean
    public IRecipeService recipeService(IRecipeRepository recipeRepository,
                                        Converter<Recipe, RecipeDTO> toDTOConverter,
                                        IProductService productService) {

        return new RecipeService(recipeRepository, toDTOConverter, productService);
    }
}
