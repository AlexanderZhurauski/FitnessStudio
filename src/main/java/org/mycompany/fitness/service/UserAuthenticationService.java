package org.mycompany.fitness.service;

import org.mycompany.fitness.core.dto.UserDTO;
import org.mycompany.fitness.core.dto.UserLoginDTO;
import org.mycompany.fitness.core.dto.UserRegistrationDTO;
import org.mycompany.fitness.service.api.IUserAuthenticationService;
import org.mycompany.fitness.service.api.IUserDataService;

import java.util.UUID;

public class UserAuthenticationService implements IUserAuthenticationService {

    private IUserDataService userDataService;

    public UserAuthenticationService(IUserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public UUID register(UserRegistrationDTO userRegistrationDTO) {
        return null;
    }

    @Override
    public boolean verify(String code, String mail) {
        return false;
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {

    }

    @Override
    public UserDTO getMyData(UUID uuid) {
        return this.userDataService.get(uuid);
    }
}
