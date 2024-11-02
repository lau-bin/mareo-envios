package com.lautarocutri.dev.mareoenvios.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AuthUser, Long> {
	Optional<AuthUser> findByUsername(String username);
}
