package org.mycompany.fitness.service.converters;

import org.mycompany.fitness.core.dto.BaseEssence;
import org.mycompany.fitness.core.dto.services.user.UserCreateDTO;
import org.mycompany.fitness.core.dto.services.user.UserDTO;
import org.mycompany.fitness.dao.entities.Role;
import org.mycompany.fitness.dao.entities.Status;
import org.mycompany.fitness.dao.entities.User;
import org.mycompany.fitness.service.converters.api.IEntityConverter;

public class UserConverter implements IEntityConverter<User, UserCreateDTO, UserDTO> {

    @Override
    public User convertToEntity(UserCreateDTO userCreateDTO) {
        User userEntity = new User();

        userEntity.setMail(userCreateDTO.getMail());
        userEntity.setPassword(userCreateDTO.getPassword());
        userEntity.setFullName(userCreateDTO.getFullName());
        userEntity.setRole(new Role(userCreateDTO.getRole()));
        userEntity.setStatus(new Status(userCreateDTO.getStatus()));

        return userEntity;
    }

    @Override
    public UserDTO convertFromEntity(User userEntity) {

        UserDTO userDTO = new UserDTO();
        BaseEssence baseEssence = new BaseEssence(userEntity.getUuid(),
                userEntity.getCreationTime(), userEntity.getLastUpdated());
        userDTO.setBaseEssence(baseEssence);
        userDTO.setMail(userEntity.getMail());
        userDTO.setFullName(userEntity.getFullName());
        userDTO.setRole(userEntity.getRole().getRole());
        userDTO.setStatus(userEntity.getStatus().getStatus());

        return userDTO;
    }
}
