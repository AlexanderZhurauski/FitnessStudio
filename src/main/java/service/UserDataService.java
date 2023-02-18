package service;

import core.dto.PageOfUserDTO;
import core.dto.UserCreateDTO;
import core.dto.UserDTO;
import core.exceptions.custom.UserNotFoundException;
import dao.entities.User;
import dao.repositories.api.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import service.api.IUserDataService;

import javax.persistence.OptimisticLockException;
import java.time.Instant;
import java.util.UUID;

@Service
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
    public PageOfUserDTO getPage(Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(pageable);
        return new PageOfUserDTO(userPage);
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
            throw new OptimisticLockException(user);
        }

        user.setMail(userCreateDTO.getMail());
        user.setRole(userCreateDTO.getRole());
        user.setPassword(userCreateDTO.getPassword());
        user.setFullName(userCreateDTO.getFullName());
        user.setStatus(userCreateDTO.getStatus());
        user.setLastUpdated(Instant.now());

        return new UserDTO(this.userRepository.save(user));
    }
}
