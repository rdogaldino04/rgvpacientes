package com.galdino.rgvpacientes.shared.security.user;

import com.galdino.rgvpacientes.domain.user.model.User;

public interface UserSecurityService {

    User getUserById(Long id);
}
