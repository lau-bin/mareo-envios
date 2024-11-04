package com.lautarocutri.dev.mareoenvios.database;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditedEntityListener {
	@PrePersist
	public void setCreationInfo(AuditedEntity entity) {
		entity.setTimestampCreation(LocalDateTime.now(ZoneOffset.UTC));
		entity.setUserCreationId(UUID.fromString(getCurrentUsername()));
	}

	@PreRemove
	public void setDeletionInfo(AuditedEntity entity) {
		entity.setTimestampDeletion(LocalDateTime.now(ZoneOffset.UTC));
		entity.setUserDeletionId(UUID.fromString(getCurrentUsername()));
	}

	private String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			return authentication.getName();
		}
		throw new IllegalStateException("Anonymous operations are not allowed");
	}
}
