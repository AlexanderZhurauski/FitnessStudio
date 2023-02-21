package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.UserCreateDTO;
import org.mycompany.fitness.core.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserDataService {

    UUID create(UserCreateDTO userCreateDTO);
    Page<UserDTO> getPage(Pageable pageable);
    UserDTO get(UUID uuid);
    UserDTO update(UUID uuid, Long lastUpdated, UserCreateDTO userCreateDTO);
}
