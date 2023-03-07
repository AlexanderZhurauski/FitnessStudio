package org.mycompany.fitness.service;

import org.mycompany.fitness.core.dto.user.UserDTO;
import org.mycompany.fitness.core.dto.user.UserLoginDTO;
import org.mycompany.fitness.core.dto.user.UserRegistrationDTO;
import org.mycompany.fitness.service.api.IUserAuthenticationService;
import org.mycompany.fitness.service.api.IUserDataService;

import java.util.UUID;

public class UserAuthenticationService implements IUserAuthenticationService {

    private IUserDataService userDataService;

    public UserAuthenticationService(IUserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public void register(UserRegistrationDTO userRegistrationDTO) {
    }

    @Override
    public void verify(String code, String mail) {
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {

    }

    @Override
    public UserDTO getMyData(UUID uuid) {
        return this.userDataService.get(uuid);
    }
}
