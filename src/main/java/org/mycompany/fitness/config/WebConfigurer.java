package org.mycompany.fitness.config;

import org.mycompany.fitness.converters.json.StringToInstantConverter;
import org.mycompany.fitness.security.JwtTokenUtil;
import org.mycompany.fitness.security.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

    @Bean
    public String appAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
