package com.galdino.rgvpacientes;

import com.galdino.rgvpacientes.shared.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
@EnableCaching
public class RgvPacientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RgvPacientesApplication.class, args);
	}

}
