package org.mycompany.fitness.service;

import jakarta.persistence.OptimisticLockException;
import org.mycompany.fitness.core.dto.services.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.services.user.UserDTO;
import org.mycompany.fitness.core.exceptions.custom.EntityNotFoundException;
import org.mycompany.fitness.dao.entities.Role;
import org.mycompany.fitness.dao.entities.Status;
import org.mycompany.fitness.dao.entities.User;
import org.mycompany.fitness.dao.repositories.api.IUserDataRepository;
import org.mycompany.fitness.service.converters.api.IEntityConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.mycompany.fitness.service.api.IUserDataService;

import java.util.UUID;

public class UserDataService implements IUserDataService {

    private IUserDataRepository userRepository;
    private IEntityConverter<User, UserCreateDTO, UserDTO> converter;

    public UserDataService(IUserDataRepository userRepository, IEntityConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }


    @Override
    public UUID create(UserCreateDTO userCreateDTO) {
        User user = converter.convertToEntity(userCreateDTO);
        return this.userRepository.save(user).getUuid();
    }

    @Override
    public Page<UserDTO> getPage(Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(pageable);
        Page<UserDTO> userPageDTO = userPage.map(converter::convertFromEntity);
        return userPageDTO;
    }

    @Override
    public UserDTO get(UUID uuid) {
        User user = this.userRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid, "user"));
        return converter.convertFromEntity(user);
    }

    @Override
    public UserDTO update(UUID uuid, Long lastUpdated, UserCreateDTO userCreateDTO) {
        User user = this.userRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid, "user"));

        if (user.getLastUpdated().toEpochMilli() != lastUpdated) {
            throw new OptimisticLockException("User with id '" + user.getUuid()
                    + "' has already been modified!");
        }

        user.setMail(userCreateDTO.getMail());
        user.setRole(new Role(userCreateDTO.getRole()));
        user.setPassword(userCreateDTO.getPassword());
        user.setFullName(userCreateDTO.getFullName());
        user.setStatus(new Status(userCreateDTO.getStatus()));
        this.userRepository.save(user);

        return converter.convertFromEntity(user);
    }
}
