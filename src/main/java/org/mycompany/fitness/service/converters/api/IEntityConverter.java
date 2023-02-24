package org.mycompany.fitness.service.converters.api;

public interface IEntityConverter<Entity, CreateDTO, ViewDTO> {

    Entity convertToEntity(CreateDTO createDTO);
    ViewDTO convertFromEntity(Entity entity);
}
