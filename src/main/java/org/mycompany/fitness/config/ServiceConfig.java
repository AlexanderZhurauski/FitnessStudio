package org.mycompany.fitness.config;

import org.jobrunr.scheduling.JobScheduler;
import org.mycompany.fitness.core.dto.product.ProductCreateDTO;
import org.mycompany.fitness.core.dto.product.ProductDTO;
import org.mycompany.fitness.core.dto.recipe.RecipeDTO;
import org.mycompany.fitness.core.dto.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.core.dto.user.UserRegistrationDTO;
import org.mycompany.fitness.dao.entities.*;
import org.mycompany.fitness.dao.repositories.*;
import org.mycompany.fitness.security.JwtTokenUtil;
import org.mycompany.fitness.security.UserHolder;
import org.mycompany.fitness.service.*;
import org.mycompany.fitness.service.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.UnknownHostException;


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
                                                                IEmailService emailService,
                                                                UserHolder userHolder,
                                                                Converter<User, UserDTO> toDTOConverter,
                                                                Converter<UserRegistrationDTO, UserCreateDTO> registrationConverter,
                                                                JwtTokenUtil tokenUtil,
                                                                PasswordEncoder passwordEncoder) {

        return new UserAuthenticationService(userDetailsService, userDataService, emailService,
                userHolder, toDTOConverter, registrationConverter, tokenUtil, passwordEncoder);
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

    @Bean
    public IEmailService emailService(JavaMailSender mailSender, JobScheduler jobScheduler,
                                      IConfirmationTokenRepository tokenRepository) {
        return new EmailService(mailSender, jobScheduler, tokenRepository);
    }
}
