package org.mycompany.fitness.config;

import org.mycompany.fitness.core.dto.enums.UserRole;
import org.mycompany.fitness.core.dto.enums.UserStatus;
import org.mycompany.fitness.core.dto.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.product.ProductDTO;
import org.mycompany.fitness.core.dto.recipe.RecipeDTO;
import org.mycompany.fitness.core.dto.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.dao.entities.*;
import org.mycompany.fitness.dao.repositories.IProductRepository;
import org.mycompany.fitness.dao.repositories.IRecipeRepository;
import org.mycompany.fitness.dao.repositories.IUserDataRepository;
import org.mycompany.fitness.security.JwtTokenUtil;
import org.mycompany.fitness.service.ProductService;
import org.mycompany.fitness.service.RecipeService;
import org.mycompany.fitness.service.UserDataService;
import org.mycompany.fitness.service.api.IProductService;
import org.mycompany.fitness.service.api.IRecipeService;
import org.mycompany.fitness.service.api.IUserDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

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
    public UserDetailsManager userDetailsManager(DataSource dataSource, PasswordEncoder encoder) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        UserDetails userDetails = (new User("super@mail.ru", encoder.encode("123"),
                "ggg", new Role(UserRole.USER), new Status(UserStatus.ACTIVATED)));
        userDetailsManager.createUser(userDetails);

        System.out.println(JwtTokenUtil.generateAccessToken(userDetails));
        return userDetailsManager;
    }
}
