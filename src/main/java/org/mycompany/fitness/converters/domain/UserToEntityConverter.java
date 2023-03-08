package org.mycompany.fitness.converters.domain;

import org.mycompany.fitness.core.dto.user.UserCreateDTO;
import org.mycompany.fitness.dao.entities.Role;
import org.mycompany.fitness.dao.entities.Status;
import org.mycompany.fitness.dao.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserToEntityConverter implements Converter<UserCreateDTO, User> {

    private PasswordEncoder passwordEncoder;

    public UserToEntityConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User convert(UserCreateDTO userCreateDTO) {

        User userEntity = new User();
        userEntity.setMail(userCreateDTO.getMail());
        userEntity.setPassword(this.passwordEncoder.encode(userCreateDTO.getPassword()));
        userEntity.setFullName(userCreateDTO.getFullName());
        userEntity.setRole(new Role(userCreateDTO.getRole()));
        userEntity.setStatus(new Status(userCreateDTO.getStatus()));

        return userEntity;
    }
}
