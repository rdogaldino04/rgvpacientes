package com.galdino.rgvpacientes.user.repository;

import com.galdino.rgvpacientes.user.dto.UserFilter;
import com.galdino.rgvpacientes.user.model.User;

import java.util.List;


public interface UserRepositoryQueries {

	List<User> getAll(UserFilter filter);

}
