package org.mycompany.fitness.dao.entities;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.mycompany.fitness.core.dto.enums.UserRole;
import org.mycompany.fitness.core.dto.enums.UserStatus;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID uuid;
    @Column(name = "creation_time", nullable = false)
    private Instant creationTime = Instant.now();
    @Column(name = "last_updated", nullable = false)
    @Version
    private Instant lastUpdated = Instant.now();
    @NotBlank
    @NotNull
    private	String mail;
    @NotBlank
    @NotNull
    private String password;
    @Column(name = "full_name")
    @NotBlank
    @NotNull
    private	String fullName;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public User() {
    }

    public User(String mail, String password, String fullName,
                UserRole role, UserStatus status) {
        this.mail = mail;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
