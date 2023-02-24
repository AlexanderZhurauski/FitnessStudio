package org.mycompany.fitness.dao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.mycompany.fitness.core.dto.enums.UserStatus;

@Entity
@Table(schema = "app", name = "user_status")
public class Status {
    @Id
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public Status() {
    }

    public Status(UserStatus status) {
        this.status = status;
        this.id = status.ordinal() + 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
