package org.mycompany.fitness.service;

import jakarta.persistence.OptimisticLockException;
import org.mycompany.fitness.core.dto.UserCreateDTO;
import org.mycompany.fitness.core.dto.UserDTO;
import org.mycompany.fitness.core.exceptions.custom.UserNotFoundException;
import org.mycompany.fitness.dao.entities.User;
import org.mycompany.fitness.dao.repositories.api.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.mycompany.fitness.service.api.IUserDataService;

import java.util.UUID;

public class UserDataService implements IUserDataService {

    private IUserRepository userRepository;

    public UserDataService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UUID create(UserCreateDTO userCreateDTO) {
        User user = new User(userCreateDTO);
        return this.userRepository.save(user).getUuid();
    }

    @Override
    public Page<UserDTO> getPage(Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(pageable);
        Page<UserDTO> userPageDTO = userPage.map(UserDTO::new);
        return userPageDTO;
    }

    @Override
    public UserDTO get(UUID uuid) {
        User user = this.userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));
        return new UserDTO(user);
    }

    @Override
    public UserDTO update(UUID uuid, Long lastUpdated, UserCreateDTO userCreateDTO) {
        User user = this.userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));

        if (user.getLastUpdated().toEpochMilli() != lastUpdated) {
            throw new OptimisticLockException("User with id '" + user.getUuid()
                    + "' has already been modified!");
        }

        user.setMail(userCreateDTO.getMail());
        user.setRole(userCreateDTO.getRole());
        user.setPassword(userCreateDTO.getPassword());
        user.setFullName(userCreateDTO.getFullName());
        user.setStatus(userCreateDTO.getStatus());
        this.userRepository.save(user);

        return new UserDTO(user);
    }
}
