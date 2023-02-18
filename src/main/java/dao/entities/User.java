package dao.entities;



import core.dto.enums.UserRole;
import core.dto.enums.UserStatus;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "app")
public class User {
    @Id
    private UUID uuid;
    @Column(name = "creation_time")
    private Instant creationTime;
    @Column(name = "last_updated")
    @Version
    private Instant lastUpdated;
    private	String mail;
    private String password;
    @Column(name = "full_name")
    private	String fullName;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public User() {
    }

    public User(UUID uuid, Instant creationTime, Instant lastUpdated,
                String mail, String password, String fullName,
                UserRole role, UserStatus status) {
        this.uuid = uuid;
        this.creationTime = creationTime;
        this.lastUpdated = lastUpdated;
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
