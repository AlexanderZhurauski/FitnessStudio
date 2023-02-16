package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import dto.enums.UserRole;
import dto.enums.UserStatus;

import java.time.Instant;
import java.util.UUID;
@JsonPropertyOrder({
        "baseEssence",
        "mail",
        "fio",
        "role",
        "status"
})
public class UserDTO {

    @JsonUnwrapped
    private BaseEssence baseEssence;
    private	String mail;

    private	String fullName;
    private UserRole role;
    private UserStatus status;

    public UserDTO() {
    }

    public UserDTO(BaseEssence baseEssence, String mail,
                   String fullName, UserRole role, UserStatus status) {
        this.baseEssence = baseEssence;
        this.mail = mail;
        this.fullName = fullName;
        this.role = role;
        this.status = status;
    }

    public BaseEssence getBaseEssence() {
        return baseEssence;
    }

    public void setBaseEssence(BaseEssence baseEssence) {
        this.baseEssence = baseEssence;
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
