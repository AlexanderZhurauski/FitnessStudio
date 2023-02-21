package org.mycompany.fitness.core.exceptions.custom;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UUID id) {
        super("No user with id '" + id + "' has been found!");
        this.id = id;
    }
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
