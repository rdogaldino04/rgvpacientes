package com.galdino.rgvpacientes.shared.security.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class UsernamePasswordAuthenticationTokenCustom extends UsernamePasswordAuthenticationToken {

    private final Long id;

    public UsernamePasswordAuthenticationTokenCustom(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Long id) {
        super(principal, credentials, authorities);
        this.id = id;
    }

}
