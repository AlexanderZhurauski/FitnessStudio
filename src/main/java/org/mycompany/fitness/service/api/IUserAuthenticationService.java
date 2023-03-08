package org.mycompany.fitness.service.api;

import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.core.dto.user.UserLoginDTO;
import org.mycompany.fitness.core.dto.user.UserRegistrationDTO;

public interface IUserAuthenticationService {

    void register(UserRegistrationDTO userRegistrationDTO);
    void verify(String code, String mail);
    String login(UserLoginDTO userLoginDTO);
    UserDTO getMyData();
}
