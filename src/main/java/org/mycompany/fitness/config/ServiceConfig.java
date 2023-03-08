package org.mycompany.fitness.config;

import org.mycompany.fitness.core.dto.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.product.ProductDTO;
import org.mycompany.fitness.core.dto.recipe.RecipeDTO;
import org.mycompany.fitness.core.dto.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.core.dto.user.UserRegistrationDTO;
import org.mycompany.fitness.dao.entities.*;
import org.mycompany.fitness.dao.repositories.IProductRepository;
import org.mycompany.fitness.dao.repositories.IRecipeRepository;
import org.mycompany.fitness.dao.repositories.IUserAuthenticationRepository;
import org.mycompany.fitness.dao.repositories.IUserDataRepository;
import org.mycompany.fitness.security.UserHolder;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class ServiceConfig {

    @Bean
    public IUserDataService userDataService(IUserDataRepository userRepository,
                                            Converter<UserCreateDTO, User> toEntityConverter,
                                            Converter<User, UserDTO> toDTOConverter,
                                            PasswordEncoder passwordEncoder) {

        return new UserDataService(userRepository, toEntityConverter, toDTOConverter, passwordEncoder);
    }

    @Bean
    public IUserAuthenticationService userAuthenticationService(UserDetailsService userDetailsService,
                                                                IUserDataService userDataService,
                                                                UserHolder userHolder,
                                                                Converter<User, UserDTO> toDTOConverter,
                                                                Converter<UserRegistrationDTO, UserCreateDTO> registrationConverter,
                                                                PasswordEncoder passwordEncoder) {

        return new UserAuthenticationService(userDetailsService, userDataService, userHolder,
                toDTOConverter, registrationConverter, passwordEncoder);
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
    @Bean
    public UserDetailsService userDetailsService(IUserAuthenticationRepository authenticationRepository) {
        return username -> authenticationRepository.findUserByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '"
                        + username + "' has not been found!"));
    }
}
