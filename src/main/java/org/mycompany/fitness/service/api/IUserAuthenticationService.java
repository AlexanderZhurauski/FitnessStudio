package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.UserDTO;
import org.mycompany.fitness.core.dto.UserLoginDTO;
import org.mycompany.fitness.core.dto.UserRegistrationDTO;

import java.util.UUID;

public interface IUserAuthenticationService {

    UUID register(UserRegistrationDTO userRegistrationDTO);
    boolean verify(String code, String mail);
    void login(UserLoginDTO userLoginDTO);
    UserDTO getMyData(UUID uuid);
}
