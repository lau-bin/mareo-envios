package com.lautarocutri.dev.mareoenvios.auth;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.lautarocutri.dev.mareoenvios.database.DBConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {

	@NonNull
	@Id
	@Column(nullable = false, updatable = false, length = DBConst.DB_UUID_LENGTH)
	private UUID id;

	@NotNull
	@NonNull
	@Column(nullable = false, length = DBConst.DB_STRING_LENGTH)
	private String username;

	@NotNull
	@NonNull
	@Column(nullable = false, length = DBConst.DB_STRING_LENGTH)
	private String password;

	private boolean enabled;

	@NotNull
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = DBConst.DB_ENUM_LENGTH)
	private Role role;

	public enum Role {
		ADMIN,
		USER;
	}
}
