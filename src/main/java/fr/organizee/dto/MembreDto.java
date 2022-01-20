package fr.organizee.dto;

import java.util.List;

import com.sun.istack.NotNull;

import fr.organizee.model.Role;

/**
 * Specifique : AppUser DTO permet de renvoyer un User sans le mot de passe (REST response).
 */
public class MembreDto {

    private Long id;
    private String email;
    private List<Role> roleList;

    public MembreDto() {
    }

    public MembreDto(@NotNull String email) {
        this(email, null);
    }

    public MembreDto(@NotNull String email, List<Role> roleList) {
        this.email = email;
        this.roleList = roleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
