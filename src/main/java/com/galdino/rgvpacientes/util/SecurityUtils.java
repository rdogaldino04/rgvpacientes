package com.galdino.rgvpacientes.util;

import com.galdino.rgvpacientes.model.User;
import com.galdino.rgvpacientes.model.UsernamePasswordAuthenticationTokenCustom;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static User getUserCurrent() {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal instanceof UsernamePasswordAuthenticationTokenCustom) {
            UsernamePasswordAuthenticationTokenCustom principal1 = (UsernamePasswordAuthenticationTokenCustom) principal;
            User user = new User();
            user.setId(principal1.getId());
            user.setUsername(principal1.getName());
            return user;
        }
        return null;
    }
}
