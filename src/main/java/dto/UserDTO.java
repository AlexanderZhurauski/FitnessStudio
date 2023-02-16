package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dto.converters.InstantToUnix;
import dto.converters.UnixToInstant;
import dto.enums.UserRole;
import dto.enums.UserStatus;

import java.time.Instant;
import java.util.UUID;
@JsonPropertyOrder({
        "uuid",
        "dt_create",
        "dt_update",
        "mail",
        "fio",
        "role",
        "status"
})
public class UserDTO {

    private UUID uuid;
    @JsonSerialize(converter = InstantToUnix.class)
    @JsonDeserialize(converter = UnixToInstant.class)
    private Instant creationTime;
    @JsonSerialize(converter = InstantToUnix.class)
    @JsonDeserialize(converter = UnixToInstant.class)
    private Instant lastUpdated;
    private	String mail;

    private	String fullName;
    private UserRole role;
    private UserStatus status;

    public UserDTO() {
    }

    public UserDTO(UUID uuid, Instant creationTime,
                   Instant lastUpdated, String mail,
                   String fullName, UserRole role, UserStatus status) {
        this.uuid = uuid;
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
    @JsonProperty("dt_create")
    public Instant getCreationTime() {
        return creationTime;
    }
    @JsonProperty("dt_create")
    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    @JsonProperty("dt_update")
    public Instant getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("dt_update")
    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @JsonProperty("fio")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("fio")
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
