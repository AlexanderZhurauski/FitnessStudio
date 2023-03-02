package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.services.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.services.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface IUserDataService {

    UUID create(UserCreateDTO userCreateDTO);
    Page<UserDTO> getPage(Pageable pageable);
    UserDTO get(UUID uuid);
    UserDTO update(UUID uuid, Instant lastUpdated, UserCreateDTO userCreateDTO);
}
