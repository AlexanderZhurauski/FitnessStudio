package org.mycompany.fitness.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.mycompany.fitness.core.dto.enums.UserRole;
import org.mycompany.fitness.core.dto.enums.UserStatus;

@JsonPropertyOrder({
        "mail",
        "fio",
        "role",
        "status",
        "password"
})
public class UserCreateDTO {

    private String mail;
    private String fullName;
    private UserRole role;
    private UserStatus status;
    private String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String mail, String fio, UserRole role,
                         UserStatus status, String password) {
        this.mail = mail;
        this.fullName = fio;
        this.role = role;
        this.status = status;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
