package com.galdino.rgvpacientes.domain.user.repository;

import com.galdino.rgvpacientes.domain.user.model.User;
import com.galdino.rgvpacientes.shared.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository
		extends CustomJpaRepository<User, Long>, JpaSpecificationExecutor<User>, UserRepositoryQueries {

	Optional<User> findByUsername(String username);

	@Query("select u from User u join fetch u.roles r order by u.username")
	List<User> findAll();

}
