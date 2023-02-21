package org.mycompany.fitness.config;

import org.mycompany.fitness.dao.repositories.api.IUserRepository;
import org.mycompany.fitness.service.UserAuthenticationService;
import org.mycompany.fitness.service.UserDataService;
import org.mycompany.fitness.service.api.IUserAuthenticationService;
import org.mycompany.fitness.service.api.IUserDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public IUserDataService userDataService(IUserRepository userRepository) {
        return new UserDataService(userRepository);
    }

    @Bean
    public IUserAuthenticationService userAuthenticationService(IUserDataService userDataService) {
        return new UserAuthenticationService(userDataService);
    }
}
