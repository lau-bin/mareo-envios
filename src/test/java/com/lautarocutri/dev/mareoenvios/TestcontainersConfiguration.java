package com.lautarocutri.dev.mareoenvios;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	public JdbcDatabaseContainer<?> jdbcDatabaseContainer() {
		JdbcDatabaseContainer<?> container = new PostgreSQLContainer<>("postgres")
				.withDatabaseName("testdb")
				.withUsername("testuser")
				.withPassword("testpass");

		container.start();
		return container;
	}
}
