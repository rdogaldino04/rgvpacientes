package com.galdino.rgvpacientes.domain.user.repository;

import com.galdino.rgvpacientes.domain.user.dto.UserFilter;
import com.galdino.rgvpacientes.domain.user.model.User;

import java.util.List;


public interface UserRepositoryQueries {

	List<User> getAll(UserFilter filter);

}
