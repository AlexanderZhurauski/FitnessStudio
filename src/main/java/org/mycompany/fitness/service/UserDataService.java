package org.mycompany.fitness.service;

import jakarta.persistence.OptimisticLockException;
import org.mycompany.fitness.core.dto.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.core.exceptions.custom.EntityNotFoundException;
import org.mycompany.fitness.dao.entities.Role;
import org.mycompany.fitness.dao.entities.Status;
import org.mycompany.fitness.dao.entities.User;
import org.mycompany.fitness.dao.repositories.IUserDataRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.mycompany.fitness.service.api.IUserDataService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.UUID;

public class UserDataService implements IUserDataService {

    private IUserDataRepository userRepository;
    private Converter<UserCreateDTO, User> toEntityConverter;
    private Converter<User, UserDTO> toDTOConverter;
    private PasswordEncoder passwordEncoder;

    public UserDataService(IUserDataRepository userRepository,
                           Converter<UserCreateDTO, User> toEntityConverter,
                           Converter<User, UserDTO> toDTOConverter,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.toEntityConverter = toEntityConverter;
        this.toDTOConverter = toDTOConverter;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void create(UserCreateDTO userCreateDTO) {
        User user = this.toEntityConverter.convert(userCreateDTO);
        this.userRepository.save(user);
    }

    @Override
    public Page<UserDTO> getPage(Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(pageable);
        Page<UserDTO> userPageDTO = userPage.map(this.toDTOConverter::convert);
        return userPageDTO;
    }

    @Override
    public UserDTO get(UUID uuid) {
        User user = this.userRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid, "user"));
        return toDTOConverter.convert(user);
    }

    @Override
    public void update(UUID uuid, Instant lastUpdated, UserCreateDTO userCreateDTO) {
        User user = this.userRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid, "user"));

        if (user.getLastUpdated().toEpochMilli() != lastUpdated.toEpochMilli()) {
            throw new OptimisticLockException("User with id '" + user.getUuid()
                    + "' has already been modified!");
        }

        user.setMail(userCreateDTO.getMail());
        user.setRole(new Role(userCreateDTO.getRole()));
        user.setPassword(this.passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setFullName(userCreateDTO.getFullName());
        user.setStatus(new Status(userCreateDTO.getStatus()));
        this.userRepository.save(user);
    }
}
