package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dto.converters.InstantToUnix;
import dto.converters.UnixToInstant;
import dto.enums.UserRole;
import dto.enums.UserStatus;

import java.time.Instant;
import java.util.UUID;

public class UserDTO {

    private UUID uuid;
    private boolean readOnly;
    @JsonProperty(namespace = "dt_create")
    @JsonSerialize(converter = InstantToUnix.class)
    @JsonDeserialize(converter = UnixToInstant.class)
    private Instant creationTime;
    @JsonProperty(namespace = "dt_update")
    @JsonSerialize(converter = InstantToUnix.class)
    @JsonDeserialize(converter = UnixToInstant.class)
    private Instant lastUpdated;
    private	String mail;
    @JsonProperty(namespace = "fio")
    private	String fullName;
    private UserRole role;
    private UserStatus status;

    public UserDTO() {
    }

    public UserDTO(UUID uuid, boolean readOnly, Instant creationTime,
                   Instant lastUpdated, String mail,
                   String fullName, UserRole role, UserStatus status) {
        this.uuid = uuid;
        this.readOnly = readOnly;
        this.creationTime = creationTime;
        this.lastUpdated = lastUpdated;
        this.mail = mail;
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

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
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
