package org.mycompany.fitness.service.converters;

import org.mycompany.fitness.core.dto.BaseEssence;
import org.mycompany.fitness.core.dto.services.user.UserDTO;
import org.mycompany.fitness.dao.entities.User;
import org.springframework.core.convert.converter.Converter;

public class UserToDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User userEntity) {

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
