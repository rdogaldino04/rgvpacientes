package com.galdino.rgvpacientes.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class UserDetailsCustom extends User {

    private final Long id;

    public UserDetailsCustom(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id) {
        super(username, password, authorities);

        this.id = id;
    }

}
