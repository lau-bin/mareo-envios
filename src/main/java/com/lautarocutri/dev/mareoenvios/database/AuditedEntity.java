package com.lautarocutri.dev.mareoenvios.database;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditedEntityListener.class)
@Audited
public abstract class AuditedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NonNull
	@Column(nullable = false, length = DBConst.DB_UUID_LENGTH)
	private UUID userCreationId;

	@Nullable
	@Column(length = DBConst.DB_UUID_LENGTH)
	private UUID userDeletionId;

	@NotNull
	@NonNull
	@Column(nullable = false)
	private LocalDateTime timestampCreation;

	@Nullable
	private LocalDateTime timestampDeletion;

	@Version
	private Long version;
}
