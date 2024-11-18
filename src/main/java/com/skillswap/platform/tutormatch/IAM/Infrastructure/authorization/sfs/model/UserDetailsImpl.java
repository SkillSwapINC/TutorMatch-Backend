package com.skillswap.platform.tutormatch.IAM.Infrastructure.authorization.sfs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Aggregates.UserAuth;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * This class is responsible for providing the user details to the Spring Security framework.
 * It implements the UserDetails interface.
 */
@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private final String username;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    /**
     * This method is responsible for building the UserDetailsImpl object from the User object.
     * @param userAuth The user object.
     * @return The UserDetailsImpl object.
     */
    public static UserDetailsImpl build(UserAuth userAuth) {
        var authorities = userAuth.getRoles().stream()
                .map(role -> role.getName().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                userAuth.getUsername(),
                userAuth.getPassword(),
                authorities);
    }
}