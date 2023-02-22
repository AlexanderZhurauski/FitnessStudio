package org.mycompany.fitness.core.exceptions.custom;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

    private UUID id;
    private String entityName;
    public EntityNotFoundException(UUID id, String entityName) {
        super("No " + entityName + " with id '" + id + "' has been found!");
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public String getEntityName() {
        return this.entityName;
    }
}
