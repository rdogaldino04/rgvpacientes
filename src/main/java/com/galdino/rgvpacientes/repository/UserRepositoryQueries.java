package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.user.UserFilter;
import com.galdino.rgvpacientes.model.User;

import java.util.List;


public interface UserRepositoryQueries {

	List<User> getAll(UserFilter filter);

}
