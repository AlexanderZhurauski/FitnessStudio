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
import org.mycompany.fitness.dao.repositories.api.IProductRepository;
import org.mycompany.fitness.dao.repositories.api.IRecipeRepository;
import org.mycompany.fitness.dao.repositories.api.IUserDataRepository;
import org.mycompany.fitness.service.ProductService;
import org.mycompany.fitness.service.RecipeService;
import org.mycompany.fitness.service.UserAuthenticationService;
import org.mycompany.fitness.service.UserDataService;
import org.mycompany.fitness.service.api.IProductService;
import org.mycompany.fitness.service.api.IRecipeService;
import org.mycompany.fitness.service.api.IUserAuthenticationService;
import org.mycompany.fitness.service.api.IUserDataService;
import org.mycompany.fitness.service.converters.api.IEntityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public IUserDataService userDataService(IUserDataRepository userDataRepository,
                                            IEntityConverter<User, UserCreateDTO, UserDTO> userConverter) {
        return new UserDataService(userDataRepository, userConverter);
    }

    @Bean
    public IUserAuthenticationService userAuthenticationService(IUserDataService userDataService) {
        return new UserAuthenticationService(userDataService);
    }

    @Bean
    public IProductService productService(IProductRepository productRepository,
                                          IEntityConverter<Product, ProductCreateDTO, ProductDTO> productConverter) {
        return new ProductService(productRepository, productConverter);
    }

    @Bean
    public IRecipeService recipeService(IRecipeRepository recipeRepository,
                                        IEntityConverter<Recipe, RecipeCreateDTO, RecipeDTO> recipeConverter,
                                         IProductService productService) {
        return new RecipeService(recipeRepository, recipeConverter, productService);
    }
}
