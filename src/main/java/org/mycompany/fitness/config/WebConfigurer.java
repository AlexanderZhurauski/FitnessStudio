package org.mycompany.fitness.config;

import org.mycompany.fitness.converters.json.StringToInstantConverter;
import org.mycompany.fitness.web.filters.ExceptionHandlerFilter;
import org.mycompany.fitness.web.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToInstantConverter());
    }

    @Bean
    public ExceptionHandlerFilter exceptionHandlerFilter() {
        return new ExceptionHandlerFilter();
    }

    @Bean
    public JwtFilter jwtFilter(UserDetailsManager userManager) {
        return new JwtFilter(userManager);
    }
}
