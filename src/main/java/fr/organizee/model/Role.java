package fr.organizee.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * User possible roles.
 */
public enum Role implements GrantedAuthority {

    ROLE_PARENT, ROLE_ENFANT;
    
    public String getAuthority() {
        return name();
    }
}
