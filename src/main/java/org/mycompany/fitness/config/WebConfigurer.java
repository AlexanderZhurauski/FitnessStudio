package org.mycompany.fitness.config;

import org.mycompany.fitness.converters.json.StringToInstantConverter;
import org.mycompany.fitness.security.JwtTokenUtil;
import org.mycompany.fitness.web.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToInstantConverter());
    }
    @Bean
    public JwtFilter jwtFilter(UserDetailsService userService, JwtTokenUtil tokenUtil) {
        return new JwtFilter(userService, tokenUtil);
    }
}